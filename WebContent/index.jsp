<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Men� de opciones</title>
</head>
<body>
	<h1>Men� de opciones de Productos</h1>
	
	<table border="1">
		<tr>
			<!-- Mapea la peticion hacia el servlet ProductoController -->
			<!-- Envia mediante get la peticion con el parametro opcion -->
			<td><a href="ProductoController?opcion=crear">Crear</a></td>
		</tr>
		<tr>
			<td><a href="ProductoController?opcion=mostrar">Mostrar</a></td>
		</tr>
	</table>
	
</body>
</html>