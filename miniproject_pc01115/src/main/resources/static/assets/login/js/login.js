app = angular.module("login-app", []);

app.controller("login-ctrl", function($scope, $http) {
   $scope.data = {
      "jsonLog": {
         "channel": "WEB_1GATE",
         "imei": "00:50:56:87:02:4d",
         "key": "",
         "lang": "vi",
         "lat": "WEB_1GATE",
         "lng": "WEB_1GATE",
         "model": "",
         "os": "win32",
         "time": "20200720111100",
         "version": "1.0.0"
     },
     "jsonKey": "LOGIN",
     "jsonData": {
         "username": "qlcpnccty9",
         "password": "qlcpcty9"
     }
   };

   $scope.login = function(){   
       if($scope.user==$scope.data.jsonData.username & $scope.pass==$scope.data.jsonData.password){
         window.open("./about.html")
       }else{
          alert("Sai tài khoản hoặc mật khẩu")
       }
   }
})