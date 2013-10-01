'use strict';

/* Controllers */

angular.module(
	'myApp.controllers',
	[ 'ngRoute', 'ngTable', 'ui.bootstrap.dialog', 'angulartics',
		'angulartics.google.analytics' ])

.config([ '$locationProvider', function($locationProvider) {

    $locationProvider.html5Mode(false).hashPrefix('!');

} ])

.run(
	[
		'$rootScope',
		'$location',
		function($rootScope, $location) {
		    $rootScope.ABSOLUTE_URL = $location.absUrl().replace(
			    /localhost:\d{2,4}/, 'dtelaroli.org');
		} ])

.controller('SearchCtrl', [ '$scope', '$dataService', function($scope, $data) {

    $scope.clear = function() {
	$scope.filter = '';
    };

    $scope.$watch('filter', function(filter) {
	$data.set('filter', filter);
    });

} ])

.controller(
	'GridCtrl',
	[ '$scope', '$restService', '$filter', '$dialog', 'ngTableParams',
		'$dataService',
		function($scope, $rest, $filter, $dialog, $table, $data) {

		    function filter(list, params) {
			if (params.filter) {
			    var data = $filter('filter')(list, params.filter);
			    return order(data, params);
			}
			return order(list, params);
		    }

		    function order(data, params) {
			if (params.sorting) {
			    return $filter('orderBy')(data, params.orderBy());
			}
			return data;
		    }

		    function paginate(data, params) {
			var start = (params.page - 1) * params.count;
			var count = params.page * params.count;
			return data.slice(start, count);
		    }

		    $scope.$tableParams = {};

		    $rest().query(function(data) {
			$scope.$list = data;
			$scope.$tableParams = new $table({
			    page : 1,
			    total : data.length,
			    count : 10,
			    filter : $data.get('filter')
			});
		    });

		    $scope.$watch('$tableParams', function(params) {
			var orderedData = filter($scope.$list, params);
			if (typeof orderedData != 'undefined') {
			    params.total = orderedData.length;
			    $scope.$items = paginate(orderedData, params);
			}
		    }, true);

		    $scope.$watch(function() {
			return $data.get('filter');
		    }, function(filter) {
			$scope.$tableParams.filter = filter;
		    });

		} ])

.controller(
	'AddCtrl',
	[ '$scope', '$restService', '$routeParams', '$log',
		function($scope, $rest, $routeParams, $log) {
		    $scope.$model = {
			parent : null
		    };
		    $scope.persist = function() {
			$rest().save({
			    model : $scope.$model
			}, function(result) {
			    $scope.$model = result;
			});
		    };

		} ])

.controller(
	'EditCtrl',
	[ '$scope', '$restService', '$routeParams',
		function($scope, $rest, $routeParams) {

		    $rest().get({
			id : $routeParams.id
		    }, function(result) {
			$scope.$model = result;
		    });

		    $scope.persist = function() {
			$scope.$model.$update({
			    id : $scope.$model.id
			});
		    };

		} ])

.controller(
	'DeleteCtrl',
	[
		'$scope',
		'$restService',
		'$location',
		'$dialog',
		'$routeParams',
		'STATIC_CONTEXT',
		function($scope, $rest, $location, $dialog, $routeParams,
			CONTEXT) {

		    var options = {
			backdrop : true,
			keyboard : true,
			backdropClick : true,
			templateUrl : CONTEXT + '/partials/message.tpl.html',
			controller : 'DialogCtrl'
		    };

		    $dialog.dialog(options)

		    .open().then(function(result) {
			if (result) {
			    $rest().remove({
				id : $routeParams.id
			    }, function() {
				$location.path('/');
			    });
			} else {
			    $location.path('/');
			}
		    });

		} ])

.controller('DialogCtrl', [ '$scope', 'dialog', function($scope, dialog) {

    $scope.cancel = function(result) {
	dialog.close(false);
    };

    $scope.ok = function() {
	dialog.close(true);
    };

} ])

.controller('IndexCtrl', [ '$scope', '$restService', '$analytics', function($scope, $rest, $analytics) {

    $analytics.pageTrack('/vp-blog/');
    $rest().query(function(data) {
	$scope.$list = data;
    });

} ])

.controller(
	'ViewCtrl',
	[
		'$scope',
		'$restService',
		'$sce',
		'$routeParams',
		'$analytics',
		function($scope, $rest, $sce, $routeParams, $analytics) {
		    $analytics.pageTrack('/vp-blog/' + $routeParams.id);
		    $rest().get(
			    {
				id : $routeParams.id
			    },
			    function(result) {
				$scope.$model = result;
				$scope.$model.content = $sce
					.trustAsHtml(result.content);
			    });
		} ]);