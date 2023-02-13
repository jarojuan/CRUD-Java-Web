<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- CSS Bootstrap -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<title>Ver Productos</title>
</head>
<body>
	<h1>VER LISTA DE PRODUCTOS</h1>
	<!-- Recibe la lista de productos desde Productos Controller, haciendo referencia al nombre del atributo -->
	<table border="1">
		<tr>
			<td>ID</td>
			<td>NOMBRE</td>
			<td>CANTIDAD</td>
			<td>PRECIO</td>
			<td>FECHA DE CREACIÓN</td>
			<td>FECHA DE ACTUALIZACIÓN</td>
			<td>ACCIONES</td>
		</tr>
		<!-- tags JSTL -->
		<!-- listaProductos hace referencia a la lista que se va a recibir desde ProductoController -->
		<c:forEach var="producto" items="${listaProductos}">
			<tr>			
				<td>
					<!-- Al pulsar en el enlace (el id) se pasa al controlador la opcion editar y el id pulsado -->
					<a href="ProductoController?opcion=editar&id=<c:out value="${producto.id}"></c:out>">
						<c:out value="${producto.id}"></c:out>
					</a>
				</td>
				<td><c:out value="${producto.nombre}"></c:out></td>
				<td><c:out value="${producto.cantidad}"></c:out></td>
				<td><c:out value="${producto.precio}"></c:out></td>
				<td><c:out value="${producto.fechaCrear}"></c:out></td>
				<td><c:out value="${producto.fechaActualizar}"></c:out></td>
				<td>
					<button type="button" class="btn btn-success">
					<a href="ProductoController?opcion=editar&id=<c:out value="${producto.id}"></c:out>">Editar</a>
					</button>
					<button type="button" class="btn btn-danger">
					<a href="ProductoController?opcion=eliminar&id=<c:out value="${producto.id} "></c:out>">Eliminar</a>
					</button>
				</td>
			</tr>
		</c:forEach>
	</table>
	
		<!-- js bootstrap -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
	
</body>
</html>