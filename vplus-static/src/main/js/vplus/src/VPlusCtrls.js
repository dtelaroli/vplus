'use strict';

angular.module('VPlus').controller('CrudCtrl', ['$scope', '$timeout', '$dialog', 'Rest', 'menus', '$location',
    function($scope, $timeout, $dialog, Rest, menus, $location) {
        var _utils = {
            createItem: function() {
            	return angular.extend($scope.model, new Rest());
            },
            remove: function(i) {
                $scope.list[i].$delete({
                    id: $scope.list[i].id
                }, function() {
                    $scope.list.splice(i, 1);
                    _utils.reset();
                });
            },
            reset: function() {
                $scope.model = {};
                $scope.tabs[0].active = true;
                $scope.alerts = [{type: 'success', msg: 'All done!'}];
                $timeout(function() {
                    $scope.alerts = [];
                }, 8000);
            }
        };

        $scope.status = 'All';
        $scope.list = Rest.query();
        $scope.model = {};
        $scope.tabs = [{active: true}];
        $scope.menus = menus;

        $scope.add = function() {
            var model = _utils.createItem();
            model.$save({}, function(response) {
                $scope.list.push(response);
                _utils.reset();
            });
        };

        $scope.edit = function(i) {
            $scope.model = $scope.list[i];
            $scope.tabs[2].active = true;
        };

        $scope.update = function() {
            $scope.model.$update({}, function() {
                _utils.reset();
            });
        };

        $scope.remove = function(i) {
            var msgbox = $dialog.messageBox('Delete Item',
                    'Are you sure?', [{
                    label: 'Yes, I\'m sure',
                    result: true
                }, {
                    label: 'Nope',
                    result: false
                }]);
            msgbox.open().then(function(result) {
                if (result) {
                    _utils.remove(i);
                }
            });
        };

        $scope.reset = function() {
            _utils.reset();
        };

        $scope.closeAlert = function(i) {
            $scope.alerts.splice(i, 1);
        };

        $scope.getActive = function(i) {
            return $location.path() === $scope.menus[i].url ? 'active' : '';
        };

        $scope.injectTabs = function(scope) {
            $scope.tabs = scope.tabs;
        };

        $scope.filter = function(status) {
            if (status === 'All') {
                $scope.list = Rest.query();
            }
            else {
                Rest.find({status: status}, function(response) {
                    $scope.list = response;
                });
            }
        };

    }]);