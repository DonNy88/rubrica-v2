<%@page import="org.infosons.achugo.rubrica.controller.Login"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-cache");

	Login controller = Login.getInstance();
	String user = request.getParameter("lg_username");
	String password = request.getParameter("lg_password");
	boolean isValidate = controller.verifica(user, password);
	String title = isValidate ? "Benvenuto" : "ERRORE";
%>

<!DOCTYPE html>
<html>
<head>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"></link>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" type="text/css"></link>
<link href="http://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet" type="text/css"></link>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.13.1/jquery.validate.min.js"></script>
<link rel="stylesheet" href="css/login.min.css" type="text/css"></link>
<meta charset="UTF-8">
<title>Rubrica v2 | <%=title%></title>
</head>
<body>
	<%if (isValidate) {	%>
		<h1>Ciao <%=user.toUpperCase()%></h1>
		<form action="FinestraPrincipale.jsp" method="post">
			<button type="submit" class="btn btn-default">vai alla Rubrica</button>
		</form>
	<%} else {%>
		<h1>Credeziali errate</h1>
		<form action="index.jsp" method="post">
			<button type="submit" class="btn btn-default">torna indietro</button>
		</form>
	<%} %>
</body>
</html>