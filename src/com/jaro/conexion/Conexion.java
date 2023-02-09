package com.jaro.conexion;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

//Pull de conexiones
public class Conexion {

	private static BasicDataSource dataSource = null;
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String user = "root";
	private static String password = "root";
	private static String hostname = "localhost";
	private static String port = "3306";
	private static String database = "crud_java_web";
	private static String url = "jdbc:mysql://" + hostname + ":"+port+ "/" + database;

	
	private static DataSource getDataSource() {
		if (dataSource == null) {
			//Se crea un nuevo objeto de tipo DataSource
			dataSource = new BasicDataSource();
			//Se setea el Driver de conexion
			dataSource.setDriverClassName(driver);
			dataSource.setUsername(user);
			dataSource.setPassword(password);
			dataSource.setUrl(url);
			//Con cuantas conexiones se inicia el pool
			dataSource.setInitialSize(20);
			//Conexiones que pueden estar activas en el pool
			dataSource.setMaxIdle(15);
			//Conexiones totales
			dataSource.setMaxTotal(20);
			//Tiempo (milisegundos) hasta que una conexion se vuelva inactiva
			dataSource.setMaxWaitMillis(5000);
			
		}
		return dataSource;
	}
	
	//Método para acceder al pool de conexiones desde otras clases
	public static Connection getConexion() throws SQLException {
		return getDataSource().getConnection();
	}
	
}
