package servlets;

import entities.User;
import logic.LogicUser;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignIn
 */
@WebServlet("/SignIn")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
      LogicUser logic;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignIn() {
        super();
        logic = new LogicUser();
        // TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		if (request.getParameter("inputPassword").equals(request.getParameter("inputPasswordCheck"))) {
		User userSign = new User();
		userSign.setMail(request.getParameter("inputEmail"));
		userSign.setName(request.getParameter("name"));
		userSign.setPassword(request.getParameter("inputPassword"));
		if (request.getParameter("inputPic") != null) {
			//create a Buffered Image to save the form Parameter corresponding to the image
		/*	BufferedImage profPic = */ System.out.println(request.getParameter("inputPic"));
		}
		if(logic.processSignIn(userSign)) { //tries to insert the user into the DB, if inserted correctly, the data layer calls another method to insert the profile pic
			userSign = logic.processLogIn(userSign);
		//	logic.setPic("${pageContext.request.contextPath}/WebContent/ImageResources/UserImage/"+userSign.getId(),BufferedImage);
			//logic.setPic(/*userSign.getId(),BufferedImage*/);
			//tengo que ver como inserto la imagen de perfil acá(Talvez, si el usuario se registró correctamente, uso su id para crear la carpeta para la imagen, la guardo, y luego modifico el user en la db usando su id, para guardar la imagen de perfil(Podría luego aprovechar el metodo en la data layer, para añadir la opcion de cambiar la foto de perfil del usuario a futuro). eso, o creo una nueva tabla para la profilepic, y en el login uso un join para recuperarla junto con los datos del user)
			request.getSession().setAttribute("user", userSign); //executes the login process, in order to save the User (Selected from the DB) into the Attribute "user" of this Session
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			
		} else {
			request.setAttribute("warning","existing"); //"warning, the email address already exist in the DataBase"
			request.getRequestDispatcher("/SignInForm.jsp").forward(request, response);
		}
		
		}else {
			//response.getWriter().append("verify Password"); 
			 request.setAttribute("warning","password"); //<-- so when the servlet forwards the request to the same jsp, that jsp verifies request.getAttribute("warning"), and if it isn't null, the jsp shows a sign asking the user with the corresponding warning
			 request.getRequestDispatcher("/SignInForm.jsp").forward(request, response);
		
		}
	}

}
