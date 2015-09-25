<%@page import="org.infosons.achugo.rubrica.controller.VisualizzaRubrica"%>
<%@page import="org.infosons.achugo.rubrica.controller.ModificaContatto"%>
<%@page import="org.infosons.achugo.rubrica.model.Persona"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-cache");

	String nome = request.getParameter("nome");
	String cognome = request.getParameter("cognome");
	String indirizzo = request.getParameter("indirizzo");
	String telefono = request.getParameter("telefono");
	String etaStr = request.getParameter("eta");
	int eta = etaStr.equals("") ? -1 : Integer.parseInt(etaStr);

	ModificaContatto controllorMC = ModificaContatto.getInstance();
	VisualizzaRubrica controllorVR = VisualizzaRubrica.getInstance();
	String titolo = "Contatto modificato";
	boolean erroreFlag = nome.equals("") || cognome.equals("") || indirizzo.equals("") || telefono.equals("") || eta == -1;
	if (erroreFlag) {
		titolo = "Contatto non modificato :-/";
	} else {
		try {
			Integer index = (Integer) session.getAttribute("id");
			Persona p = controllorVR.recuperaTuttiContatti().get(index);
			controllorMC.modificaContatto(p, new Persona(nome, cognome, indirizzo, telefono, eta));
		} catch (Exception e) {
			erroreFlag = false;
		}
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rubrica v2 | <%=titolo %></title>

<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" type="text/css"></link>
<link href="http://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet" type="text/css"></link>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.13.1/jquery.validate.min.js"></script>
<link rel="stylesheet" href="css/login.min.css" type="text/css"></link>
<script src="js/login.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

</head>
<body>
	<%if (!erroreFlag) {%>
		<h1>Il contatto &egrave; stato modificato</h1>
	<%} else { %>
		<h1>Il contatto non &egrave; stato modificato. Forse non hai riempito tutti campi. Riprova.</h1>
	<%}%>
	<form action="FinestraPrincipale.jsp" method="post">
		<button type="submit" class="btn btn-default">vai alla Rubrica</button>
	</form>
</body>
</html>