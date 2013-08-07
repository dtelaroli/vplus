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

angular.module('VPlus.Services', ['ngResource']);
angular.module('VPlus.Utils', ['ngGrid', 'ui.bootstrap']);
angular.module('VPlus.Ctrls', ['VPlus.Config', 'VPlus.Utils', 'VPlus.Services']);

angular.module('VPlus', ['VPlus.Ctrls']);