package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Product;
import logic.LogicProduct;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
     LogicProduct logic;  

    public AddProduct() {
        super();
        logic = new LogicProduct();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Illegal Access");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try {
		if (request.getParameter("name") != null) { //if true, then the form to add a new product on ManageProduct.jsp was completed, and the servlet proceeds to send the new product to the logic layer
			Product newProduct = new Product();
			newProduct.setName((String)request.getParameter("name"));
			if(!((String)request.getParameter("description")).isEmpty()) 
				newProduct.setDescription((String)request.getParameter("description"));
			newProduct.setPrice((float)Integer.parseInt(request.getParameter("price"))); //parseFloat?
			newProduct.setCategory((String)request.getParameter("category"));
			newProduct.setStock(Integer.parseInt(request.getParameter("stock")));
			if (logic.add(newProduct)) {
				LinkedList<Product> productList = logic.showAll();
				request.setAttribute("productList", productList);
				request.setAttribute("trigger", "clean");
				request.getRequestDispatcher("WEB-INF/ManageProduct.jsp").forward(request, response);
			} else {
				LinkedList<Product> productList = logic.showAll();
				request.setAttribute("productList", productList);
				request.setAttribute("trigger", "errorAdd");
				request.getRequestDispatcher("WEB-INF/ManageProduct.jsp").forward(request, response);
			}
		} else {
		
		LinkedList<Product> productList = logic.showAll();	//checkear si no hay una forma de no tener que repetir esto por cada request
		request.setAttribute("productList", productList);
		request.setAttribute("trigger", "add");
		request.getRequestDispatcher("WEB-INF/ManageProduct.jsp").forward(request, response);
		}
		//primero tengo que redirigir del ManageProduct a un jsp con un form para añadir product(o al mismo pero pasando una variable en la request para que arriba del todo de la tabla muestre fields para añadir un producto; luego, una vez se completo ese form, se presiona el boton de submit del mismo y este servlet ejecuta el logic.add, para luego devolver al admin al jsp con la tabla sin los campos de añadir producto, o devuelve un pop up si no se pudo insertar el product)
	 // a la hora de añadir imágenes, creo que lo mejor sería ejecutar el product.add, y, si ese deduelve true, ejecutar la parte de "añadir imágenes" (tengo que ver como hago para sacar las imagenes de la request, que es adonde supongo van a estar a la hora de recibir el form)

}catch (Exception e) {
	e.printStackTrace(response.getWriter());
}
	}

}
