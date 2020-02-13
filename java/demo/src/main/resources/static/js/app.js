let NewApp = angular.module("NewApp",[]);

NewApp.controller("readStaff", function ($http, $scope) {
    console.log("Hello, motherfucker");
    $http.get(
       'http://localhost:8080/readstaff'
    ).then(function (response) {
        $scope.readstaff = response.data;
    })



});

