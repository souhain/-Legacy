<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" data-ng-app="olApp" lang="en">
<head>
	<title>OpenLegacy - Restful test</title>
	<meta name="viewport" content="width=device-width">
	<!-- favorites icon -->
	<link href="img/favicon.ico" rel="shortcut icon" />
	
	<!-- Bootstrap core CSS -->
	<link href="lib/bootstrap/css/bootstrap.min.css" media="all" type="text/css" rel="stylesheet"/>	
	<!-- custom style for this project -->
	<link type="text/css" rel="stylesheet" href="css/project.css" />	
	
	<!-- jQuery -->
	<script src="lib/jquery/jquery-2.1.4.min.js" type="text/javascript"></script>
	<script src="lib/jquery/jquery.cookie.js" type="text/javascript"></script>
	
	<!-- Bootstrap core JS -->    
    <script src="lib/bootstrap/js/bootstrap.min.js"></script>
    
    <!-- Angular core JS -->
    <script src="lib/angular/angular.min.js"></script>
    <script src="lib/angular/angular-route.min.js"></script>
    <script src="lib/angular/angular-ui-router.min.js"></script>
    
    <script src="lib/bootstrap/js/ui-bootstrap-tpls-0.14.3.min.js"></script>
    
    <script src="js/app/app.js_ng" type="text/javascript"></script>
	<script src="js/app/controllers.js_ng" type="text/javascript"></script>
	<script src="js/app/services.js" type="text/javascript"></script>
	<script src="js/app/directives.js" type="text/javascript"></script>
	<script src="js/app/config.js" type="text/javascript"></script>
</head>
<body>
	<img src="img/preloader.gif" class="preloader" ng-show="_showPreloader" style="z-index:99">
	<div class="content-wrapper">	
		<div ui-view="header" ng-cloak></div>	
		<div class="container-fluid">    
	    	<div class="row main">		  			  			
				<div class="col-xs-12 col-sm-3 col-md-2 no-pa-ma">				
					<div ui-view="sideMenu" ng-cloak></div>
				</div>						
				<div class="col-xs-12 col-sm-9 col-md-10">								
					<!-- content view -->				
					<div ui-view ng-cloak></div>
					<!-- /content view -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>