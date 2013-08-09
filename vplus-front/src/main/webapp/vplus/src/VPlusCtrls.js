'use strict';

angular.module('VPlus.Ctrls').controller('CrudCtrl', ['$scope', '$dialog', 'Rest', 
    function($scope, $dialog, Rest) {
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
                });
            }
        };

        $scope.template = 'vplus/tmpl/partial-bar.html';
        $scope.model = {};
        $scope.list = Rest.query();
        $scope.alerts = [];
        $scope.grid = true;
        
        $scope.add = function() {
            var model = _utils.createItem();
            model.$save({}, function(response) {
                $scope.list.push(response);
            });
            $scope.model = {};
            $scope.grid = true;
        };

        $scope.edit = function(i) {
            $scope.model = $scope.list[i];
            $scope.grid = false;
        };
        
        $scope.update = function() {
            $scope.model.$update();
            $scope.model = {};
            $scope.grid = true;
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

        $scope.closeAlert = function(index) {
            $scope.alerts.splice(index, 1);
        };
    }]);