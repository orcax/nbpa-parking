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
      $http({
        method: 'GET',
        url: HOST_URL + '/api/occupancy/v2/meanhour',
        params: data
      }).then(
        function success(resp) {
          var result = resp.data;
          createChart(result);
        },
        function error(resp) {
        	alert('You have not logged in or the seesion has expired. Please login.');
            window.location = "/login.html";
        }
      );
    }
    
    function createChart(data) {
      var categories = [];
      var series = [];
      for(var i in data) {
        var s = {
          name: int2Weekday(i),
          data: []
        };
        var c = [];
        for(var j in data[i]) {
          c.push(data[i][j]['time']);
          s.data.push([data[i][j]['time'], data[i][j]['value']]);
        }
        if(categories.length == 0) categories = c;
        series.push(s);
      }
      console.log(categories);
      console.log(series);
      $('#line-chart').highcharts({
        title: {
          text: 'Hourly Average Number',
          x: -20 //center
        },
        xAxis: {
          type: 'category',
        },
        yAxis: {
          title: {
            text: 'Average Number'
          }
        },
        legend: {
          layout: 'vertical',
          align: 'right',
          verticalAlign: 'middle',
          borderWidth: 0
        },
        series: series
      });
    }
    
  }
]);