<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-cache");
	
	Enumeration<?> attrs = request.getParameterNames();
	int id = -1;
	while (attrs != null && attrs.hasMoreElements()) {
		String name = (String) attrs.nextElement();
		if (name.contains("remove")) {
			id = Integer.parseInt(name.substring("remove".length()));
		}
	}
	session.setAttribute("id", new Integer(id));
%>

<!DOCTYPE html>
<html>
<head>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"></link>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" type="text/css"></link>
<link href="http://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet" type="text/css"></link>
<link rel="stylesheet" href="css/elimina.min.css" type="text/css"></link>
<meta charset="UTF-8">
<title>Rubrica v2 | Elimina Contatto</title>
</head>
<body>
	<div id="loginModal" class="modal show relative non-overflow" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="text-center">Elimina Contatto</h1>
				</div>
				<div class="modal-body">
					<h4>Sei sicuro di voler eliminare il contatto?</h4>
				</div>
				<div class="modal-footer">
					<div class="col-md-6 no-align">
						<form action="rimosso.jsp">
							<button class="btn" data-dismiss="modal" aria-hidden="true">S&igrave;, procedi pure. Grazie</button>
						</form>
					</div>
					<div class="col-md-6 no-align">
						<form action="FinestraPrincipale.jsp">
							<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">No, mi sono sbagliato xD</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>