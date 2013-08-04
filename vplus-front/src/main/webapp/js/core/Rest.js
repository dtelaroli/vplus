'use strict';

angular.module('VPlus.Services')
.factory('Rest', ['$resource', 'URL', function($resource, URL) {
	var statusUrl = URL + '/status/:status';
	return $resource(URL, {}, {
		query : {
			method : 'GET',
			isArray : true
		},
		find: {
			url: statusUrl,
			method: 'GET',
			isArray: true,
			params: { status: '@status' }
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