'use strict';

describe('VPlus Crud Controller', function() {

    beforeEach(function() {
        this.addMatchers({
            toEqualData: function(expected) {
                return angular.equals(this.actual, expected);
            }
        });
    });

    beforeEach(angular.mock.module('VPlus.Ctrls', function($provide) {
        $provide.constant('URL', '/url/:id');
    }));

    describe('CrudCtrl', function() {
        var scope, dialog, ctrl, $httpBackend, $timeout;

        beforeEach(angular.mock.module('ui.bootstrap.dialog'));

        beforeEach(inject(function(_$dialog_, _$httpBackend_, $rootScope, $controller, _$timeout_) {
            $httpBackend = _$httpBackend_;
            $httpBackend
                    .expectGET('/url')
                    .respond([{id: 1, name: 'Test1'}, {id: 2, name: 'Test2'}]);

            dialog = _$dialog_;
            dialog.messageBox = function(title, msg, btns) {
                return {
                    open: function() {
                        return {
                            then: function(callback) {
                                callback(true);
                            }
                        };
                    }
                };
            };

            $timeout = _$timeout_;
            scope = $rootScope.$new();
            ctrl = $controller('CrudCtrl', {
                $scope: scope,
                $dialog: dialog
            });
        }));

        it('should return 2 items from resource on startup', function() {
            expect(scope.list).toEqualData([]);
            $httpBackend.flush();

            expect(scope.list)
                    .toEqualData([{id: 1, name: 'Test1'}, {id: 2, name: 'Test2'}]);
        });

        it('should save new item', function() {
            $httpBackend
                    .expectPOST('/url', {name: 'Test1'})
                    .respond({name: 'Test1'});
            expect(scope.list.length).toBe(0);

            scope.model.name = 'Test1';
            scope.add();
            $httpBackend.flush();

            expect(scope.list.length).toBe(3);
            expect(scope.model).toEqualData({});
        });

        it('should update existing item', function() {
            expect(scope.list.length).toBe(0);
            $httpBackend.flush();

            scope.model = scope.list[1];
            scope.model.name = 'Edited';
            $httpBackend
                    .expectPUT('/url/2', {id: 2, name: 'Edited'})
                    .respond({id: 1, name: 'Edited'});

            scope.update();
            $httpBackend.flush();

            expect(scope.list.length).toBe(2);
            expect(scope.model).toEqualData({});
            expect(scope.list[1].name).toBe('Edited');
        });

        it('should remove item', function() {
            expect(scope.list.length).toBe(0);
            $httpBackend.flush();

            $httpBackend
                    .expectDELETE('/url/2')
                    .respond({message: 'ok'});

            scope.remove(1);
            $httpBackend.flush();

            expect(scope.list.length).toBe(1);
            expect(scope.model).toEqualData({});
        });

        it('should remove message item', function() {
            scope.alerts.push({id: 1});
            expect(scope.alerts.length).toBe(1);

            scope.closeAlert(0);

            expect(scope.alerts.length).toBe(0);
        });
        
        it('should reset data', function() {
            var i = {id:1};
            scope.alerts.push(i);
            scope.model = i;
            scope.reset();
            expect(scope.alerts.length).toBe(1);

            $timeout.flush();
            expect(scope.model.id).toBeUndefined();
            expect(scope.alerts.length).toBe(0);
        });

    });

});