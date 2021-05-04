package servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DataCard;
import entities.Card;

/**
 * Servlet implementation class AddCard
 */
@WebServlet("/AddCard")
public class AddCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       DataCard dc;
   
    public AddCard() {
        super();
        dc = new DataCard();
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
		Card card = new Card();
		card.setNumber(request.getParameter("number"));
		card.setSecurityCode(Integer.parseInt(request.getParameter("securitycode")));
		card.setDate(Date.valueOf(request.getParameter("date")));
		if(dc.add(card)){
			card = dc.getCard(card);
			request.getSession().setAttribute("card", card);
		}
		request.getRequestDispatcher("/PurchaseDetails").forward(request, response);
		
	}

}
