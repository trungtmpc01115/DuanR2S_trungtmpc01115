app.controller("user-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.roles = [];
	$scope.form = {
		photo: 'anhmacdinh.png',
	};

	$scope.director;
	$scope.initialize = function() {
		$http.get("/rest/accounts").then(resp => {
			$scope.items = resp.data;
		});
	}

	$scope.initialize();

	$scope.reset = function() {
		$scope.form = {
			photo: 'anhmacdinh.png',
		};
	}

	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		$(".nav-tabs a:eq(0)").tab('show')
	}

	$scope.create = function() {
		var item = angular.copy($scope.form);
		$http.post(`/rest/accounts`, item).then(resp => {
			if (resp.status == 200) {
				$scope.items.push(resp.data);
				$scope.reset();
				return Swal.fire({
					width: '400px',
					title: 'Thêm mới User thành công!',
					icon: 'success',
					showConfirmButton: false,
					timer: 1500
				})
			}
		}).catch(error => {
			if (error.status == 400) {
				return Swal.fire({
					width: '400px',
					title: 'Username đã tồn tại!',
					icon: 'error',
					confirmButtonText: 'Ok',
				})
			}
			Swal.fire({
				width: '400px',
				title: 'Lỗi thêm mới User!',
				icon: 'error',
				confirmButtonText: 'Ok',
			})
			console.log("Error", error);
		});
	}

	$scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put(`/rest/accounts`, item).then(resp => {
			if (resp.status == 200) {
				var index = $scope.items.findIndex(a => a.username == item.username);
				$scope.items[index] = item;
				return Swal.fire({
					width: '400px',
					title: 'Cập nhật User thành công!',
					icon: 'success',
					showConfirmButton: false,
					timer: 1500
				})
			}
		}).catch(error => {
			if (error.status == 404) {
				return Swal.fire({
					width: '400px',
					title: 'Không tìm thấy User này!',
					icon: 'error',
					confirmButtonText: 'Ok',
				})
			}
			Swal.fire({
				width: '400px',
				title: 'Lỗi cập nhật User!',
				icon: 'error',
				confirmButtonText: 'Ok',
			})
			console.log("Error", error);
		});
	}

	$scope.delete = function(item) {
		Swal.fire({
			width: '400px',
			title: 'Khi bạn xóa sẽ mất các dữ liệu liên quan đến User này ! ',
			showCancelButton: true,
			confirmButtonText: `OK`,
		}).then((result) => {
			if (result.isConfirmed) {
				$http.delete(`/rest/accounts/${item.username}`).then(resp => {
					if (resp.status == 200) {
						var index = $scope.items.findIndex(a => a.username == item.username);
						$scope.items.splice(index, 1);
						$scope.reset();
						return Swal.fire({
							width: '400px',
							title: 'Xóa User thành công!',
							icon: 'success',
							showConfirmButton: false,
							timer: 1500
						})
					}
				}).catch(error => {
					if (error.status == 404) {
						return Swal.fire({
							width: '400px',
							title: 'Không tìm thấy User này!',
							icon: 'error',
							confirmButtonText: 'Ok',
						})
					}
					Swal.fire({
						width: '400px',
						title: 'Lỗi xóa User!',
						icon: 'error',
						confirmButtonText: 'Ok',
					})
					console.log("Error", error);
				});
			}
		})
	}

	$scope.imageChanged = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/images/users', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.photo = resp.data.name;
		}).catch(error => {
			return Swal.fire({
				width: '400px',
				title: 'Lỗi uplaod hình ảnh!',
				icon: 'error',
				confirmButtonText: 'Ok',
			})
			console.log("Error", error);
		})
	}

	$scope.pager = {
		page: 0,
		size: 10,
		get items() {
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.items.length / this.size);
		},

		first() {
			this.page = 0;
		},
		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		},
		last() {
			this.page = this.count - 1;
		},
	}
}); 