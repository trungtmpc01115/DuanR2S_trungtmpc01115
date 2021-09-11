app = angular.module("admin-app", ["ngRoute"]);

app.controller("admin-ctrl", function($scope, $http, $rootScope,$window) {
		$http.get("/rest/authorities/admin").then(resp =>{
			$rootScope.isAdmin = resp.data;
		});
		$http.get("/rest/authorities/pm").then(resp =>{
			$rootScope.isPm = resp.data;	
		});
		
		$scope.logout = function() {
			Swal.fire({
				width: '400px',
				title: 'Bạn chắc chắn mún đăng xuất?',
				showCancelButton: true,
				confirmButtonText: `OK`,
			}).then((result) => {
				if (result.isConfirmed) {
	          	 	$window.location.href = '/security/logoff';
				}
			})
		}
		
})

app.config(function($routeProvider) {
	$routeProvider
	    .when("/home", {
			templateUrl: "/assets/admin/home/index.html",
			controller: "home-ctrl"
		})
		.when("/product", {
			templateUrl: "/assets/admin/product/index.html",
			controller: "product-ctrl"
		})
		.when("/category", {
			templateUrl: "/assets/admin/category/index.html",
			controller: "category-ctrl"
		})
		.when("/user", {
			templateUrl: "/assets/admin/user/index.html",
			controller: "user-ctrl"
		})
		.when("/authority", {
			templateUrl: "/assets/admin/authority/index.html",
			controller: "authority-ctrl"
		})
		.when("/role", {
			templateUrl: "/assets/admin/role/index.html",
			controller: "role-ctrl"
		})
		.otherwise({
			templateUrl: "/assets/admin/home/index.html",
			controller: "home-ctrl"
		});
})