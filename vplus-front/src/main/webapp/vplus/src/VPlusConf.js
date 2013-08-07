angular.module('VPlus.Config', []);


angular.module('VPlus.Config').config(['$httpProvider', function($httpProvider) {
	var interceptor = ['$rootScope','$q', function(scope, $q) {
		
		scope.alerts = [];
		function success(response) {
			return response;
		}
		
		function error(response) {
			switch(response.status) {
				case 403:
				case 401:
					scope.alerts.push({ type: 'error', msg: response.data });
					setTimeout(function() {
						window.location.reload();
					}, 5000);
					break;
					
				default:
					if(response.data.errors) {
						for(var i in response.data.errors) {
							var mes = response.data.errors[i].message;
							var cat = response.data.errors[i].category;
							var msg = mes.replace('{field}', cat) + '<br/>';
							scope.alerts.push({ type: 'error', msg: msg });
						}
					}
					else {
						scope.alerts.push({ type: 'error', msg: response.data });
					}
					break;
			}
			return $q.reject(response);
		}
		
		return function(promise) {
			return promise.then(success, error);
		};
	}];
	$httpProvider.responseInterceptors.push(interceptor);
	$httpProvider.defaults.headers.common['Accept'] = 'application/json';
	$httpProvider.defaults.headers.common['Content-Type'] = 'application/json';
}]);

angular.module('VPlus.Services', ['ngResource']);
angular.module('VPlus.Utils', ['ngGrid', 'ui.bootstrap']);
angular.module('VPlus.Ctrls', ['VPlus.Config', 'VPlus.Services', 'VPlus.Utils']);
angular.module('VPlus', ['VPlus.Ctrls']);