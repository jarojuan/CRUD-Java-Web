<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
			</tr>
		</c:forEach>
	</table>
</body>
</html>