'use strict';

nbpaApp.controller('StatsCtrl', ['$scope', '$http',
  function($scope, $http) {

    $scope.plotChart = function() {
      var location = $('#location').val();
      var attribute = $('#attribute').val();
      alert(location);
    }

  }
]);