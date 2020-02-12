let NewApp = angular.module("NewApp",[]);

NewApp.controller("readStaff", function ($http, $scope) {
    console.log("Hello, motherfucker");
    $http.get({
        method: 'GET',
        url: 'http://localhost:8080/readstaff'
    })
        .then(function (response) {
            //console.log(response.data)
        $scope.readstaff = response.data;
    });


});

