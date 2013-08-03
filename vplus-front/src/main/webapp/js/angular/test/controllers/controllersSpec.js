'use strict';

describe('App', function() {

	beforeEach(angular.mock.module('App', function($provide) {
		$provide.constant('URL', '/:id/:action');
		$provide.constant('OBJ_RELATED', { id: 222, field: 'name' });
	}));
	
	var get = '/222/list.json', $httpBackend = null, response = [{ id: 1 }];
	beforeEach(inject(function($rootScope, $controller, $injector) {
		$httpBackend = $injector.get('$httpBackend');
		$httpBackend.whenGET(get).respond(response);
	}));
	
	afterEach(function() {
		$httpBackend.verifyNoOutstandingExpectation();
    	$httpBackend.verifyNoOutstandingRequest();
	});
	
	function flush() {
		$httpBackend.flush();
	}
	
	describe('CrudCtrl', function() {
		var scope = null;
		beforeEach(inject(function($rootScope, $controller, $injector) {
			scope = $rootScope.$new();
			$controller('CrudCtrl', {
				$scope: scope
			});
		}));
		
		it('should load root list', function() {
			expect(App.CrudCtrl).not.toBeNull();
			
			$httpBackend.expectGET(get);
			flush();
			
			expect(scope.list[0].id).toEqual(response[0].id);
		});
		
		it('should open form to edit', function() {
			flush();
			
			scope.edit(0);
			expect(scope.disabled()).toEqual(true);
			expect(scope.editing[0]).toEqual(true);
			expect(scope.item.id).toBe(1);
		});
		
		it('should add new item in grid', function() {
			flush();
			scope.item = null;
			var length = scope.list.length;
			expect(scope.list.length).toBe(length);
			scope.add();
			expect(scope.list.length).toBe(length + 1);
			expect(scope.disabled()).toEqual(true);
			expect(scope.editing[1]).toEqual(true);
			expect(scope.item).toBeDefined();
			expect(scope.item['name'].id = 222);
		});
		
		it('should close form', function() {
			flush();
			
			var index = 0;
			scope.editing[index] = true;
			expect(scope.disabled()).toEqual(true);
			expect(scope.editing[index]).toEqual(true);
			
			scope.close(index);
			expect(scope.disabled()).toEqual(false);
			expect(scope.editing[index]).toEqual(false);
		});
		
	});
	
});