'use strict';

angular.module('VPlus.Ctrls').controller('CrudCtrl', ['$scope', '$timeout', '$dialog', 'Rest', 'TEMPLATES',
    function($scope, $timeout, $dialog, Rest, TEMPLATES) {
        var _utils = {
            createItem: function() {
                var object = new Rest();
                for (var attr in $scope.model) {
                    object[attr] = $scope.model[attr];
                }
                return object;
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
        
        $scope.list = Rest.query();
        
        $scope.model = {};
        
        $scope.tabs = [
            { title: 'Data grid', content: TEMPLATES.grid },
            { title: 'Add new', content: TEMPLATES.add },
            { title: 'Edit item', content: TEMPLATES.edit, disabled: true }
        ];

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

        $scope.closeAlert = function(index) {
            $scope.alerts.splice(index, 1);
        };
    }]);