'use strict';

angular.module('App.Services')
.factory('Tarefa', ['$resource', 'URLS', function($resource, URLS) {
	return $resource(URLS.TAREFA, {}, {
		all : {
			method : 'GET',
			isArray : true,
			params: { action: 'list.json', id: '@id' }
		},
		add: {
			method: 'POST',
			params: { _method: 'POST' }
		},
		edit : {
			method : 'PUT',
			params: {  _method: 'PUT', id: '@id', action: 'edit' }
		},
		remove: {
			method : 'DELETE',
			params: { _method: 'DELETE', id: '@id' }
		},
		saveAll: {
			method: 'POST',
			params: { action: 'saveAll' }
		}
	});
}]);