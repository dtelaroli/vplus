'use strict';

angular.module('VPlus.Ctrls').run(['$rootScope', 'Rest', '$dialog', function($scope, Rest, $dialog) {
	var utils = {
		createItem: function() {
			var object = new Rest();
			var copy = $scope.item.constructor();
		    for (var attr in $scope.item) {
		        if ($scope.item.hasOwnProperty(attr)) object[attr] = $scope.item[attr];
		    }
			return object;
		},
		remove: function(i) {
			$scope.list[i].$delete({ id: $scope.list[i].id }, function() {
				$scope.list.splice(i, 1);
			});
		}
	};
	
	$scope.item = {};
	$scope.list = Rest.query();
	
	$scope.add = function() {
		var item = utils.createItem();
		item.$save({}, function(response) {
			$scope.list.push(response);
		});
		$scope.item = {};
	};
	
	$scope.edit = function(i) {
		$scope.list[i].$update();
	};
	
	$scope.remove = function(i) {
		var msgbox = $dialog.messageBox('Delete Item', 'Are you sure?', [{label:'Yes, I\'m sure', result: true},{label:'Nope', result: false}]);
        msgbox.open().then(function(result){
            if(result) {
            	utils.remove(i);
            }
        });
	};
	
}])
.controller('CrudCtrl', ['$scope', function() {
	
}]);