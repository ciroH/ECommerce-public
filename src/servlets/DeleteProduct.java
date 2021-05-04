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
 * Servlet implementation class DeleteProduct
 */
@WebServlet("/DeleteProduct")
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
      LogicProduct logic; 
	
    public DeleteProduct() {
        super();
        logic = new LogicProduct();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Illegal Access");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try{		
		if (request.getParameter("id") != null) {
			Product productToDelete = new Product();
			productToDelete.setId(Integer.parseInt(request.getParameter("id"))); //NumberFormatException: For input string: ""
			if (logic.delete(productToDelete)) {
				LinkedList<Product> productList = logic.showAll();
				request.setAttribute("productList", productList);
				request.setAttribute("trigger", "clean");
				request.getRequestDispatcher("WEB-INF/ManageProduct.jsp").forward(request, response);
			} else {
				LinkedList<Product> productList = logic.showAll();
				request.setAttribute("productList", productList);
				request.setAttribute("trigger", "errorDelete");
				request.getRequestDispatcher("WEB-INF/ManageProduct.jsp").forward(request, response);
			}
			
		}else {
			LinkedList<Product> productList = logic.showAll();
			request.setAttribute("productList", productList);
			request.setAttribute("trigger", "delete");
			request.getRequestDispatcher("WEB-INF/ManageProduct.jsp").forward(request, response);
		}
		
}catch (Exception e) {
	e.printStackTrace(response.getWriter());
}
	}

}
