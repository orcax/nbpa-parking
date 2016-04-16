'use strict';

nbpaApp.controller('HomeCtrl', ['$scope', '$http',
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
      var startDate = $('#start-date input').val().trim()
      if(startDate != '') data.startDate = startDate;
      var endDate = $('#end-date input').val().trim()
      if(endDate != '') data.endDate = endDate;
      var startTime = $('#start-time input').val().trim()
      if(startTime != '') data.startTime = startTime;
      var endTime = $('#end-time input').val().trim()
      if(endTime != '') data.endTime = endTime;
      data.min = 0;
      console.log(data);
      $http({
        method: 'GET',
        url: HOST_URL + '/api/occupancy',
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
      chartObj.data.addColumn('string', 'Datetime');
      chartObj.data.addColumn('number', 'DailyNumber');
      chartObj.data.addColumn('number', 'MonthlyNumber');
      var rows = [];
      for(var i in data) {
        rows.push([String(data[i]['datetime']), data[i]['dailyNumber'], data[i]['monthlyNumber']]);
      }
      chartObj.data.addRows(rows);
      chartObj.options = {
        vAxis: {
          title: 'Number',
        },
        hAxis: {
          title: 'Hour',
        }
      };
      return chartObj;
    }
    
  }
]);