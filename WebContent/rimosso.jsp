<%@page import="org.infosons.achugo.rubrica.controller.EliminaContatto"%>
<%@page import="org.infosons.achugo.rubrica.model.Persona"%>
<%@page import="org.infosons.achugo.rubrica.controller.VisualizzaRubrica"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-cache");

	VisualizzaRubrica controllerVR = VisualizzaRubrica.getInstance();
	Integer index = (Integer) session.getAttribute("id");
	Persona p = controllerVR.recuperaTuttiContatti().get(index);
	EliminaContatto controllerEC = EliminaContatto.getInstance();
	String output = "Contatto eliminato con successo";
	try {
		controllerEC.cancellaContatto(p);
	} catch (Exception e) {
		output = "Contatto non eliminato per qualche motivo. Riprova più tardi. :-/";
	}
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
<title>Rubrica v2 | Elimina Contatto</title>
</head>
<body>
	<h1><%=output %></h1>
	<form action="FinestraPrincipale.jsp" method="post">
		<button type="submit" class="btn btn-default">Vai alla Rubrica</button>
	</form>
</html>