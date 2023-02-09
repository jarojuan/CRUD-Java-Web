package com.jaro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.jaro.conexion.Conexion;
import com.jaro.modelo.Producto;

// El DAO (Objeto de Acceso a Datos) se encarga de intercambiar información con la base de datos

public class ProductoDAO {

	private Connection connection;
	//Objeto usado para poder generar el canal de conexion y enviar las querys
	private PreparedStatement statement;
	private boolean estadoOperacion;
	private String sql;
	
	//Métodos CRUD
	
	//Guardar un nuevo producto
	public boolean guardar(Producto producto) throws SQLException {
		//String sql = "";
		estadoOperacion = false;
		//Obtiene una conexion de pool
		connection = obtenerConexion();
		
		try {
			//Inicio de la transaccion
			connection.setAutoCommit(false);
			sql = "INSERT INTO productos (id, nombre, cantidad, precio, fechaCrear, fechaActualizar) VALUES (?,?,?,?,?,?)";
			
			//Se prepara la query
			statement = connection.prepareStatement(sql);
			//Se pasan los parameros
			statement.setString(1, null); //Null porque es autoincremental
			statement.setString(2, producto.getNombre());
			statement.setInt(3, producto.getCantidad());
			statement.setDouble(4, producto.getPrecio());
			statement.setDate(5, producto.getFechaCrear());
			statement.setDate(6, producto.getFechaActualizar());
			
			//Se ejecuta la query (si es >0 quiere decir que es true)
			estadoOperacion = statement.executeUpdate()>0;
			
			//Guarda los datos en la bd
			connection.commit();
			statement.close();
			//Devuelve la conexion al pool
			connection.close();
		} catch (SQLException e) {
			//Si hay algun error, no guarda ningun resultado de la query en la bd
			connection.rollback();
			System.out.println("Error. No se ha podido guardar el nuevo producto");
			e.printStackTrace();
		}
		
		return true;
	}
	
	//Editar
	public boolean editar(Producto producto) {
		
		return true;
	}
	
	//Eliminar
	public boolean eliminar(int idProducto) {
		
		return true;
	}
	
	//Obtener lista de todos los productos de la bd
	public List<Producto> mostrarTodosProductos() {
		return null;
	}
	
	//Obtener un producto de la bd por su id
	public Producto mostrarUnProducto(int idProducto) {
		return null;
	}
	
	//Obtener conexion desde el pool de conexiones
	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConexion();
	}
	
}
