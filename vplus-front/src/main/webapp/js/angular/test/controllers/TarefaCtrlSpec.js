'use strict';

describe('EsabApp', function() {
	
	beforeEach(module('EsabApp'));
	angular.module('App.Config')
	.constant('URLS', { TAREFA: '/tarefa', TIPO_USUARIO: '/tipo' })
	.constant('PROCESSO_ID', '1');
	
	describe('TarefaCtrl', function() {
		var scope = null;
		beforeEach(inject(function($rootScope, $controller) {
			scope = $rootScope.$new();
			$controller('TarefaCtrl', { 
				$scope: scope,
				PROCESSO_ID: ''
			});
		}));
		
		it('deve abortar ações se PROCESSO_ID vazio', inject(function($controller) {
			expect(scope.tiposUsuario).toBeUndefined();
			expect(scope.tarefas).toBeUndefined();
		}));
	});

	describe('TarefaCtrl', function() {
		var pathTarefa = '/tarefa?action=list.json&id=1';
		var pathTipo = '/tipo?order=descricao';
		var scope = null;
		var $httpBackend = null;
		beforeEach(inject(function($rootScope, $controller, $injector) {
			scope = $rootScope.$new();
			$controller('TarefaCtrl', {
				$scope: scope
			});
			$httpBackend = $injector.get('$httpBackend');	
			$httpBackend.whenGET(pathTarefa).respond([{ id: 10 }]);
			$httpBackend.whenGET(pathTipo).respond([{ id: 20 }]);
		}));
		
		afterEach(function() {
			$httpBackend.verifyNoOutstandingExpectation();
	    	$httpBackend.verifyNoOutstandingRequest();
		});
		
		it('deve configurar sortableOptions', function() {
			expect(scope.sortableOptions).toBeDefined();
			$httpBackend.flush();
		});
		
		it('deve retornar tarefas', function() {
			$httpBackend.expectGET(pathTarefa);
			$httpBackend.expectGET(pathTipo);
			expect(0).toBe(scope.tarefas.length);
			
			$httpBackend.flush();
			expect(10).toBe(scope.tarefas[0].id);
		});
		
		it('deve retornar grupos', function() {
			$httpBackend.expectGET(pathTarefa);
			$httpBackend.expectGET(pathTipo);
			expect(0).toBe(scope.tiposUsuario.length);
			$httpBackend.flush();
			expect(20).toBe(scope.tiposUsuario[0].id);
		});
	});
});