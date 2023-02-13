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

	// Variables que se usaran varias veces
	private ProductoDAO productoDAO = new ProductoDAO();
	private Producto producto = new Producto();
	// Para indicar la fecha actual (Es de tipo java.util.Date, para pasarla a la bd se cambiará el tipo)
	private Date fechaActual = new Date();	
	private List<Producto> lista = new ArrayList<Producto>();
	// Se usa para redireccionar
	private RequestDispatcher requestDispatcher;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recibe las peticiones que tengan el parámetro opcion
		String opcion = request.getParameter("opcion");

		switch (opcion) {
		case "crear":
			System.out.println("Has pulsado la opcion crear");
			// Redirecciona a a la pagina de crear mediante el RequestDispactcher
			requestDispatcher = request.getRequestDispatcher("/views/crear.jsp");
			requestDispatcher.forward(request, response);
			break;
		case "mostrar":
			System.out.println("Has pulsado la opcion mostrar");

			try {
				lista = productoDAO.mostrarTodosProductos();
				for (Producto producto : lista) {
					System.out.println(producto);
				}
				// Pasa la lista con el nombre de parametro listraProductos
				request.setAttribute("listaProductos", lista);
				requestDispatcher = request.getRequestDispatcher("/views/verProductos.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "editar":
			// Recoge el id recibido desde verProductos.jsp y lo parsea a int
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("Id editar = " + id);
			try {
				producto = productoDAO.mostrarUnProducto(id);
				System.out.println("Producto a editar: " + producto);
				// Se pasa el producto que se va a editar. Lo recogerá actualizar.jsp
				request.setAttribute("productoEditar", producto);
				requestDispatcher = request.getRequestDispatcher("/views/actualizar.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "eliminar":
			// Recoge el id recibido al pulsar eliminar desde verProductos.jsp
			id = Integer.parseInt(request.getParameter("id"));
			try {
				productoDAO.eliminar(id);
				System.out.println("Articulo eliminado correctamente.");
				// Redirecciona al verProductos.jsp mostrando la lista de productos actualizada
				lista = productoDAO.mostrarTodosProductos();
				request.setAttribute("listaProductos", lista);
				requestDispatcher = request.getRequestDispatcher("/views/verProductos.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				System.out.println("Error. El articulo no se ha podido eliminar");
				e.printStackTrace();
			}
			break;
		default:
			break;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtiene la peticion con name "opcion" que llegue de los formularios
		String opcion = request.getParameter("opcion");

		switch (opcion) {
			case "guardar":
				// Guarda la informacion del formulario en un objeto Producto
				producto.setNombre(request.getParameter("nombre"));
				// Los datos del formulario se reciben como String, por lo que hay que setearlos
				producto.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
				producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
				// Se crea un objeto de sql.Date para pasar la fecha de tipo util a una fecha de
				// tipo sql
				producto.setFechaCrear(new java.sql.Date(fechaActual.getTime()));
	
				// Guarda el producto en la base de datos
				try {
					productoDAO.guardar(producto);
					System.out.println("Producto guardado correctamente.");
					// Redirecciona al index.jsp
					requestDispatcher = request.getRequestDispatcher("/index.jsp");
					requestDispatcher.forward(request, response);
				} catch (SQLException e) {
					System.out.println("Error. No se ha podido guardar el producto.");
					e.printStackTrace();
				}
				break;
			case "editar":
				//Recoge el id para pasarselo a ProductoDAO y poder indicar el id del producto a editar
				producto.setId(Integer.parseInt(request.getParameter("id")));
				producto.setNombre(request.getParameter("nombre"));
				producto.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
				producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
				producto.setFechaActualizar(new java.sql.Date(fechaActual.getTime()));
	
				// Guarda el producto editado en la base de datos
				try {
					productoDAO.editar(producto);
					System.out.println("Producto editado correctamente.");
					// Redirecciona al verProductos.jsp mostrando la lista de productos actualizada
					lista = productoDAO.mostrarTodosProductos();
					request.setAttribute("listaProductos", lista);
					requestDispatcher = request.getRequestDispatcher("/views/verProductos.jsp");
					requestDispatcher.forward(request, response);
				} catch (SQLException e) {
					System.out.println("Error. No se ha podido editar el producto.");
					e.printStackTrace();
				}
				break;
			default:
				break;
		}

	}

}
