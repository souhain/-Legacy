( function() {

	'use strict';

	/* App Module */
	
	var olApp = angular.module('olApp', ['controllers', 'services', 'directives', 'ngRoute', 'ui.router']).run(['$rootScope', '$state', function($rootScope, $state) {
		$rootScope.allowHidePreloader = true;
		$rootScope.allowShowPreloader = true;
		$rootScope._showPreloader = false;
		
		$rootScope.showPreloader = function(hideContent) {
			if ($rootScope.allowShowPreloader == true) {
				$rootScope._showPreloader = true;				
			}		
		}
		$rootScope.hidePreloader = function() {						
			if ($rootScope.allowHidePreloader == true) {
				$rootScope._showPreloader = false;
				$rootScope._showContent = true;
			}			
		}
	}]);
	
	olApp.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
		var header = { templateUrl: "views/partials/header.html", controller: "headerCtrl" };
		
		$stateProvider	
			.state('menu', {
				url: '/',
				views: {
					"": {
						templateUrl: "views/static/menu.html",
						controller: 'sideMenuCtrl'
					},
					"header": header,
					"sideMenu": {
						templateUrl: "views/partials/sideMenu.html",
						controller: "sideMenuCtrl"
					}
				}
			});
	
		var urlsToFilter = [];
		
		function addRoute(stateName, entityName, url) {
			$stateProvider.state(stateName, {
				url: url,
				views: {
					"": {
						templateUrl: "views/static/" + entityName + ".html",
						controller: entityName + 'Ctrl'
					},
					"header": header,
					"sideMenu": {
						templateUrl: "views/partials/sideMenu.html",
						controller: "sideMenuCtrl"
					}
				}
			});
		};
		<#if services??>
			<#list services as service>				
		var url = "/${service.name}";
		if ($.inArray(url, urlsToFilter) == -1) {
			addRoute("${service.name}", "${service.name}", url);
		}				
			</#list>
		</#if>
		
		$urlRouterProvider.otherwise(function ($injector, $location) {
	        var $state = $injector.get('$state');
        	$state.go("menu");
	    });
	}]);
})();