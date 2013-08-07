'use strict';

angular.module('VPlus.Services')
.factory('Rest', ['$resource', 'URL', function($resource, URL) {
	var statusUrl = URL + '/status/:status';
	return $resource(URL, {}, {
		find: {
			url: statusUrl,
			method: 'GET',
			isArray: true,
			params: { status: '@status' }
		},
		update : {
			method : 'PUT',
			params: { id: '@id' }
		}
	});
}]);