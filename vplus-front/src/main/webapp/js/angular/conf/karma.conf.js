// Karma configuration
// Generated on Fri Jun 14 2013 17:34:31 GMT-0300 (BRT)


// base path, that will be used to resolve files and exclude
basePath = '..';


// list of files / patterns to load in the browser
files = [
  JASMINE,
  JASMINE_ADAPTER,
  '../jquery-1.7.2.min.js',
  '../jquery.bouncebox.1.0.js',
  'lib/angular/angular.js',
  'lib/angular/angular-mocks.js',
  'lib/angular/angular-resource.js',
  'lib/angular-ui/build/angular-ui.js',
  'flexa-core/config.js',
  'flexa-core/directives.js',
  'flexa-core/factories.js',
  'flexa-core/filters.js',
  'src/**/*.js',
  'test/**/*Spec.js'
];


// list of files to exclude
exclude = [
  
];


// test results reporter to use
// possible values: 'dots', 'progress', 'junit'
reporters = ['progress'];


// web server port
port = 9876;


// cli runner port
runnerPort = 9100;


// enable / disable colors in the output (reporters and logs)
colors = true;


// level of logging
// possible values: LOG_DISABLE || LOG_ERROR || LOG_WARN || LOG_INFO || LOG_DEBUG
logLevel = LOG_INFO;


// enable / disable watching file and executing tests whenever any file changes
autoWatch = true;


// Start these browsers, currently available:
// - Chrome
// - ChromeCanary
// - Firefox
// - Opera
// - Safari (only Mac)
// - PhantomJS
// - IE (only Windows)
browsers = ['PhantomJS'];


// If browser does not capture in given timeout [ms], kill it
captureTimeout = 20000;


// Continuous Integration mode
// if true, it capture browsers, run tests and exit
singleRun = false;
