<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editar Productos</title>
</head>
<body>
	<h1>ACTUALIZAR UN PRODUCTO</h1>
	
	<!-- Mapea la peticion hacia el servlet ProductoController y la envia mediante post-->
	<form action="ProductoController" method="post">
		<!-- c:set recibe (en value) la informacion desde el controlador (llamando a productoEditar)
		y la pasa a una variable llamada producto -->
		<c:set var="producto" value="${productoEditar}"></c:set>
		<!-- input oculto que sirve para identificar al formulario de editar -->
		<input type="hidden" name="opcion" value="editar">
		<!-- input oculto para que el controlador pueda recibir el id del producto -->
		<input type="hidden" name="id" value="${producto.id}">
		<table border="1">
			<tr>
				<td>Nombre:</td>
				<td><input type="text" name="nombre" size="50" value="${producto.nombre}"></td>
			</tr>
			<tr>
				<td>Cantidad:</td>
				<td><input type="text" name="cantidad" size="50" value="${producto.cantidad}"></td>
			</tr>
			<tr>
				<td>Precio:</td>
				<td><input type="text" name="precio" size="50" value="${producto.precio}"></td>
			</tr> 
		</table>
		<input type="submit" value="Guardar">
	</form>
</body>
</html>