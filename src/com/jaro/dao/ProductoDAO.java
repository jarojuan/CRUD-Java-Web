package com.jaro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	//Objeto que obtiene los datos de las consultas sql (se usa en los metodos de mostrar)
	private ResultSet resultSet;
	
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
			sql = "INSERT INTO productos (id, nombre, cantidad, precio, fecha_crear, fecha_actualizar) "
					+ "VALUES (?,?,?,?,?,?)";
			
			//Se prepara la query, abriendo un canal con la bd
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
		
		return estadoOperacion;
	}
	
	//Actualizar
	public boolean editar(Producto producto) throws SQLException {
		estadoOperacion = false;
		connection = obtenerConexion();
		
		try {
			//Se inicia la transaccion
			connection.setAutoCommit(false);
			sql = "UPDATE productos SET nombre=?, cantidad=?, precio=?, fechaActualizar=? WHERE id=?";
			statement = connection.prepareStatement(sql);
			//Al pasar los parametros, se debe seguir el orden (de ?) que tienen en la query
			statement.setString(1, producto.getNombre());
			statement.setInt(2, producto.getCantidad());
			statement.setDouble(3, producto.getPrecio());
			statement.setDate(4, producto.getFechaActualizar());
			statement.setInt(5, producto.getId());
			
			//Se ejecuta la query (si es >0 quiere decir que es true)
			estadoOperacion = statement.executeUpdate()>0;
			
			//Guarda los datos en la bd, usando la transaccion
			connection.commit();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			//Si hay algun error, no guarda ningun resultado de la query en la bd
			connection.rollback();
			System.out.println("Error. No se ha podido editar el producto");
			e.printStackTrace();
		}
		
		return estadoOperacion;
	}
	
	//Eliminar
	public boolean eliminar(int idProducto) throws SQLException {
		estadoOperacion = false;
		connection = obtenerConexion();
		
		try {
			connection.setAutoCommit(false);
			sql = "DELETE FROM productos WHERE id=?";
			statement = connection.prepareStatement(sql);	
			//Le pasamos a la query el id que recibe el metodo
			statement.setInt(1, idProducto);
			//Se ejecuta la query (si es >0 quiere decir que es true)
			estadoOperacion = statement.executeUpdate()>0;			
			connection.commit();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			connection.rollback();
			System.out.println("Error. No se ha podido eliminar el producto");
			e.printStackTrace();
		}
		
		return estadoOperacion;
	}
	
	//Obtener lista de todos los productos de la bd
	public List<Producto> mostrarTodosProductos() throws SQLException {
		//estadoOperacion = false;
		connection = obtenerConexion();
		List<Producto> listaProductos = new ArrayList<Producto>();
		//List<Producto> listaProductos = new ArrayList<>();
		
		try {
			connection.setAutoCommit(false);
			sql = "SELECT * FROM productos";
			statement = connection.prepareStatement(sql);	
			//Ejecuta la query y obtiene el resultado en el objeto ResultSet
			resultSet = statement.executeQuery(sql);
			
			//Mientras resultSet tenga un registro que iterar devuelve true
			while (resultSet.next()) {
				Producto producto = new Producto();
				//1 porque el en la base de datos el id esta en la columna 1 de la tabla
				producto.setId(resultSet.getInt(1));
				producto.setNombre(resultSet.getString(2));
				producto.setCantidad(resultSet.getInt(3));
				producto.setPrecio(resultSet.getDouble(4));
				producto.setFechaCrear(resultSet.getDate(5));
				producto.setFechaActualizar(resultSet.getDate(6));
				//Añade el producto a la lista
				listaProductos.add(producto);
			}
			/*	
			//Se ejecuta la query (si es >0 quiere decir que es true)
			estadoOperacion = statement.executeUpdate()>0;			
			connection.commit();
			statement.close();
			connection.close();
			*/
		} catch (SQLException e) {
			//connection.rollback();
			System.out.println("Error. No se han podido mostrar los productos");
			e.printStackTrace();
		}
		
		return listaProductos;
	}
	
	//Obtener un producto de la bd por su id
	public Producto mostrarUnProducto(int idProducto) throws SQLException {
		connection = obtenerConexion();
		Producto producto = new Producto();
		
		try {
			connection.setAutoCommit(false);
			sql = "SELECT * FROM productos WHERE id=?";
			statement = connection.prepareStatement(sql);
			//Se pasa a la query el id que recibe el metodo
			statement.setInt(1, idProducto);
			
			//Ejecuta la query (mediante el statement, que ya ha recibido la query) y obtiene el resultado
			resultSet = statement.executeQuery();
			
			//Si resultSet tiene un registro que iterar devuelve true
			if (resultSet.next()) {
				//1 porque el en la base de datos el id esta en la columna 1 de la tabla
				producto.setId(resultSet.getInt(1));
				producto.setNombre(resultSet.getString(2));
				producto.setId(resultSet.getInt(3));
				producto.setPrecio(resultSet.getDouble(4));
				producto.setFechaCrear(resultSet.getDate(5));
				producto.setFechaActualizar(resultSet.getDate(6));
			}

		} catch (SQLException e) {
			//connection.rollback();
			System.out.println("Error. No se ha podido mostrar el producto buscado");
			e.printStackTrace();
		}
		
		return producto;
	}
	
	//Obtener conexion desde el pool de conexiones
	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConexion();
	}
	
}
