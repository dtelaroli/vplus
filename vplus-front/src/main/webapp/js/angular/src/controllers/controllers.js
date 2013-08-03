'use strict';

angular.module('App')
.controller('CrudCtrl', ['$scope', 'AjaxService', 'OBJ_RELATED', 'fn', function($scope, AjaxService, OBJ_RELATED, fn) {
	if(OBJ_RELATED.id == '') {
		return;
	}
	
	$scope.list = AjaxService.query({ id: OBJ_RELATED.id });
	$scope.editing = [];
	$scope.item = {};
	
	$scope.disabled = function() {
		for(var i in $scope.editing) {
			if($scope.editing[i]) {
				return true;
			}
		}
		return false;
	};
	
	$scope.add = function() {
		var length = $scope.list.length;
		$scope.item = new AjaxService();
		$scope.item[OBJ_RELATED.field] = { id: OBJ_RELATED.id };
		$scope.list.push($scope.item);
		$scope.edit(length);
	};
	
	$scope.edit = function(i) {
		$scope.editing[i] = true;
		$scope.item = $scope.list[i];
	};
	
	$scope.persist = function(i) {
		if($scope.item.id == null) {
			$scope.item.$add();
		}
		else {
			$scope.item.$edit();
		}
		$scope.close(i);
	};
	
	$scope.close = function(i) {
		$scope.editing[i] = false;
	};
	
	$scope.remove = function(i) {
		fn.confirm('Deseja realmente excluir?', function() {
			var item = $scope.list[i];
			if(item.id != null) {
				$scope.list[i].$remove({ id: item.id }, function(result) {
					fn.info('Ítem removido com sucesso');
					$scope.list.splice(i, 1);
				});
			}
			else {
				$scope.list.splice(i, 1);
			}
			$scope.close();
			fn.apply($scope);
		});
	};
}])

.controller('FormaPagtCtrl', ['$scope', 'formas', 'convenios', function($scope, formas, convenios) {
	$scope.formas = formas;
	$scope.convenios = convenios;
	
	$scope.reset = function() {
		$scope.forma = null;
		$scope.convenio = null;
		$scope.valor = {
			total: null,
			totalComDesconto: null,
			parcelaComDesconto: null,
			totalAluno: null,
			parcelaAluno: null,
			totalEmpresa: null,
			parcelaEmpresa: null
		};
	};
	$scope.reset();
	
	$scope.somaTotal = function() {
		return $scope.forma.qtd * $scope.forma.valor;
	};
	$scope.divideTotalComDesconto = function() {
		return $scope.valor.totalComDesconto / $scope.forma.qtd;
	};
	$scope.calcDesconto = function() {
		return $scope.valor.total * $scope.convenio.descontoConvenioAtivo / 100;
	};
	
	$scope.calcValores = function() {
		if($scope.forma) {
			$scope.valor.total = $scope.somaTotal();
			$scope.valor.totalComDesconto = $scope.valor.total;
			if($scope.convenio) {
				$scope.valor.totalComDesconto -= $scope.calcDesconto();
			}
			$scope.valor.parcelaComDesconto = $scope.divideTotalComDesconto();
		}
		else {
			$scope.reset();
		}
		$scope.calcConvenio();
	};
	$scope.calcConvenio = function() {
		if($scope.convenio) {
			$scope.valor.totalAluno = $scope.valor.totalComDesconto * $scope.convenio.percentualAlunoConvenioAtivo / 100;
			$scope.valor.parcelaAluno = $scope.valor.totalAluno / $scope.forma.qtd;
			$scope.valor.totalEmpresa = $scope.valor.totalComDesconto * $scope.convenio.percentualEmpresaConvenioAtivo / 100;
			$scope.valor.parcelaEmpresa = $scope.valor.totalEmpresa / $scope.forma.qtd;
		}
	};
	
	$scope.$watch('forma', function(val) {
		$scope.calcValores();
	});
	$scope.$watch('convenio', function(val) {
		$scope.calcValores();
	});
	$scope.$watch('usaConvenio', function(val) {
		if(val == 'false')
			$scope.convenio = null;
	});
	
}]);