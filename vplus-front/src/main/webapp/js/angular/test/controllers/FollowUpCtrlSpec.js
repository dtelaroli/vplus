'use strict';

describe('App', function() {
	
	var provide = null;
	beforeEach(angular.mock.module('App', function($provide) {
		$provide.constant('URL', '/:id/:action');
		$provide.constant('OBJ_RELATED', { id: 222 });
		$provide.constant('USER', { id: 20 });
		provide = $provide;
	}));
	
	describe('FollowUpCtrl', function() {
		var $httpBackend = null, scope = null;
		beforeEach(inject(function($rootScope, $controller, $injector) {
			scope = $rootScope.$new();
			$controller('FollowUpCtrl', {
				$scope: scope
			});
			$httpBackend = $injector.get('$httpBackend');
			$httpBackend.whenGET(/.*/).respond([{ id: 1 }]);
			$httpBackend.whenPOST(/.*/).respond({ id: 10, pessoaJuridica: { id : 222 }});
			$httpBackend.whenPUT(/.*/).respond({ id: 10 });
			$httpBackend.whenDELETE(/.*/).respond({});
		}));
		
		function expectGetInit() {
			$httpBackend.expectGET('/222/list.json');
			$httpBackend.flush();
			expect(scope.list.length).toBe(1);
		}
		
		it('init', function() {
			expectGetInit();
		});
		
		it('persist invoke add', function() {
			scope.persist();
			
			$httpBackend.expectPOST(/.*/);
			$httpBackend.flush();
		});
		
		it('persist invoke edit', function() {
			expectGetInit();
			scope.item = scope.list[0];
			scope.persist();
			
			$httpBackend.expectPUT(/.*/);
			$httpBackend.flush();
		});
		
		it('add method', function() {
			expectGetInit();
			scope.item.comentario = 'comment';
			scope.add();
			
			var anterior = scope.list[0].id;
			$httpBackend.expectPOST(/.*/);
			$httpBackend.flush();
			expect(scope.list.length).toBe(2);
			expect(scope.list[0].id).toBe(10);
			expect(scope.list[1].id).toBe(anterior);
			expect(scope.item).toEqual({});
		});
		
		it('newItem method', function() {
			scope.item.comentario = 'comment';
			var item = scope.newItem();
			expect(item.pessoaJuridica.id).toBe(222);
			expect(item.comentario).toBe('comment');
			expect(item.id).toBeUndefined();
		});
		
		it('edit method', function() {
			expectGetInit();
			scope.item = scope.list[0];
			scope.edit();
			
			$httpBackend.expectPUT(/.*/);
			$httpBackend.flush();
			expect(scope.list[0].id).toBe(10);
			expect(scope.item).toEqual({});
		});
		
		it('saveText method incluir', function() {
			expect(scope.getSaveText()).toBe('Incluir');
		});
		
		it('saveText method editar', function() {
			scope.item = { id: 1 };
			expect(scope.getSaveText()).toBe('Salvar Alteração');
		});
		
		it('startEdit', function() {
			expectGetInit();
			
			scope.startEdit(0);
			expect(scope.item.id).toBe(1);
		});
		
		it('editing method to be true', function() {
			scope.item = { id: 1 };
			expect(scope.editing()).toBe(true);
		});
		
		it('editing method to be true', function() {
			expect(scope.editing()).toBe(false);
		});
		
		function addZero(number) {
			return number < 10 ? '0' + number : number;
		}
		
		function getDate() {
			var d = new Date();
			var string = addZero(d.getDate()) + '/';
			string += addZero(d.getMonth() + 1) + '/';
			string += d.getFullYear() + ' ';
			string += addZero(d.getHours() + 1) + ':'; 
			string += addZero(d.getMinutes());
			return string;
		}
		
		function getDateMinus1() {
			var d = new Date();
			var string = addZero(d.getDate() - 1) + '/';
			string += addZero(d.getMonth() + 1) + '/';
			string += d.getFullYear() + ' ';
			string += addZero(d.getHours()) + ':'; 
			string += addZero(d.getMinutes());
			return string;
		}
		
		it('not editing when date is after of 24h', function() {
			expect(scope.enableEdit(getDateMinus1(), 20)).toBe(false);
		});
		
		it('editing when date is after of 24h', function() {
			expect(scope.enableEdit(getDate(), 20)).toBe(true);
		});
		
		it('isOwner true when user diff', function() {
			expect(scope.isOwner(20)).toBe(true);
		});
		
		it('isOwner true when user diff', function() {
			expect(scope.isOwner(21)).toBe(false);
		});
		
		it('not editing when is not owner', function() {
			expect(scope.enableEdit(getDate(), 21)).toBe(false);
		});
		
		it('editing when is not owner', function() {
			expect(scope.enableEdit(getDate(), 20)).toBe(true);
		});
		
		it('convert date string', function() {
			var date = new Date(2013, 5 - 1, 21, 1, 10, 0);
			expect(scope.parseDate('21/05/2013 01:10')).toEqual(date);
		});
		
	});
});