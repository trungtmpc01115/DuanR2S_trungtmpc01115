app.controller("product-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.cates = [];
	$scope.form = {
		image: 'anhmacdinh.png',
		available: true,
	};

	$scope.initialize = function() {
		$http.get("/rest/products").then(resp => {
			$scope.items = resp.data;
		});
		$http.get("/rest/categories").then(resp => {
			$scope.cates = resp.data;
		});


	}

	$scope.initialize();

	$scope.reset = function() {
		$scope.form = {
			image: 'anhmacdinh.png',
			available: true,
		};
	}

	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		$(".nav-tabs a:eq(0)").tab('show')
	}

	$scope.create = function() {
		var item = angular.copy($scope.form);
		$http.post(`/rest/products`, item).then(resp => {
			if (resp.status == 200) {
				$scope.initialize();
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
					title: 'Id sản phẩm đã tồn tại!',
					icon: 'error',
					confirmButtonText: 'Ok',
				})
			}
			Swal.fire({
				width: '400px',
				title: 'Lỗi thêm mới sản phẩm!',
				icon: 'error',
				confirmButtonText: 'Ok',
			})
			console.log("Error", error);
		});
	}

	$scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put(`/rest/products`, item).then(resp => {
			if (resp.status == 200) {
				$scope.initialize();
				return Swal.fire({
					width: '400px',
					title: 'Cập nhật sản phẩm thành công!',
					icon: 'success',
					showConfirmButton: false,
					timer: 1500
				})
			}
		}).catch(error => {
			if (error.status == 404) {
				return Swal.fire({
					width: '400px',
					title: 'Không tìm thấy sản phẩm này!',
					icon: 'error',
					confirmButtonText: 'Ok',
				})
			}
			Swal.fire({
				width: '400px',
				title: 'Lỗi cập nhật sản phẩm!',
				icon: 'error',
				confirmButtonText: 'Ok',
			})
			console.log("Error", error);
		});
	}

	$scope.delete = function(item) {
		Swal.fire({
			width: '400px',
			title: 'Bạn mún xóa sản phẩm này đúng không?',
			showCancelButton: true,
			confirmButtonText: `OK`,
		}).then((result) => {
			if (result.isConfirmed) {
				var idSP = item.id === undefined?-1:item.id
				$http.delete(`/rest/products/${idSP}`).then(resp => {
					if (resp.status == 200) {
						var index = $scope.items.findIndex(p => p.id == item.id);
						$scope.items.splice(index, 1);
						$scope.reset();
						return Swal.fire({
							width: '400px',
							title: 'Xóa sản phẩm thành công!',
							icon: 'success',
							showConfirmButton: false,
							timer: 1500
						})
					}
				}).catch(error => {
					if (error.status == 404) {
						return Swal.fire({
							width: '400px',
							title: 'Không tìm thấy sản phẩm này!',
							icon: 'error',
							confirmButtonText: 'Ok',
						})
					}
					Swal.fire({
						width: '400px',
						title: 'Bạn không thể xóa sản phẩm đã có người mua!',
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
		$http.post('/rest/upload/images/products', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.image = resp.data.name;
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