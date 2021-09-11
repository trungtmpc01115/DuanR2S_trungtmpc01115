app.controller("home-ctrl",function($scope,$http,$location){
	$scope.items = [];
	$scope.initialize = function() {
		$http.get("/rest/products").then(resp => {
			$scope.items = resp.data;
		});
	}
	
	$scope.initialize();
	
})