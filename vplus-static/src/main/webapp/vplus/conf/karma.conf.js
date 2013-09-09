// Karma configuration
// Generated on Tue Aug 06 2013 21:22:44 GMT-0300 (BRT)

module.exports = function(config) {
	config.set({

		// base path, that will be used to resolve files and exclude
		basePath : '..',

		// frameworks to use
		frameworks : [ 'jasmine' ],

		// list of files / patterns to load in the browser
		files : [
		         'vendor/angular/angular.min.js',
		         'vendor/angular/angular-mocks.js',
		         'vendor/angular/angular-resource.min.js',
		         'vendor/angular/angular-sanitize.min.js',
		         'vendor/angular-ui/ng-grid/lib/jquery-1.9.1.js',
		         'vendor/angular-ui/ng-grid/ng-grid-2.0.7.min.js',
		         'vendor/angular-ui/bootstrap/ui-bootstrap-tpls-0.5.0.js',
		         'src/VPlusNamespace.js',
		         'src/*.js',
		         'test/**/*Spec.js' 
		],

		// list of files to exclude
		exclude : [

		],

		// test results reporter to use
		// possible values: 'dots', 'progress', 'junit', 'growl', 'coverage'
		reporters : [ 'progress' ],

		// web server port
		port : 9876,

		// enable / disable colors in the output (reporters and logs)
		colors : true,

		// level of logging
		// possible values: config.LOG_DISABLE || config.LOG_ERROR ||
		// config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
		logLevel : config.LOG_INFO,

		// enable / disable watching file and executing tests whenever any file
		// changes
		autoWatch : true,

		// Start these browsers, currently available:
		// - Chrome
		// - ChromeCanary
		// - Firefox
		// - Opera
		// - Safari (only Mac)
		// - PhantomJS
		// - IE (only Windows)
		browsers : [ 'PhantomJS' ],

		// If browser does not capture in given timeout [ms], kill it
		captureTimeout : 60000,

		// Continuous Integration mode
		// if true, it capture browsers, run tests and exit
		singleRun : false
	});
};
