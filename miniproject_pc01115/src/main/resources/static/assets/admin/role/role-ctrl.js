app.controller("role-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.form = {};

	$scope.initialize = function() {
		$http.get("/rest/roles").then(resp => {
			$scope.items = resp.data;
		});
	}

	$scope.initialize();

	$scope.reset = function() {
		$scope.form = {};
	}

	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		$(".nav-tabs a:eq(0)").tab('show')
	}

	$scope.create = function() {
		var item = angular.copy($scope.form);
		$http.post(`/rest/roles`, item).then(resp => {
			if (resp.status == 200) {
				$scope.items.push(resp.data);
				$scope.reset();
				return Swal.fire({
					width: '400px',
					title: 'Thêm mới thành công!',
					icon: 'success',
					showConfirmButton: false,
					timer: 1500
				})
			}
		}).catch(error => {
			if (error.status == 400) {
				return Swal.fire({
					width: '400px',
					title: 'Id đã tồn tại!',
					icon: 'error',
					confirmButtonText: 'Ok',
				})
			}
			Swal.fire({
				width: '400px',
				title: 'Lỗi thêm mới Role!',
				icon: 'error',
				confirmButtonText: 'Ok',
			})
			console.log("Error", error);
		});
	}

	$scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put(`/rest/roles`, item).then(resp => {
			if (resp.status == 200) {
				var index = $scope.items.findIndex(a => a.id == item.id);
				$scope.items[index] = item;
				return Swal.fire({
					width: '400px',
					title: 'Cập nhật thành công!',
					icon: 'success',
					showConfirmButton: false,
					timer: 1500
				})
			}
		}).catch(error => {
			if (error.status == 404) {
				return Swal.fire({
					width: '400px',
					title: 'Không tìm thấy role này!',
					icon: 'error',
					confirmButtonText: 'Ok',
				})
			}
			Swal.fire({
				width: '400px',
				title: 'Lỗi cập nhật!',
				icon: 'error',
				confirmButtonText: 'Ok',
			})
			console.log("Error", error);
		});
	}

	$scope.delete = function(item) {
		Swal.fire({
			width: '400px',
			title: 'Khi bạn xóa sẽ mất các dữ liệu liên quan đến role này !',
			showCancelButton: true,
			confirmButtonText: `OK`,
		}).then((result) => {
			if (result.isConfirmed) {
				$http.delete(`/rest/roles/${item.id}`).then(resp => {
					if (resp.status == 200) {
						var index = $scope.items.findIndex(a => a.id == item.id);
						$scope.items.splice(index, 1);
						$scope.reset();
						return Swal.fire({
							width: '400px',
							title: 'Xóa thành công!',
							icon: 'success',
							showConfirmButton: false,
							timer: 1500
						})
					}
				}).catch(error => {
					if (error.status == 404) {
						return Swal.fire({
							width: '400px',
							title: 'Không tìm thấy role này!',
							icon: 'error',
							confirmButtonText: 'Ok',
						})
					}
					console.log("Error", error);
				});
			}
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