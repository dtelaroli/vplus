'use strict';

/* Services */

angular.module('myApp.services', [ 'ngResource' ]).value('version', '0.1').

factory('$restService', [ '$resource', 'REST_URL', function($resource, url) {
	return $resource(url + '/:id/:action/:status', {}, {
		find : {
			method : 'GET',
			isArray : true,
			params : {
				action : status,
				status : '@status'
			}
		},
		update : {
			method : 'PUT',
			params : {
				id : '@id'
			}
		},
		remove : {
			method : 'DELETE',
			params : {
				id : '@id'
			}
		}
	});
} ]);