'use strict';

nbpaApp.controller('StatsCtrl', ['$scope', '$http',
  function($scope, $http) {

    if (!String.prototype.trim) {
      (function(){  
        // Make sure we trim BOM and NBSP
        var rtrim = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g;
        String.prototype.trim = function () {
          return this.replace(rtrim, "");
        }
      })();
    }

    /**
     * Plot chart
     */
    $scope.plotChart = function() {
      var data = {};
      var location = $('#location').val().trim();
      if(location != '') data.location = location;
      var column = $('#attribute').val().trim();
      if(column != '') data.column = column;
      var startDate = $('#start-date input').val().trim()
      if(startDate != '') data.startDate = startDate;
      var endDate = $('#end-date input').val().trim()
      if(endDate != '') data.endDate = endDate;
      data.min = 0;
      console.log(data);
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