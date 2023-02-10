package com.jaro.controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jaro.dao.ProductoDAO;
import com.jaro.modelo.Producto;

/**
 * Servlet implementation class ProductoController
 */
@WebServlet(description = "Administra peticiones para la tabla productos", urlPatterns = { "/ProductoController" })
public class ProductoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//Se usa para redireccionar
	private RequestDispatcher requestDispatcher;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recibe las peticiones que tengan el parámetro opcion
		String opcion = request.getParameter("opcion");
		
		switch (opcion) {
		case "crear":
			System.out.println("Has pulsado la opcion crear");
			//Redirecciona a ala pagina de crear mediante el RequestDispactcher
			requestDispatcher = request.getRequestDispatcher("/views/crear.jsp");
			requestDispatcher.forward(request, response);
			break;
		case "mostrar":
			System.out.println("Has pulsado la opcion mostrar");
			ProductoDAO productoDAO = new ProductoDAO();
			List<Producto> lista = new ArrayList<Producto>();
			try {
				lista = productoDAO.mostrarTodosProductos();
				for (Producto producto : lista) {
					System.out.println(producto);
				}
				//Pasa la lista con el nombre de parametro listraProductos
				request.setAttribute("listaProductos", lista);
				requestDispatcher = request.getRequestDispatcher("/views/verProductos.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;

		default:
			break;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Obtiene la peticion con name "opcion" que llegue de los formularios
		String opcion = request.getParameter("opcion");
		ProductoDAO productoDAO = new ProductoDAO();
		Producto producto = new Producto();
		//Para indicar la fecha de creacion (Es de tipo java.util.Date)
		Date fechaActual = new Date();
		
		//Guarda la informacion del formulario en un objeto Producto
		producto.setNombre(request.getParameter("nombre"));
		//Los datos del formulario se reciben como String, por lo que hay que setearlos
		producto.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
		producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
		//Se crea un objeto de sql.Date para pasar la fecha de tipo util a una fecha de tipo sql
		producto.setFechaCrear(new java.sql.Date(fechaActual.getTime()));
		
		//Guarda el producto en la base de datos
		try {
			productoDAO.guardar(producto);
			System.out.println("Producto guardado correctamente.");
			//Redirecciona al index.jsp
		    requestDispatcher = request.getRequestDispatcher("/index.jsp");
			requestDispatcher.forward(request, response);
		} catch (SQLException e) {
			System.out.println("Error. No se ha podido guardar el producto.");
			e.printStackTrace();
		}
		
		
	}

}
