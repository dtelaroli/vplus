'use strict';

var App = angular.module('App')
.controller('FollowUpCtrl', ['$scope', 'OBJ_RELATED', 'USER', 'AjaxService', 'fn', 
                             function($scope, RELATED, USER, AjaxService, fn) {
	$scope.list = AjaxService.query({ id: RELATED.id });
	$scope.item = {};
	
	$scope.editing = function() {
		return typeof $scope.item.id == 'number';
	};
	
	$scope.enableEdit = function(date, userId) {
		function addDay(date) {
			date.setDate(date.getDate() + 1);
			return date;
		}
		return $scope.isOwner(userId) && 
			addDay($scope.parseDate(date)).getTime() > new Date().getTime();
	};
	
	$scope.parseDate = function(string) {
		function removeZero(number) {
			return number.replace(/0([1-9])/, '$1');
		}
		
		var mask = /^(\d{2})\/(\d{2})\/(\d{4})\s(\d{2}):(\d{2})$/;
		var day = removeZero(string.replace(mask, '$1'));
		var month = removeZero(string.replace(mask, '$2'));
		var year = string.replace(mask, '$3');
		var hour = removeZero(string.replace(mask, '$4'));
		var min = removeZero(string.replace(mask, '$5'));
		var date = new Date(year, month - 1, day, hour, min);
		return date;
	};
	
	$scope.isOwner = function(id) {
		return id == USER.id;
	};
	
	$scope.persist = function() {
		if(typeof $scope.item.id == 'number') {
			$scope.edit();
		}
		else {
			$scope.add();
		}
	};
	
	$scope.newItem = function() {
		var item = new AjaxService();
		item.pessoaJuridica = { id: RELATED.id };
		item.comentario = $scope.item.comentario;
		return item;
	};
	
	$scope.add = function() {
		$scope.item = $scope.newItem();
		$scope.item.$add(function(response) {
			$scope.list.unshift(response);
			$scope.item = {};
		});
	};
	
	$scope.edit = function() {
		$scope.item.$edit();
		$scope.item = {};
	};
	
	$scope.startEdit = function(index) {
		$scope.item = $scope.list[index];
	};
	
	$scope.getSaveText = function() {
		return $scope.editing() ? 'Salvar Alteração' : 'Incluir';
	};
	
	$scope.remove = function(i) {
		$scope.root.remove(i, $scope.list);
	};
	
}]);