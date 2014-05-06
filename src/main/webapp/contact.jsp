<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript">
		
	$(document).ready(function() {
		$("#button1").click(function() {

			$.post("rest/contact/add/user",$("#wizard1Form").serialize() , function(data, status) {
				$( "#level1" ).hide();
				$( "#level2" ).show();
			});
		});
	});

	$(document).ready(function() {
		$("#button2").click(function() {

			$.post("rest/contact/add/user/contact",$("#wizard2Form").serialize() , function(data, status) {
				alert("Dados armazenados no bean (" + status + ").");
				window.location.href = "http://localhost:8080/estudo-rest/rest/contact/this";
			});
		});
	});
	
</script>
</head>
<body>
	<div id="level1">
		<form method="post" name="wizard1Form" id="wizard1Form">
			Name: <input type="text" name="name" /> 
			Idade: <input type="text" name="idade" /> 
			<br />
			<input type="button" id="button1" value="OK">
		</form>
	</div>
	
	<div id="level2" style="display:none">
		<form method="post" name="wizard2Form" id="wizard2Form" action="">
			E-mail: <input type="text" name="email" /> 
			Tel: (<input type="text" name="ddd" maxlength="3" size="3" />) <input type="text" name="phone" maxlength="9" size="9" /> 
			<br /> 
			<input type="button" id="button2" value="OK">
		</form>
	</div>

</body>
</html>