<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Crear Producto</title>
</head>
<body>
	<h1>AÑADIR UN NUEVO PRODUCTO</h1>
	
	<!-- Mapea la peticion hacia el servlet ProductoController y la envia mediante post-->
	<form action="ProductoController" method="post">
	<!-- input oculto que sirve para identificar al formulario de guardar, ya que tambien existe el formulario editar -->
	<input type="hidden" name="opcion" value="guardar">
		<table border="1">
			<tr>
				<td>Nombre:</td>
				<td><input type="text" name="nombre" size="50"></td>
			</tr>
			<tr>
				<td>Cantidad:</td>
				<td><input type="text" name="cantidad" size="50"></td>
			</tr>
			<tr>
				<td>Precio:</td>
				<td><input type="text" name="precio" size="50"></td>
			</tr> 
		</table>
		<input type="submit" value="Guardar">
	</form>
	
</body>
</html>