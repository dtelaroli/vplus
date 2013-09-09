'use strict';

angular.module('VPlus.Config')
        .config(['$httpProvider', function($httpProvider) {

        var interceptor = ['$rootScope', '$q', function(scope, $q) {
                scope.alerts = [];

                function success(response) {
                    return response;
                }

                function error(response) {
                    var err = parseError(response);
                    switch (response.status) {
                        case 404:
                            err = 'Url not found (404)';
                            scope.alerts = [{type: 'error', msg: err}];
                            break;

                        case 403:
                        case 401:
                            err += ' (Permission)';
                            scope.alerts = [{type: 'error', msg: err}];
                            break;

                        default:
                            if (response.data.errors) {
                                scope.alerts = [];
                                for (var i in response.data.errors) {
                                    var mes = response.data.errors[i].message;
                                    var cat = response.data.errors[i].category;
                                    scope.alerts.push({type: 'error', msg: cat + ' ' + mes});
                                }
                            }
                            else {
                                scope.alerts = [{type: 'error', msg: err}];
                            }
                            break;
                    }
                    return $q.reject(response);
                }

                function parseError(response) {
                    var message = 'An unknown error occurred';
                    if (response.data !== '') {
                        message = response.data;
                    }
                    return message;
                }

                return function(promise) {
                    return promise.then(success, error);
                };
            }];
        $httpProvider.responseInterceptors.push(interceptor);
        $httpProvider.defaults.headers.common['Accept'] = 'application/json';
        $httpProvider.defaults.headers.common['Content-Type'] = 'application/json';
    }]);