/**
 * CommentsSystemDemo main js file
 */

var menue;
var commentsSystemDemoApp = angular.module("commentsSystemDemoApp", ['ngRoute']);

commentsSystemDemoApp.config(function($routeProvider) {
	$routeProvider
		.when('/login', {
			templateUrl: 'html/login.html',
			controller: 'LoginControllerController'
		}).when('/register', {
			templateUrl: 'html/register.html',
			controller: 'RegisterController'
		}).when('/getComments', {
			templateUrl: 'html/comments.html',
			controller: 'GetCommentsController'
		}).when('/addComment', {
			templateUrl: 'html/addComment.html',
			controller: 'AddCommentController'
		}).when('/logout', {
			templateUrl: 'html/login.html',
			controller: 'LogoutControllerController'
		}).when('/elements', {
			templateUrl: 'html/elements.html',
			controller: 'ElementsController'
		}).otherwise({
			redirectTo: '/login'
		});
});

commentsSystemDemoApp.controller('LoginControllerController', function($window, $location, $scope, $rootScope, $route, $http) {
	$scope.message = "";
	$rootScope.mainMenuItems = [{name: "login", link: "login"}, {name: "register", link: "register"}, {name: "elements", link: "elements"}];
	$scope.login = function () {
		cdata = 'username='+$scope.username+'&password='+$scope.password;
		
		$http({
		    method: 'POST',
		    url: 'http://localhost:8080/CommentsSystemDemo/rest/doLogin',
		    data: cdata,
		    headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).then(function(result) {
		    	$location.path( "/getComments" );
		       }, function(error) {
		           console.log(error);
		       });
    };
});

commentsSystemDemoApp.controller('LogoutControllerController', function($window, $location, $scope, $rootScope, $route, $http) {
	$scope.logout = function() {
		$http.get("http://localhost:8080/CommentsSystemDemo/rest/doLogout")
	    .then(function successCallback(response){
	    	$location.path("/login");
	    }, function errorCallback(response){
	    	$location.path("/login");
	    });
	};
	
	$scope.logout();
	
});

commentsSystemDemoApp.controller('RegisterController', function($scope, $rootScope, $http) {
	$scope.message = "";
	$rootScope.mainMenuItems = [{name: "login", link: "login"}, {name: "elements", link: "elements"}];
	$scope.register = function () {
		data = {"username": $scope.username, "password": $scope.password, "email": $scope.email, "phone": $scope.phone};
        $http.post("http://localhost:8080/CommentsSystemDemo/rest/register", data)
            .then(function successCallback(response){
            	$scope.message = "Successfully POST-ed data"
                console.log("Successfully POST-ed data");
            }, function errorCallback(response){
            	$scope.message = "POST-ing of data failed"
                console.log("POST-ing of data failed");
            });
    };
});

commentsSystemDemoApp.controller('ElementsController', function($scope, $rootScope, $http) {
	$scope.message = "";
	$rootScope.mainMenuItems = [{name: "login", link: "login"}, {name: "register", link: "register"}, {name: "elements", link: "elements"}];
});

commentsSystemDemoApp.controller('GetCommentsController', function($scope, $rootScope, $http,$location) {
	$scope.message = "";
	$rootScope.mainMenuItems = [{name: "Add Comment", link: "addComment"}, {name: "Logout", link: "logout"}];
    $scope.getComments = function () {
        console.log("I've been pressed!");  
        $http.get("http://localhost:8080/CommentsSystemDemo/rest/getComments")
            .then(function successCallback(response){
                $scope.response = response;
                console.log(response);
                $scope.comments = response.data;
                
                $http.get("http://localhost:8080/CommentsSystemDemo/rest/username")
                .then(function successCallback(response){
                	$scope.message += ' for user' + response.data;   
                }, function errorCallback(response){
                	$scope.message += ' for user UNKNOWN';
                    console.log("http://localhost:8080/CommentsSystemDemo/rest/username");
                });
            }, function errorCallback(response){
            	if (response.status === 401) {
            		$location.path( "/login" );
        	    }
                console.log("Unable to perform get request to http://localhost:8080/CommentsSystemDemo/rest/getComments");
                $scope.comments = [{}];
            });
    };
    
    $scope.getComments();
});

commentsSystemDemoApp.controller('AddCommentController', function($scope, $rootScope, $location, $http) {
	$scope.message = "";
	$rootScope.mainMenuItems = [{name: "Get Comments", link: "getComments"}, {name: "Logout", link: "logout"}];
	$scope.addComment = function () {
		$scope.message = "";
		
		if($scope.comment == undefined || $scope.comment === 0 || !$scope.comment.trim()  === '') {
			$scope.message = "Cannot add empty comment";
			return;
		}
		
		/*
		 data = {"personId":1, "personComment": $scope.comment};
        $http.post("http://localhost:8080/CommentsSystemDemo/rest/addComment", data)
            .then(function successCallback(response){
            	$location.path("/getComments");
                console.log("Successfully POST-ed data");
            }, function errorCallback(response){
            	$scope.message = "POST-ing of data failed"
                console.log("POST-ing of data failed");
            });
		 * */
        
		cdata = 'personId='+1+'&personComment='+$scope.comment;
		$http({
		    method: 'POST',
		    url: 'http://localhost:8080/CommentsSystemDemo/rest/addComment',
		    data: cdata,
		    headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).then(function(result) {
		    	$location.path( "/getComments" );
		       }, function(error) {
		    	   $scope.message = "POST-ing of data failed"
		           console.log(error);
		       });
    };
});

