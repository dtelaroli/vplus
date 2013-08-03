'use strict';

angular.module('App.Filters').filter('getNextInt', [function() {
	return function(data, field) {
		var max = 0;
		angular.forEach(data, function(obj, i) {
			var order = parseInt(data[i][field]);
			if(order > max) {
				max = order;
			};
		});
		return max + 1;
	};
}])

.filter('getItemByField', ['fn', function(fn) {
	return function(data, field, id) {
		return fn.getByField(data, field, id);
	};
}])

.filter('getItemById', ['fn', function(fn) {
	return function(data, id) {
		return fn.getByField(data, 'id', id);
	};
}])

.filter('getListEscape', [function() {
	function scape(list, id, field, i) {
		list.splice(i, 1);
		for(var j in list) {
			var relId = getField(list[j], field);
			if(relId == id) {
				return scape(list, id, field, j);
			}
		}
		return list;
	};
	
	function getField(val, property) {
		var split = property.split('.');
		for(var j in split) {
			if(val[split[j]]) {
				val = val[split[j]];
			}
		}
		return val;
	};
	
	return function(data, field, i) {
		var id = data[i].id;
		return scape(angular.copy(data), id, field, i);
	};
}]);