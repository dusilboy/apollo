<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html class="no-js" lang="en">
<head>
	
	<meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title><sitemesh:write property='title' /></title>
	<link rel="stylesheet" href="/resources/css/foundation.css" />
    <link rel="stylesheet" href="/resources/css/main.css" />
    <script src="/resources/js/vendor/modernizr.js"></script>

    <sitemesh:write property='head' />
    <script type="text/javascript">
	</script>
</head>
<body>
<div id="wrap">
    <header>
        <h1 class="imgRep"><a href="/">SK telecom APOLLO</a></h1>
        <!-- end of #nav_menu -->
    </header>
		
	<sitemesh:write property='body' />
</body>
</html>