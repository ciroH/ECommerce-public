package servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DataProduct;
import entities.Product;


@WebServlet("/ManageCart")
public class ManageCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    public ManageCart() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<Integer,Integer> shoppingCart = new HashMap<>();
		if (request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id")); //conviene no traerme el product en este servlet y directamente usar el id como key, porque me llegan a cambiar el product y el user va a tener dos veces ele mismo product
			int qty = Integer.parseInt(request.getParameter("quantity"));
			if(request.getSession().getAttribute("shoppingCart") != null) { 
				shoppingCart.putAll((HashMap<Integer,Integer>)request.getSession().getAttribute("shoppingCart"));
				if(!shoppingCart.containsKey(id)) {
					shoppingCart.put(id,qty); //meter esta linea adentro de un if, verificando si ya no existe la key
				} else {
						int oldQty = shoppingCart.get(id);
						shoppingCart.replace(id, qty + oldQty);
				}
			}else {
				shoppingCart.put(id,qty);
			}
			//else creo el hashMap, le meto el product, y lo asigno a la session.
		request.getSession().setAttribute("shoppingCart", shoppingCart);
		request.getRequestDispatcher("WEB-INF/ShoppingCart.jsp").forward(request, response);
		} else {
			if (request.getSession().getAttribute("shoppingCart")!=null) {
				request.getRequestDispatcher("WEB-INF/ShoppingCart.jsp").forward(request, response);
			}else {
			request.getSession().setAttribute("shoppingCart", shoppingCart);
			request.getRequestDispatcher("WEB-INF/ShoppingCart.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
