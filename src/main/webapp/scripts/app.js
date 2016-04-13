'use strict';

var APP_NAME = 'nbpaApp';

/**
 * Initialize AngularJs app
 */
var nbpaApp = angular.module(APP_NAME, [
  'ngRoute',
  'ngSanitize',
  'googlechart'
]);

/**
 * Configure routing
 */
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