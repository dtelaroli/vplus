angular.module('VPlus.Config', []);
angular.module('VPlus.Services', ['ngResource']);
angular.module('VPlus.Utils', ['ngGrid', 'ui.bootstrap']);
var VPlus = angular.module('VPlus', ['VPlus.Config', 'VPlus.Services', 'VPlus.Utils', 'ngSanitize']);