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

@WebServlet("/ModifyProduct")
public class ModifyProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LogicProduct logic; 
    
    public ModifyProduct() {
        super();
        logic = new LogicProduct();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Illegal Access ");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product productToModify = new Product();
		Float originalPrice = 0.0f;

		if (request.getParameter("id") != null) {
			System.out.println("id no es null (if n°1 true):"+ request.getParameter("id"));
		productToModify.setId(Integer.parseInt(request.getParameter("id")));
			System.out.println("  linea 43, id: "+productToModify.getId());
		originalPrice = logic.idSearch(productToModify.getId()).getPrice();
			System.out.println("   originalPrice:"+originalPrice);
			System.out.println("  Antes del segundo if, id:"+ productToModify.getId());
		}
		if (request.getParameter("name") != null) {	//to process the form that contained modified product's fields
			System.out.println("name no es null (if n°2 true):"+request.getParameter("name"));
			System.out.println("linea 50, id:"+productToModify.getId());
/* if(Integer.isNull(productToModify.getId())) */ productToModify.setId(Integer.parseInt(request.getParameter("id"))); 
			productToModify.setName(request.getParameter("name"));
			productToModify.setDescription(request.getParameter("description"));
			productToModify.setCategory(request.getParameter("category"));
			productToModify.setStock(Integer.parseInt(request.getParameter("stock")));
			productToModify.setPrice(Float.parseFloat(request.getParameter("price")));
			if(originalPrice != productToModify.getPrice()) { //"if the price that the Product had before using the form to modify attributes isn't the same as the one after sending the form, then move the old price to the variable oldPrice and the new price into the variable price"
				System.out.println("original price es distinto del precio actual (if n°4 true):"+ originalPrice);
				productToModify.setOldPrice(originalPrice);		//should i move this to the logic layer?
			}
			if (logic.modify(productToModify)) {			//sends the product loaded with the modified data to the DB, returns true for success, or false for Data layer exception
				System.out.println("se ejecutó logic.modify (if n°5 true)");
				LinkedList<Product> productList = logic.showAll();
				request.setAttribute("productList", productList);
				request.setAttribute("trigger", "clean");
				request.getRequestDispatcher("WEB-INF/ManageProduct.jsp").forward(request, response);
			} else {
				LinkedList<Product> productList = logic.showAll();
				request.setAttribute("productList", productList);
				request.setAttribute("trigger", "errorModify");
				request.getRequestDispatcher("WEB-INF/ManageProduct.jsp").forward(request, response);
			}	
	    }else if (request.getParameter("id") != null) {	//if the request has no "name" Attribute, but it has an "id" Attribute, it means that the user didn't sent the form with the modified product, but rather he sent a request by clicking on the modify button of an specific row of the Products table.
			request.setAttribute("originalProduct",logic.idSearch(productToModify.getId())); //<-- to show in ManageProduct the form with the fields already filled with the original values, i have to forward the Product obtained from the DB in order to show it in the form.
			LinkedList<Product> productList = logic.showAll();
			request.setAttribute("productList", productList);
			request.setAttribute("trigger", "showModify");	//i'll have to use a third type of attribute for the use case modify, because the attribute "modify" already shows the button to open the modify "form" in each row
			request.getRequestDispatcher("WEB-INF/ManageProduct.jsp").forward(request, response);
				
	    }else {	//show the "modify" buttons in each row of the Products table, basically forwarding the trigger Attribute "modify"
	    	LinkedList<Product> productList = logic.showAll();
			request.setAttribute("productList", productList);
			request.setAttribute("trigger", "modify");
			request.getRequestDispatcher("WEB-INF/ManageProduct.jsp").forward(request, response);
	    	
	    }
	}

}