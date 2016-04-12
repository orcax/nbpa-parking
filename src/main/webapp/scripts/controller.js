var nbpaControllers = angular.module('nbpaControllers', ['ngSanitize']);

nbpaControllers.controller('NavCtrl', ['$scope', '$location',
  function($scope, $location) {
    $scope.isActive = function (viewLocation) { 
      return viewLocation === $location.path();
    };
  }
]);

nbpaControllers.controller('HomeCtrl', ['$scope', '$http',
  function($scope, $http) {
  }
]);

nbpaControllers.controller('StatsCtrl', ['$scope', '$http',
  function($scope, $http) {
  }
]);