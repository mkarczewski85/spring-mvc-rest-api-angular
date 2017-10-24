var app = angular.module('crudApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/manager',
    EMP_SERVICE_API : 'http://localhost:8080/api/employee/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/list',
                controller:'EmployeeController',
                controllerAs:'ctrl',
                resolve: {
                    users: function ($q, EmployeeService) {
                        console.log('Load all employees');
                        var deferred = $q.defer();
                        EmployeeService.loadAllEmployees().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);

