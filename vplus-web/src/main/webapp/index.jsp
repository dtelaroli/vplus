<html>
<head>
<title>Index</title>
	<script type="text/javascript" src="/vplus-web/js/vendor/ng-grid//lib/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="/vplus-web/js/vendor/angular/angular.min.js"></script>
	<script type="text/javascript" src="/vplus-web/js/vendor/angular/angular-resource.min.js"></script>
	<script type="text/javascript" src="/vplus-web/js/vendor/ng-grid/ng-grid-2.0.7.min.js"></script>
	<link type="text/css" rel="stylesheet" href="/vplus-web/js/vendor/ng-grid/ng-grid.min.css"/>
	
	<script type="text/javascript" src="/vplus-web/js/core/Conf.js"></script>
	<script type="text/javascript" src="/vplus-web/js/angular/flexa-core/factories.js"></script>
	<script type="text/javascript" src="/vplus-web/js/core/Rest.js"></script>
	<script type="text/javascript">
		angular.module('VPlus.Config').constant('URL', '/vplus-web/my/:id');
		angular.module('VPlus').controller('MyCtrl',
				[ '$scope', 'Rest', function($scope, Rest) {
	
					var tmpl = '<button data-ng-click="save(row.rowIndex)">Save</button>';
					$scope.model = {};
					$scope.list = Rest.query();
					$scope.gridOptions = {
						data : 'list',
						multiSelect: false,
						columnDefs: [
							{field:'name', displayName:'Name', enableCellEdit:true}, 
							{field:"created.time", displayName:'Created', cellFilter:'date:"short"'},
							{field:"modified.time", displayName:'Modified', cellFilter:'date:"short"'},
							{field:'status', displayName:'Status', enableCellEdit:true},
							{field:'save', displayName:'Save', cellTemplate: tmpl }
						]
					};
					
					$scope.save = function(i) {
						$scope.list[i].$edit();
						return false;
					};
	
					$scope.submit = function() {
						var item = new Rest();
						item.name = $scope.model.name;
						item.$add();
						$scope.list.push(item);
					};
	
				} ]);
	</script>
	<style type="text/css">
		.gridStyle {
		    border: 1px solid rgb(212,212,212);
		    width: 800px; 
		    height: 400px
		}
	</style>
</head>
<body data-ng-app="VPlus">
	<h2>Hello World!</h2>
	<div data-ng-controller="MyCtrl">
		<table>
			<thead>
				<tr>
					<th>Name</th>
					<th>Created</th>
					<th>Modified</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<tr data-ng-repeat="item in list">
					<td>{{item.name}}</td>
					<td>{{item.created.time | date:'short'}}</td>
					<td>{{item.modified.time | date:'short'}}</td>
					<td>{{item.status.$}}</td>
				</tr>
			</tbody>
		</table>
		<div class="gridStyle" data-ng-grid="gridOptions"></div>

		<div>
			<form data-ng-submit="submit()">
				<input data-ng-model="model.name" /> <input type="submit"
					value="Save" />
			</form>
		</div>
	</div>
</body>
</html>
