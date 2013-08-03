'use strict';

angular.module('App.Directives')
        .directive('uiSortableEnable', function() {
            return {
        link: function(scope, iElement, iAttrs) {
            scope.$watch(iAttrs.uiSortableEnable, function(value) {
                value ? iElement.sortable('enable') : iElement
                        .sortable('disable');
            });
        }
    };
})
        .directive('float', function() {
    var FLOAT_REGEXP = /^\-?\d+((\.|\,)\d+)?$/;
    return {
        require: 'ngModel',
        link: function(scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function(viewValue) {
                if (FLOAT_REGEXP.test(viewValue)) {
                    ctrl.$setValidity('float', true);
                    return parseFloat(viewValue.replace(',', '.'));
                } else {
                    ctrl.$setValidity('float', false);
                    return undefined;
                }
            });
        }
    };
})
        .directive('charLimit', function() {
    return {
        restrict: 'A',
        link: function($scope, $element, $attributes) {
            var limit = $attributes.charLimit;

            $element.bind('keyup', function(event) {
                var element = $element.parent().parent();

                element.toggleClass('warning', limit - $element.val().length <= 10);
                element.toggleClass('error', $element.val().length > limit);
            });

            $element.bind('keypress', function(event) {
                // Once the limit has been met or exceeded, prevent all keypresses from working
                if ($element.val().length >= limit) {
                    // Except backspace
                    if (event.keyCode !== 8) {
                        event.preventDefault();
                    }
                }
            });
        }
    };
});