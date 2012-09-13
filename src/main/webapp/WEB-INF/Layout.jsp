<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title><s:text name="sistema.titulo"/></title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<link href="stylesheets/styles.css" type="text/css" rel="stylesheet" />
	<link href="stylesheets/demo_page.css" type="text/css" rel="stylesheet" />
	<link href="stylesheets/demo_table.css" type="text/css" rel="stylesheet" />
	<link href="stylesheets/TableTools.css" type="text/css" rel="stylesheet" />

	
	<script type="text/JavaScript" src="javascripts/jquery-1.7.1.min.js"></script>
	<script type="text/JavaScript" src="javascripts/application.js"></script>
	<script type="text/javascript" src="javascripts/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="javascripts/TableTools.js"></script>
	<script type="text/javascript" src="javascripts/ZeroClipboard.js"></script>
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<div id="logo-fed" class="centered"></div>
		</div>
		<div class="decoration"></div>
		<div class="application-name centered">
			<h1><s:text name="sistema.titulo_abrev"/></h1>
		</div>
		
		<div id="page" class="centered">
			<s:include value="content/%{renderPartial}"/>
			

		</div>		
		<div id="footer">
			<p><s:text name="conacyt.ubicacion"/></p>
		</div>
	</div>
	
</body>
</html>
