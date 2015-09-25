<%@page import="java.util.ArrayList"%>
<%@page import="java.io.IOException"%>
<%@page import="org.infosons.achugo.rubrica.model.Persona"%>
<%@page import="java.util.List"%>
<%@page import="org.infosons.achugo.rubrica.controller.VisualizzaRubrica"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-cache");
	VisualizzaRubrica controllerVR = VisualizzaRubrica.getInstance();
	session.removeAttribute("id");
%>

<!DOCTYPE html>
<html>
<head>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"></link>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" type="text/css"></link>
<link href="http://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet" type="text/css"></link>
<link href="css/rubrica.min.css" rel="stylesheet" type="text/css"></link>
<meta charset="UTF-8">
<title>Rubrica v2 | Finestra principale</title>
</head>
<body>
	<!--==============================Content=================================-->
	<br />
	<br />
	<div align="center" class="container">
		<div class="row col-md-6 col-md-offset-2 custyle">
			<table class="table table-striped custab">
				<thead>
					<tr>
						<th>Nome</th>
						<th>Cognome</th>
						<th>Telefono</th>
						<th class="text-center">Azione</th>
					</tr>
				</thead>
				<%
					List<Persona> contatti = controllerVR.recuperaTuttiContatti();
					if (contatti == null) {
						contatti = new ArrayList<Persona>();
					}
					for (int index = 0; index < contatti.size(); index++) {
				%>
				<tr>
					<td><h4><%= contatti.get(index).getNome() %></h4></td>
					<td><h4><%= contatti.get(index).getCognome() %></h4></td>
					<td><h4><%= contatti.get(index).getTelefono() %></h4></td>
					<td class="text-center">
						<form action="Modifica.jsp" method="POST">
							<p data-placement="top" data-toggle="tooltip" title="Edit">
								<button type="submit" class="btn btn-default btn-info btn-xs glyphicon glyphicon-edit"
									name=<%out.write("edit" + index);%> id=<%out.write("edit" + index);%> value=<%out.write("edit" + index);%>>Modifica</button>
							</p>
						</form>
						<form action="Elimina.jsp">
							<p data-placement="top" data-toggle="tooltip" title="Remove">
								<button type="submit" class="btn btn-default btn-danger btn-xs glyphicon glyphicon-remove"
									name=<%out.write("remove" + index);%> id=<%out.write("remove" + index);%> value=<%out.write("remove" + index);%>>Elimina</button>
							</p>
						</form>
					</td>
				</tr>
				<%
					}
				%>
				
			</table><a href="Nuovo.jsp" class="btn btn-primary btn-xs pull-right"><b>+</b> Nuovo contatto</a>
		</div>
	</div>
</body>
</html>