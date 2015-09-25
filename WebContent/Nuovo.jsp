<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-cache");
%>

<!DOCTYPE html>
<html>
<head>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"></link>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" type="text/css"></link>
<link href="http://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet" type="text/css"></link>
<script src="js/nuovo.min.js"></script>
<link rel="stylesheet" href="css/nuovo.min.css" type="text/css"></link>
<meta charset="UTF-8">
<title>Rubrica v2 | Nuovo Contatto</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<h2>Nuovo Contatto</h2>
			<form role="form" action="salvaContatto.jsp" method="POST">
				<div class="form-group bs-float-label col-md-6 col-md-offset-2">
					<input id="float-input-1" type="text" class="form-control float-input" placeholder="Nome" name="nome" id="nome"/>
					<input id="float-input-1" type="text" class="form-control float-input" placeholder="Cognome" name="cognome" id="cognome"/>
					<input id="float-input-1" type="text" class="form-control float-input" placeholder="Telefono" name="telefono" id="telefono"/>
					<input id="float-input-1" type="text" class="form-control float-input" placeholder="Indirizzo" name="indirizzo" id="indirizzo"/>
					<input id="float-input-1" type="number" class="form-control float-input" placeholder="Et&agrave;" name="eta" id="eta"/>
					<br />
					<br />
					<button class="btn btn-primary btn-lg btn-block" name="salva" id="salva">Salva Contatto</button>
					<span><a href="FinestraPrincipale.jsp">Torna indietro</a></span>
				</div>
			</form>
		</div>
	</div>
</body>
</html>