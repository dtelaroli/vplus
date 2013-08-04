<!DOCTYPE html>
<html lang="en">
<head>
<title>Index</title>

<script type="text/javascript" src="/vplus-web/js/vendor/ng-grid//lib/jquery-1.9.1.js"></script>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-rc1/css/bootstrap.min.css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0-rc1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/vplus-web/js/vendor/angular/angular.min.js"></script>
<script type="text/javascript" src="/vplus-web/js/vendor/angular/angular-resource.min.js"></script>
<script type="text/javascript" src="/vplus-web/js/vendor/bootstrap-custom/ui-bootstrap-custom-0.4.0.min.js"></script>
<script type="text/javascript" src="/vplus-web/js/vendor/bootstrap-custom/ui-bootstrap-custom-tpls-0.4.0.min.js"></script>
<script type="text/javascript" src="/vplus-web/js/vendor/ng-grid/ng-grid-2.0.7.min.js"></script>
<link type="text/css" rel="stylesheet" href="/vplus-web/js/vendor/ng-grid/ng-grid.min.css" />

<script type="text/javascript" src="/vplus-web/js/core/Conf.js"></script>
<script type="text/javascript" src="/vplus-web/js/angular/flexa-core/factories.js"></script>
<script type="text/javascript" src="/vplus-web/js/core/Rest.js"></script>
<script type="text/javascript">
	angular.module('VPlus.Config').constant('URL', '/vplus-web/my/:id');
	angular.module('VPlus').controller('MyCtrl', [
		'$scope',
		'Rest',
		function($scope, Rest) {

			var btnSave = '<div class="ngCellText"><button data-ng-click="save(row.rowIndex)" class="btn btn-primary btn-mini btn-margin">Save</button>';
			var btnDel = '<button data-ng-click="remove(row.rowIndex)" class="btn btn-primary btn-mini btn-margin">Remove</button></div>';
			var sel = '<select data-ng-model="list[row.rowIndex].status"><option value="ACTIVE">Active</option><option value="INACTIVE">Inactive</option><option value="REMOVED">Removed</option></select>';
			var filter = '<div ng-click="col.sort()" ng-class="{ ngSorted: !noSortVisible }">'
					+ '<span class="ngHeaderText">{{col.displayName}}</span>'
					+ '<div class="ngSortButtonDown" ng-show="col.showSortButtonDown()"></div>'
					+ '<div class="ngSortButtonUp" ng-show="col.showSortButtonUp()"></div>'
					+ '</div>'
					+ '<div ng-show="col.allowResize" class="ngHeaderGrip" ng-click="col.gripClick($event)" ng-mousedown="col.gripOnMouseDown($event)"></div>'
					+ '';

			$scope.$watch('radioModel', function(newValue, oldValue) {
				$scope.list = Rest.find({ status: newValue });
			});
			$scope.radioModel = 'ACTIVE';
			$scope.model = {};
			
			$scope.gridOptions = {
				data : 'list',
				multiSelect : false,
				showFooter : true,
				showFilter : true,
				headerCellTemplate: filter,
				filterOptions : {
					filterText : '',
					useExternalFilter : false
				},
				columnDefs : [ {
					field : 'name',
					displayName : 'Name',
					enableCellEdit : true
				}, {
					field : "created.time",
					displayName : 'Created',
					cellFilter : 'date:"short"'
				}, {
					field : "modified.time",
					displayName : 'Modified',
					cellFilter : 'date:"short"'
				}, {
					field : 'status',
					displayName : 'Status',
					enableCellEdit : true,
					editableCellTemplate : sel
				}, {
					field : 'action',
					displayName : 'Action',
					cellTemplate : btnSave + btnDel
				}, ]
			};

			$scope.save = function(i) {
				$scope.list[i].$edit();
				return false;
			};

			$scope.remove = function(i) {
				$scope.list[i].$remove({
					id : $scope.list[i].id
				}, function() {
					$scope.list.splice(i, 1);
				});
				return false;
			};

			$scope.submit = function() {
				var item = new Rest();
				item.name = $scope.model.name;
				item.$add({}, function() {
					$scope.list.push(item);
					$scope.model.name = null;
				});
			};

		} ]);
</script>
<style type="text/css">
.grid {
	border: 1px solid rgb(212, 212, 212);
	height: 500px;
}
.title {
	height: 50px;
}
.btn-margin {
	margin-right: 5px;
}
.col4 {
	text-align: center;
}
</style>
</head>
<body data-ng-app="VPlus">
	<div data-ng-controller="MyCtrl">
		<h2 class="title">My Database</h2>
		<div class="navbar navStyle">
			<div class="btn-toolbar">
				<div class="btn-group navbar-form pull-left">
			        <button type="button" class="btn btn-primary" data-ng-model="radioModel" data-btn-radio="'ACTIVE'">Active</button>
			        <button type="button" class="btn btn-primary" data-ng-model="radioModel" data-btn-radio="'INACTIVE'">Inactve</button>
			        <button type="button" class="btn btn-primary" data-ng-model="radioModel" data-btn-radio="'REMOVED'">Removed</button>
			    </div>
			    <div class="btn-group">
			    	<form data-ng-submit="submit()" class="navbar-form input-group">
						<input type="text" data-ng-model="model.name" class="form-control"/>
						<span class="input-group-btn">
							<button class="btn btn-primary" type="submit">Add!</button>
						</span>
					</form>
			    </div>
			</div>
		</div>
		<div class="grid" data-ng-grid="gridOptions"></div>
	</div>
</body>
</html>