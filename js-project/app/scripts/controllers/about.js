'use strict';

/**
 * @ngdoc function
 * @name jsProjectApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the jsProjectApp
 */
angular.module('jsProjectApp')
  .controller('AboutCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
