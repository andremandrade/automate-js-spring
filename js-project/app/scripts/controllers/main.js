'use strict';

/**
 * @ngdoc function
 * @name jsProjectApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the jsProjectApp
 */
angular.module('jsProjectApp')
  .controller('MainCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
