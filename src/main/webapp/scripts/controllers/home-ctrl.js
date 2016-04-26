'use strict';

nbpaApp.controller('HomeCtrl', ['$scope', '$http',
  function($scope, $http) {
    Highcharts.setOptions({
      global: {
        timezoneOffset: 5 * 60
      }
    });

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
      $http({
        method: 'GET',
        url: HOST_URL + '/api/occupancy',
        params: data
      }).then(
        function success(resp) {
          var result = resp.data;
          createStock(result);
        },
        function error(resp) {
        }
      );
    }
    
    function createStock(data) {
      var names = ['Daily', 'Monthly', 'Total'];
      var seriesOptions = [
        { name: 'Daily', data: [] }, 
        { name: 'Monthly', data: [] }, 
        { name: 'Total', data: [] }
      ];
      for(var i in data) {
        seriesOptions[0].data.push([data[i]['datetime'], data[i]['dailyNumber']]);
        seriesOptions[1].data.push([data[i]['datetime'], data[i]['monthlyNumber']]);
        seriesOptions[2].data.push([data[i]['datetime'], data[i]['totalNumber']]);
      }
      $('#stock-chart').highcharts('StockChart', {
        rangeSelector: {
          selected: 1
        },
        title: {
          text: 'Time Series Data'
        },
        legend: {
          align: 'center'
        },
        plotOptions: {
          series: {
            compare: 'value'
          }
        },
        tooltip: {
          pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> <br/>',
          valueDecimals: 2
        },
        series: seriesOptions
    }); 
    }
    
  }
]);