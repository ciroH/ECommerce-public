package logic;

import java.util.LinkedList;

import data.DataProduct;
import entities.Product;

public class LogicProduct {
	DataProduct dp = new DataProduct();
	
	public LinkedList<Product> showAll(){
		
		LinkedList<Product> productList = new LinkedList<>();
		productList = dp.getAll();
		return productList;
	}
	
	public boolean add(Product product) {
		
		return dp.add(product);	
	}
	
	public boolean delete(Product product) {
		
		return dp.delete(product);
	}
	public Product idSearch(int id) {
		
		return dp.searchById(id);
	}
	
	public boolean modify(Product product) {
		
		return dp.modify(product);
	}
}
