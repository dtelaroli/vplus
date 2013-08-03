'use strict';

angular.module('VPlus.Services')
.factory('Rest', ['$resource', 'URL', function($resource, URL) {
	return $resource(URL, {}, {
		query : {
			method : 'GET',
			isArray : true
		},
		add: {
			method : 'POST'
		},
		edit : {
			method : 'PUT',
			params: { id: '@id' }
		},
		remove: {
			method : 'DELETE',
			params: { id: '@id' }
		}
	});
}]);