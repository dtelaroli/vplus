'use strict';

angular.module('VPlus.Services')
        .factory('Rest', ['$resource', 'URL', function($resource, URL) {
        return $resource(URL + '/:id', {}, {
            find: {
                url: URL + '/status/:status',
                method: 'GET',
                isArray: true,
                params: { status: '@status' }
            },
            update: {
                method: 'PUT',
                params: { id: '@id' }
            },
            remove: {
            	method: 'DELETE',
            	params: { id: '@id' }
            }
        });
    }]);