'use strict';

function routes($routeProvider) {

    $routeProvider.when('/', {
	templateUrl : 'grid.tpl.html',
	controller : 'GridCtrl'
    }).when('/add', {
	templateUrl : 'form.tpl.html',
	controller : 'AddCtrl'
    }).when('/:id', {
	templateUrl : 'form.tpl.html',
	controller : 'EditCtrl'
    }).when('/:id/remove', {
	templateUrl : 'grid.tpl.html',
	controller : 'DeleteCtrl'
    }).otherwise({
	redirectTo : '/'
    });

}

angular.module('myApp', ['myApp.filters', 'myApp.services', 'myApp.directives', 'myApp.controllers']);