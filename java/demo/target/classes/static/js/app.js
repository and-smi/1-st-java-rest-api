let NewApp = angular.module("NewApp",[]);

NewApp.controller("readStaff", function ($http) {
    console.log("Hello, motherfucker");
    $http.get("http://localhost:8080/readstaff")
        .success(function (result) {
            console.log("success", result);
        })
        .error(function (result) {
            console.log("error", result);
        });
});

