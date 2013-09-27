'use strict';

/* Directives */

angular.module('myApp.directives', [])

.directive('appVersion', [ 'version', function(version) {
    return function(scope, elm, attrs) {
	elm.text(version);
    };
} ])

.directive('vpBlank', [ function() {
    return {
	link: function(scope, elm, attrs) {
	    elm.attr('target', '_blank');
	}
    };
} ])

.directive('ckEditor', [ function() {
    return {
	require : '?ngModel',
	link : function($scope, elm, attr, ngModel) {

	    var ck = CKEDITOR.replace(elm[0]);

	    ck.on('instanceReady', function() {
		ck.setData(ngModel.$viewValue);
	    });

	    ck.on('pasteState', function() {
		$scope.$apply(function() {
		    ngModel.$setViewValue(ck.getData());
		});
	    });

	    ngModel.$render = function(value) {
		ck.setData(ngModel.$modelValue);
	    };
	}
    };
} ]);