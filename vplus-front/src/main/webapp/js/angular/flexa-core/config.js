'use strict';

angular.module('App.Config', []).config(['$httpProvider', function($httpProvider) {
	var interceptor = ['$rootScope','$q', function(scope, $q) {
		
		function success(response) {
			return response;
		}
		
		function error(response) {
			switch(response.status) {
				case 403:
					window.location.reload();
					break;
					
				case 401:
					AjaxHelper.error('Você não tem permissão para acessar esta área');
					setTimeout(function() {
						window.history.back();
					}, 5000);
					break;
					
				default:
					var msg = '';
					if(response.data.errors) {
						for(var i in response.data.errors) {
							var mes = response.data.errors[i].message;
							var cat = response.data.errors[i].category;
							msg += mes.replace('{field}', cat) + '<br/>';
						}
					}
					else {
						msg = response.data;
					}
					AjaxHelper.error(msg);
					break;
			}
			return $q.reject(response);
		}
		
		return function(promise) {
			return promise.then(success, error);
		};
	}];
	$httpProvider.defaults.headers.common['Accept'] = 'application/json';
	$httpProvider.defaults.headers.common['Content-Type'] = 'application/json';
	//$httpProvider.defaults.headers.common['Content-Type'] = 'application/x-www-form-urlencoded';
	$httpProvider.responseInterceptors.push(interceptor);
}]);
angular.module('App.Directives', []);
angular.module('App.Utils', []);
angular.module('App.Filters', []);
angular.module('App.Services', ['ngResource']);
var App = angular.module('App', ['App.Config', 'App.Directives', 'App.Utils', 'App.Filters', 'App.Services'])
.run(['$rootScope', 'fn', function($scope, fn) {
	$scope.root = { 
		remove: function(i, list) {
			fn.confirm('Deseja realmente excluir?', function() {
				var item = list[i];
				if(item.id != null) {
					list[i].$remove({ id: item.id }, function(result) {
						fn.info('Ítem removido com sucesso');
						list.splice(i, 1);
					});
				}
				else {
					list.splice(i, 1);
				}
				fn.apply($scope);
			});
		}
	};
}]);

angular.module('global', ['App.Config', 'App.Directives', 'App.Utils', 'App.Filters', 'App.Services']);