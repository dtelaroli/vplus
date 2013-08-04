angular.module('VPlus.Config', []).config(['$httpProvider', function($httpProvider) {
	var interceptor = ['$rootScope','$q', function(scope, $q) {
		
		function success(response) {
			return response;
		}
		
		function error(response) {
			var msg = '';
			switch(response.status) {
				case 403:
					//window.location.reload();
					break;
					
				case 401:
					console.error('Você não tem permissão para acessar esta área');
					setTimeout(function() {
						//window.history.back();
					}, 5000);
					break;
					
				default:
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
//					console.error(msg);
					break;
			}
			console.error(response.status, msg);
			return $q.reject(response);
		}
		
		return function(promise) {
			return promise.then(success, error);
		};
	}];
	$httpProvider.defaults.headers.common['Accept'] = 'application/json';
	$httpProvider.defaults.headers.common['Content-Type'] = 'application/json';
	$httpProvider.responseInterceptors.push(interceptor);
}]);

angular.module('VPlus.Directives', []);
angular.module('VPlus.Filters', []);
angular.module('VPlus.Services', ['ngResource']);
angular.module('VPlus.Utils', ['ngGrid', 'ui.bootstrap', 'ui.utils']);

angular.module('VPlus', ['VPlus.Config', 'VPlus.Directives', 'VPlus.Utils', 'VPlus.Filters', 'VPlus.Services'])
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