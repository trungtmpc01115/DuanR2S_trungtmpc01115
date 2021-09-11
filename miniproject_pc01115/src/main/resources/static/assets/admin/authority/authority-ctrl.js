app.controller("authority-ctrl",function($scope,$http,$location){
	$scope.roles = [];
	$scope.admins = [];
	$scope.authorities=[];
	
	$scope.initialize = function(){
		$http.get("/rest/roles").then(resp =>{
			$scope.roles = resp.data;
		})
		
		$http.get("/rest/accounts").then(resp =>{
			$scope.admins = resp.data;
		})
		
		$http.get("/rest/authorities").then(resp =>{
			$scope.authorities = resp.data;
		}).catch(error =>{
			$location.path("/authority");
		})	
	}
	
	$scope.authority_of = function(acc,role){
		if($scope.authorities){
			return $scope.authorities.find(ur => ur.account.username == acc.username && ur.role.id == role.id);
		}
	}
	
	$scope.authority_changed = function(acc,role){
		var authority = $scope.authority_of(acc,role);
		if(authority){
			$scope.revoke_authority(authority);
		}
		else{
			authority = {account:acc,role:role};
			$scope.grant_authority(authority);
		}
	}
	
	$scope.grant_authority = function(authority){
		$http.post(`/rest/authorities`,authority).then(resp =>{
			$scope.authorities.push(resp.data)
			return Swal.fire({
				width: '400px',
				title: 'Cấp quyền sử dụng thành công!',
				icon: 'success',
				showConfirmButton: false,
				timer: 1500
			})
		}).catch(error =>{
			return Swal.fire({
				width: '400px',
				title: 'Lỗi cấp quyền!',
				icon: 'error',
				confirmButtonText: 'Ok',
			})
			console.log("Error",error);
		})
	}
	
	$scope.revoke_authority = function(authority){
		$http.delete(`/rest/authorities/${authority.id}`).then(resp =>{
			var index = $scope.authorities.findIndex(a => a.id == authority.id);
			$scope.authorities.splice(index,1);
		    return Swal.fire({
				width: '400px',
				title: 'Thu hồi quyền sử dụng thành công!',
				icon: 'success',
				showConfirmButton: false,
				timer: 1500
			})
		}).catch(error =>{
			return Swal.fire({
				width: '400px',
				title: 'Lỗi thu hồi quyền!',
				icon: 'error',
				confirmButtonText: 'Ok',
			})
			console.log("Error",error);
		})
	}
	
	
	$scope.initialize();
	
})