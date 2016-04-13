'use strict';

nbpaApp.controller('NavCtrl', ['$scope', '$location',
  function($scope, $location) {

    $scope.isActive = function (viewLocation) { 
      return viewLocation === $location.path();
    };

  }
]);