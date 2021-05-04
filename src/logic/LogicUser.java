package logic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.security.*;


import data.DataUser;
import entities.User;

public class LogicUser{
  DataUser du = new DataUser();
	public User processLogIn(User loginUser) {	
		//recibe usuario mapeado(solo con mail y password cargados)
		//reemplaza el password por el hash del password
		//le pasa el usuario a DataUser.getOnLogin()
		//recibe el user completo (o en null), lo devuelve --el Servlet tiene que verificar si el User recibido es null
		
	/*	hashedPass = getHash(loginUser.getPassword())
		loginUser.setPassword(hashedPass);
	*/  
		return du.getOnLogin(loginUser);
	}
	
	
	public boolean processSignIn(User signInUser) {
		
		
		return du.signIn(signInUser);
	}
		
/* 

	//Separar en una nueva clase del package logic

	public String getHash(String password) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		
		
	return "";
			}
*/
	
/*	public void setPic(//String imagePath, BufferedImage buffImg) {
		File homeDir = new File(System.getProperty("user.dir"));
		String pathSeparator = System.getProperty("file.separator");
	//	File imageFile = new File(homeDir,);
	//	BufferedImage pic = new BufferedImage(imageFile);

		System.out.println("user.dir "+System.getProperty("user.dir"));
		System.out.println("user.home "+System.getProperty("user.home"));
		System.out.println("user.name "+System.getProperty("user.name"));
		System.out.println("path.separator "+System.getProperty("path.separator"));
		System.out.println("file.separator "+System.getProperty("file.separator"));
		System.out.println("java.class.path "+System.getProperty("java.class.path"));
		File imageDir = Path
		//System.out.println(ResourcesPlugin.getWorkspace().getRoot().getLocation().toString());
	}
*/

}