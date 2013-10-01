'use strict';

/* Services */

angular.module('myApp.services', [ 'ngResource' ])
.value('version', '0.1')
.constant('STATIC_CONTEXT', '/vp-static')

.factory('$dataService', ['$rootScope', function($rootScope) {
    var data = {};
    return {	
	get: function(name) {
	    return data[name];
	},
	set: function(name, newData) {
	    data[name] = newData;
	    $rootScope.$broadcast('$' + name + 'Change', newData);
	}
    };
}])

.factory('$restService', [ '$resource', 'URL', function($resource, URL) {
    var rest = function($url) {
	    if($url === undefined) {
		$url = URL + '/:id/:action/:status';
	    }
	    
	    return $resource($url, {}, {
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
	};
    return rest;
} ]);