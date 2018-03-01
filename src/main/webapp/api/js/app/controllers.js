(function() {
	'use strict';
	/* Controllers */
	var module = angular.module('controllers', ["ui.bootstrap"]);
	
	module = module.controller('headerCtrl', function ($rootScope, $scope, $http, $state) {			
	});	
	
	module = module.controller('sideMenuCtrl', function($scope, $state, $rootScope) {
		function getMenuLinks() {
			var menuLinks = [];
			<#if services??>
				<#list services as service>	
				menuLinks.push('${service.name}');
				</#list>
			</#if>
			
			return menuLinks;
		}
		
		$scope.menuLinks = getMenuLinks();
	});
	
	// template for all entities 
	<#if services??>
		<#list services as service>	
	module = module.controller('${service.name}Ctrl', function($scope, $rootScope, $state, $http, $location) {
		$scope.startup = function() {
			$scope.serviceUrl = olConfig.baseUrl + '${service.name?lower_case}';
			$scope.baseUrl = $location.protocol() + '://' + $location.host() + ':' + $location.port();
			$scope.model = {};
			$scope.outModel = {};
			$scope.formRestResult = '';
			$scope.rawData = '';
			$scope.rawRestResult = '';
			
			$rootScope.hidePreloader();
		}
		
		$scope.formGet = function() {
			var url = $scope.getGetUrl();
			doGet(url, 'formRestResult', true);
		}
		
		$scope.formPost = function() {
			doPost(angular.toJson($scope.model), 'formRestResult', true);
		}
		
		$scope.rawGet = function() {
			var url = $scope.serviceUrl + $('#rawData').val();
			doGet(url, 'rawRestResult');
		}
		
		$scope.rawPost = function() {
			doPost(angular.fromJson($('#rawData').val()), 'rawRestResult');
		}
		
		function doGet(url, callback, updateOutModel) {
			$rootScope.showPreloader();
			$http({
				  method: 'GET',
				  url: url,
				  headers : {
						'Content-Type' : 'application/json',
						'Accept' : 'application/json'
					}
			}).then(function successCallback(response) {
				$scope[callback] = response.data;
				if (updateOutModel) {
					$scope.outModel = response.data;
				}
				$rootScope.hidePreloader();
			}, function errorCallback(response) {
				$rootScope.hidePreloader();
				alert(response.data);
			});
		}
		
		function doPost(data, callback, updateOutModel) {
			$rootScope.showPreloader();
			$http({
				  method: 'POST',
				  url: $scope.serviceUrl,
				  data: data,
				  headers : {
						'Content-Type' : 'application/json',
						'Accept' : 'application/json'
					}
			}).then(function successCallback(response) {
				$scope[callback] = response.data;
				if (updateOutModel) {
					$scope.outModel = response.data;
				}
				$rootScope.hidePreloader();
			}, function errorCallback(response) {
				$rootScope.hidePreloader();
				alert(response.data);
			});
		}
		
		$scope.getGetUrl = function() {
			var url = $scope.serviceUrl;
			var params = [];
			<#list service.methods as method>
				<#list method.inputParams as inputParam>
					<#list inputParam.fields as field>
					params.push('${field.fieldName}');
					</#list>
				</#list>
			</#list>
			
			for (var i = 0; i < params.length; i++) {
				if (i == 0) {
					url += '?';
				} else {
					url += '&';
				}
				
				url += params[i] + '=' + $scope.model[params[i]];
			}
			
			return url;
		}

		$scope.startup();
	});
		</#list>
	</#if>
})();