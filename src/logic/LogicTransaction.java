package logic;

import java.util.HashMap;

import data.DataTransaction;
import entities.Product;
import entities.Transaction;

public class LogicTransaction {

 public	Transaction registerTransaction(int userId, int addressId, int cardId, HashMap<Integer,Integer> shoppingCart){	//shoppingCart has the if of each product as the Key and the quantity as the Value
		DataTransaction dataT = new DataTransaction();
		Transaction transaction = new Transaction();
		float currentProductPrice;
		float currentProductTotal;
		int currentProductQty;
//		int idSavedTransaction = 0;
		transaction.setIdUser(userId);
		transaction.setIdAddress(addressId);
		transaction.setIdCard(cardId);
		HashMap<Product,Integer> cartToPrint =  new HashMap<>();
		LogicProduct lp = new LogicProduct();
	/******		Generating the shopping cart with all the details from products, and calculating the total of the Transaction:	******/
		for(HashMap.Entry<Integer,Integer> product : shoppingCart.entrySet()){
			cartToPrint.put(lp.idSearch(product.getKey()) , product.getValue());
			currentProductPrice = (lp.idSearch(product.getKey()).getPrice());
			currentProductQty = product.getValue();
			currentProductTotal= (currentProductPrice * currentProductQty);
			transaction.setTotal(transaction.getTotal() + currentProductTotal); 
		}
	/***** 	Setting the serverDate:	*****/	
		long mlSeconds = System.currentTimeMillis();
		transaction.setServerDate(mlSeconds);
	/*****	Transforming the cart into a string	*****/	
		String printedCart = "+";
		for (int i = 0; i < 49; i++) printedCart += "-";
		printedCart+="+qty--+total---+ <br>";
		for(HashMap.Entry<Product,Integer> product : cartToPrint.entrySet()) {
			for (int i = 0; i < 50; i++) printedCart += "-";
			printedCart+="|-----|--------| <br>";
			printedCart+="|"+product.getKey().getName();
			for (int i = 0; i < (49 - product.getKey().getName().length()); i++) printedCart += " ";
			printedCart+="|"+product.getValue();
			for (int i = 0; i < (5 - product.getValue().toString().length()); i++) printedCart+=" ";
			printedCart+="|"+product.getValue() * product.getKey().getPrice();
			for (int i = 0; i < (8 - (String.valueOf((product.getValue() * product.getKey().getPrice()))).length()); i++) printedCart+=" ";
			printedCart+="| <br>";			
		}	
		printedCart+="+";
		for (int i = 0; i < 49; i++) printedCart += "¯";
		printedCart+="+-----+"+transaction.getTotal();
		for (int i = 0; i < (8 - String.valueOf(transaction.getTotal()).length()); i++) printedCart+=" ";
		printedCart+="+ <br>";
		transaction.setDetail(printedCart);	
	/*****	Calling DataTransaction	*****/	
//		idSavedTransaction = dataT.saveTransaction(transaction).getIdTransaction();
		if (dataT.saveTransaction(transaction)) {
/*			transaction.setIdTransaction(idSavedTransaction);
			if (!dataT.saveDetails(transaction)) {	//if saving the Transaction Details fails, this if block will be true and the Transaction will be deleted from the DB
														//-deprecated comment: must define a way of make them both true or false, maybe if saveTransaction or saveDetails fails, erasing the transaction from the DB and returning an error would be the best
			//deletes Transaction from the DB	
			//changes transaction.idTransaction to 0 and returns it	
			}
*/		}
		
	return transaction;	
	}
	
}
