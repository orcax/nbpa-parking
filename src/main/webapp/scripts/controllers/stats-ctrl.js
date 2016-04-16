'use strict';

nbpaApp.controller('StatsCtrl', ['$scope', '$http',
  function($scope, $http) {

    /**
     * Plot chart
     */
    $scope.plotChart = function() {
      var data = {};
      data.location = $('#location').val();
      data.column = $('#attribute').val();
      data.weekday = $('#weekday').val();
      data.min = 0.0;
      $http({
        method: 'GET',
        url: HOST_URL + '/api/occupancy/meanhour',
        params: data
      }).then(
        function success(resp) {
          $scope.chartObject = createChartObj(resp.data);
        },
        function error(resp) {
          
        }
      );
    }
    
    function createChartObj(data) {
      var chartObj = {};
      chartObj.type = 'LineChart';
      chartObj.data = new google.visualization.DataTable();
      chartObj.data.addColumn('string', 'Hour');
      chartObj.data.addColumn('number', 'Average Number');
      var rows = [];
      for(var i in data) {
        rows.push([data[i]['time'], data[i]['value']]);
      }
      chartObj.data.addRows(rows);
      chartObj.options = {
        vAxis: {
          title: 'Average Number',
        },
        hAxis: {
          title: 'Hour',
        }
      };
      return chartObj;
    }

  }
]);