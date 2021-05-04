package servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Product;
import logic.LogicProduct;

/**
 * Servlet implementation class PurchaseDetails
 */
@WebServlet("/PurchaseDetails")
public class PurchaseDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       LogicProduct logic;

    public PurchaseDetails() {
        super();
        logic = new LogicProduct();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().getAttribute("shoppingCart"); //delete this line
		HashMap<Integer,Integer> shoppingCart = new HashMap<>();
		HashMap<Product,Integer> cartToShow =  new HashMap<>();
		shoppingCart.putAll((HashMap<Integer,Integer>)request.getSession().getAttribute("shoppingCart"));
		for(HashMap.Entry<Integer,Integer> product : shoppingCart.entrySet()){
			cartToShow.put(logic.idSearch(product.getKey()) , product.getValue());
		}
		request.setAttribute("cartToShow", cartToShow);
		request.getRequestDispatcher("WEB-INF/ShowPurchaseDetails.jsp").forward(request, response);
	}

}
