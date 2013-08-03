angular.module('EsabApp', ['ui', 'global'])
.controller('TarefaCtrl', ['$scope', '$http', '$filter', 'fn', 'Tarefa', 'TipoUsuario', 'PROCESSO_ID',
	function($scope, $http, $filter, fn, Tarefa, TipoUsuario, PROCESSO_ID) {
		if(PROCESSO_ID == '') {
			return;
		}
		
		$scope.tarefas = Tarefa.all({ id: PROCESSO_ID });
		$scope.tiposUsuario = TipoUsuario.all();
		$scope.editing = [];
		$scope.selectedTar = {};
		$scope.selectedGr = {};
		$scope.disabled = false;
		
		$scope.sortableOptions = {
			disabled: $scope.disabled || $scope.tiposUsuario.length > 1,
			revert: true,
			distance: 8,
			stop: function() {
				fn.order($scope.tarefas, 'ordem', function() {
					angular.forEach($scope.tarefas, function(tarefa, i) {
						tarefa.$edit();
					});
				});
			}
		};
		
		$scope.getTipoUsuario = function(id, i) {
			if(id == null) {
				$scope.tarefas[i].grupoAtendente = null;
				return null;
			}
			return $filter('getItemById')($scope.tiposUsuario, id);
		};
		
		$scope.getTarefa = function(id, i) {
			if(id == null) {
				$scope.tarefas[i].tarefaIndeferido = null;
				fn.apply();
				return null;
			}
			return $filter('getItemById')($scope.tarefas, id);
		};
		
		$scope.getSufixPrazo = function(prazo) {
			return prazo > 1 ? 'dias' : 'dia';
		};
		
		$scope.add = function() {
			var length = $scope.tarefas.length;
			var tarefa = new Tarefa();
			tarefa.ordem = $filter('getNextInt')($scope.tarefas, 'ordem');
			tarefa.processo = $scope.processo;
			$scope.tarefas.push(tarefa);
			$scope.edit(length);
		};
		
		$scope.edit = function(i) {
			$scope.editing[i] = true;
			$scope.disabled = true;
			$scope.selectedTar = $scope.tarefas[i].tarefaIndeferido || {};
			$scope.selectedGr = $scope.tarefas[i].grupoAtendente || {};
		};

		$scope.persist = function(i) {
			var tarefa = $scope.tarefas[i];
			tarefa.tarefaIndeferido = $filter('getItemById')($scope.tarefas, $scope.selectedTar.id);
			tarefa.grupoAtendente = $filter('getItemById')($scope.tiposUsuario, $scope.selectedGr.id);
			
			if(tarefa.id == null) {
				tarefa.$add();
			}
			else {
				tarefa.$edit();
			}
			$scope.close(i);
		};
		
		$scope.close = function(i) {
			$scope.editing[i] = false;
			$scope.disabled = false;
		};
		
		$scope.remove = function(i) {
			fn.confirm('Deseja realmente excluir?', function() {
				var tarefa = $scope.tarefas[i];
				if(tarefa.id != null) {
					tarefa.$remove({ id: tarefa.id }, function(result) {
						fn.info('Ítem removido com sucesso');
						$scope.tarefas.splice(i, 1);
					});
				}
				else {
					$scope.tarefas.splice(i, 1);
				}
				$scope.close();
				fn.apply($scope);
			});
		};
	}
]);