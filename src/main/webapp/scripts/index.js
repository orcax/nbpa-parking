var nbpaApp = angular.module('nbpaApp', [
  'ngRoute',
  'nbpaControllers'
]);

nbpaApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/home', {
        templateUrl: 'views/home.html',
        controller: 'HomeCtrl'
      }).
      when('/stats', {
        templateUrl: 'views/stats.html',
        controller: 'StatsCtrl'
      }).
      otherwise({
        redirectTo: '/home'
      });
  } 
]);