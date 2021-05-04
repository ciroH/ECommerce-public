package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DataAddress;
import entities.Address;

/**
 * Servlet implementation class AddAddress
 */
@WebServlet("/AddAddress")
public class AddAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
       DataAddress da;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAddress() {
        super();
        da = new DataAddress();
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
		Address address = new Address();
		address.setStreet(request.getParameter("street"));
		address.setStreetNumber(request.getParameter("streetnumber"));
		address.setCity(request.getParameter("city"));
		address.setState(request.getParameter("state"));
		address.setCountry(request.getParameter("country"));
		address.setPostalCode(request.getParameter("postalcode"));
		if(da.add(address)){
			address = da.getAddress(address);
			request.getSession().setAttribute("address", address);
		}
		request.getRequestDispatcher("/PurchaseDetails").forward(request, response);
		
		
	}

}
