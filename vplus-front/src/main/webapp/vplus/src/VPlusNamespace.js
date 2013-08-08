angular.module('VPlus.Config', []);
angular.module('VPlus.Services', ['ngResource']);
angular.module('VPlus.Utils', ['ngGrid', 'ui.bootstrap']);
angular.module('VPlus.Ctrls', ['VPlus.Config', 'VPlus.Services', 'VPlus.Utils']);
angular.module('VPlus', ['VPlus.Ctrls']);