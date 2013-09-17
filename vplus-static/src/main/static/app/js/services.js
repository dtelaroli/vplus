'use strict';

/* Services */

angular.module('myApp.services', [ 'ngResource' ])
.value('version', '0.1')
.constant('STATIC_CONTEXT', '/vp-static')

.factory('$restService', [ '$resource', 'REST_URL', function($resource, URL) {
    return $resource(URL + '/:id/:action/:status', {}, {
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