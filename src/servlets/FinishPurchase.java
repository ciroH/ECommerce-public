package servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Address;
import entities.Card;
import entities.Product;
import entities.Transaction;
import entities.User;
import logic.LogicTransaction;

/**
 * Servlet implementation class FinishPurchase
 */
@WebServlet("/FinishPurchase")
public class FinishPurchase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       LogicTransaction logic;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinishPurchase() {
        super();
        logic = new LogicTransaction();
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
		User user = (User)request.getSession().getAttribute("user");
		Address address = (Address)request.getSession().getAttribute("address");
		Card card = (Card)request.getSession().getAttribute("card");
		HashMap<Integer,Integer> shoppingCart = new HashMap<>();
		shoppingCart.putAll((HashMap<Integer, Integer>)request.getSession().getAttribute("shoppingCart"));
				//remember to empty the cart (and the address and card) from the session once the transaction is registered in the DB
		Transaction transaction = logic.registerTransaction(user.getId(), address.getId(), card.getId(), shoppingCart);
		request.setAttribute("ticket", transaction);
/*		if (transaction.getIdTransaction() == 0) {
			request.getRequestDispatcher("WEB-INF/TransactionError.jsp").forward(request, response);
		}
*/		
		request.getSession().removeAttribute("shoppingCart");
		request.getRequestDispatcher("WEB-INF/TransactionTicket.jsp").forward(request, response);
		
	}

}
