package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DataProduct;
import entities.Product;


@WebServlet("/FilterProduct")
public class FilterProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       DataProduct dp;

    public FilterProduct() {
        super();
        dp = new DataProduct();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		float maxPrice = Float.parseFloat(request.getParameter("maxPrice"));
		LinkedList<Product> filterList = dp.filterByPrice(maxPrice);
		request.setAttribute("filteredProducts", filterList);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
