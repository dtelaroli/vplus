'use strict';

angular.module('App.Services')
.factory('AjaxService', ['$resource', 'URL', function($resource, URL) {
	return $resource(URL, {}, {
		query : {
			method : 'GET',
			isArray : true,
			params: { action: 'list.json', id: '@id' }
		},
		add: {
			method : 'POST'
		},
		edit : {
			method : 'PUT',
			params: { action: 'edit', id: '@id' }
		},
		remove: {
			method : 'DELETE',
			params: { id: '@id' }
		}
	});
}]);