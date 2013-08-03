'use strict';

angular.module('VPlus.Utils').factory('fn', ['$rootScope', function(scope) {
	var fn = {
		success: function() {
			$('#box').bounceBoxToggle();
			setTimeout(function() {
				$('#box').bounceBoxHide();
			}, 5000);
		},
		
		confirmExec: function(msg, callback, extras) {
			$('#msgCont').text(msg);
			$('#yesButton').removeAttr('onclick');
			$('#yesButton').click(function() {
				$('#box3').bounceBoxHide();
				callback(extras);
				$('#yesButton').unbind('click');
			});
			$('#box3').bounceBoxToggle();
		},
		
		error: function(message) {
			$('#box3').bounceBoxHide();
			$('#box2').bounceBoxToggle();
			$('#error').html(message);
			setTimeout(function() {
				$('#box2').bounceBoxHide();
			}, 10000); 
		},
		
		orderArray: function(data, field) {
			angular.forEach(data, function(item, i) {
				data[i][field] = parseInt(i) + 1;
			});
			return data;
		},
		order: function(data, field, callback) {
			this.orderArray(data, field);
			callback.call();
			this.apply();
		},
		apply: function() {
			if(!scope.$$phase) {
				scope.$apply();
			}
		},
		confirm: function(msg, callback) {
        	this.confirmExec(msg, callback);
        },
        info: function(msg) {
        	this.success();
        },
        getByField: function(data, field, id) {
        	if(id == null) {
        		return null;
        	}
        	if(field == null) {
        		console.error('Field not should be null');
        	}
        	var val = null;
        	angular.forEach(data, function(item, i) {
        		if(item[field] == id) {
        			val = item;
					return;
				}
        	});
        	return val;
		}
	};
	return fn;
}]);