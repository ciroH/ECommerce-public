package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Product;


public class DataProduct {
	public LinkedList<Product> getAll() {	//logic will group the returned List later into categories (NULL equals 'other' category)
		LinkedList<Product> productList = new LinkedList<>();
		Product item;
		PreparedStatement getAllStatement = null;
		ResultSet rs = null;
		try {
			getAllStatement = DbConnector.getInstance().getConn().prepareStatement("select id,name,description,price,oldprice,stock,category from product where stock > 0 order by id desc");
			rs = getAllStatement.executeQuery();
			
			while (rs!=null && rs.next()) {
				item = new Product();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setDescription(rs.getString("description"));
				item.setPrice(rs.getInt("price"));
				item.setOldPrice(rs.getInt("oldprice"));
				item.setStock(rs.getInt("stock"));
				item.setCategory(rs.getString("category"));
				//item.setImage
				productList.add(item);
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}finally {
			try {
				if (rs!=null) {
					rs.close();
				}
				if (getAllStatement!=null) {
					getAllStatement.close();
				}
				DbConnector.getInstance().releaseConn();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	return productList;
	}
		public LinkedList<Product> getByCategory(String category) {	
		LinkedList<Product> productList = new LinkedList<>();
		Product item;
		PreparedStatement categoryStatement = null;
		ResultSet rs = null;
		try {
			categoryStatement = DbConnector.getInstance().getConn().prepareStatement("select id,name,description,price,oldprice,stock,category from product where category = ? ");
			categoryStatement.setString(1, category);
			rs = categoryStatement.executeQuery();
			
			while (rs!=null && rs.next()) {
				item = new Product();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setDescription(rs.getString("description"));
				item.setPrice(rs.getInt("price"));
				item.setOldPrice(rs.getInt("oldprice"));
				item.setStock(rs.getInt("stock"));
				item.setCategory(rs.getString("category"));
				//item.setImage
				productList.add(item);
			}
			
		} catch (SQLException e) {
				e.printStackTrace();
		}finally {
			try {
				if (rs!=null) {
					rs.close();
				}
				if (categoryStatement!=null) {
					categoryStatement.close();
				}
				DbConnector.getInstance().releaseConn();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	return productList;
	}
		public LinkedList<String> getCategories(){
			LinkedList<String> categories = new LinkedList<>();
			PreparedStatement categoryStatement = null;
			ResultSet rs = null;
			try {
				categoryStatement = DbConnector.getInstance().getConn().prepareStatement("select distinct category from product");
				rs = categoryStatement.executeQuery();
				
				while (rs!=null && rs.next()) {
					categories.add(rs.getString("category"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if (rs!=null) {
						rs.close();
					}
					if (categoryStatement!=null) {
						categoryStatement.close();
					}
					DbConnector.getInstance().releaseConn();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
	return categories;	
	}
		public LinkedList<Product> search(String searchInput) {	
			LinkedList<Product> productList = new LinkedList<>();
			Product item;
			PreparedStatement searchStatement = null;
			ResultSet rs = null;
			try {
				searchStatement = DbConnector.getInstance().getConn().prepareStatement("select id,name,description,price,oldprice,stock,category from product where stock > 0 AND name LIKE ? order by id desc");
				searchStatement.setString(1,"%"+searchInput+"%");	//preguntarle a Adrián porqué es "%" envez de "'%" y "%'" 
				rs = searchStatement.executeQuery();

				while (rs!=null && rs.next()) {
					item = new Product();
					item.setId(rs.getInt("id"));
					item.setName(rs.getString("name"));
					item.setDescription(rs.getString("description"));
					item.setPrice(rs.getInt("price"));
					item.setOldPrice(rs.getInt("oldprice"));
					item.setStock(rs.getInt("stock"));
					item.setCategory(rs.getString("category"));
					//item.setImage

					productList.add(item);
				}
			} catch (SQLException e) {
					e.printStackTrace();
			}finally {
				try {
					if (rs!=null) {
						rs.close();
					}
					if (searchStatement!=null) {
						searchStatement.close();
					}
					DbConnector.getInstance().releaseConn();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		return productList;
		}
		
		public boolean add(Product product) {
			PreparedStatement addPstmt = null;
			if (!(search(product.getName()).isEmpty())) {	//felt that the "LIKE" in the SQL syntax of the method search was enought; items called "mouse" or other generic short names shouln't be used when adding new products
				return false;			//false means that there's a product already on the database with the same name or a similar one
			}
			try {

					addPstmt = DbConnector.getInstance().getConn().prepareStatement("INSERT INTO product (name,description,price,stock,category) VALUES (?,?,?,?,?)");
					addPstmt.setString(1, product.getName());
					addPstmt.setString(2,product.getDescription());
					addPstmt.setFloat(3,product.getPrice());
					addPstmt.setInt(4,product.getStock());
					addPstmt.setString(5, product.getCategory());
					//add photos
					addPstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				// devolver error de conexión a la db como un throwException al jsp de "insertar producto"
				// y si devuelvo false?
			} finally {
				
				try {
					if (addPstmt!=null) {
						addPstmt.close();
					}
					DbConnector.getInstance().releaseConn();
						
				} catch (Exception e2) {
					e2.printStackTrace();
					//return(false);
				}
			}
				return true;
			
		}
		
		public boolean delete(Product product) {	//if it returns true, the product is deleted from the linkedList that's on the servlet too, instead of doing another select. after that, the updated table is shown on the jsp
			PreparedStatement addPstmt = null;
			try {

					addPstmt = DbConnector.getInstance().getConn().prepareStatement("DELETE FROM product WHERE id = ?");
					addPstmt.setInt(1, product.getId());

					addPstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
				// devolver error de conexión a la db como un throwException al jsp de "insertar producto"
			} finally {
				
				try {
					if (addPstmt!=null) {
						addPstmt.close();
					}
					DbConnector.getInstance().releaseConn();
						
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
				return true;	
					}
		public boolean modify(Product product) {
			PreparedStatement modPstmt = null;
			int idParamNumber;
			try {
				if(product.getOldPrice() != 0.0f) {		//usar una función en logic (se debe pasar por esa capa en caso de ejecutar un modify)
														//que mueva el valor de price a oldPrice si al comparar Product.price con el valor del field de price no hay un match.
					
					modPstmt =DbConnector.getInstance().getConn().prepareStatement("UPDATE product SET name=?, description=?, category=?, price=?, stock=?, oldprice=?	WHERE id=?");
					modPstmt.setFloat(6, product.getOldPrice());	
					idParamNumber = 7;
				}else {
					modPstmt =DbConnector.getInstance().getConn().prepareStatement("UPDATE product SET name=?, description=?, category=?, price=?, stock=?	WHERE id=?");									
					idParamNumber = 6;
				}
														
				modPstmt.setString(1, product.getName());
				modPstmt.setString(2, product.getDescription());
				modPstmt.setString(3, product.getCategory());
				modPstmt.setFloat(4, product.getPrice());
				modPstmt.setInt(5, product.getStock());
				modPstmt.setInt(idParamNumber, product.getId());
				System.out.println("Data Layer Product Id:" + product.getId());

				
				modPstmt.executeUpdate();

			}catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				try {
					if (modPstmt !=null) {
						modPstmt.close();
					}
					DbConnector.getInstance().releaseConn();
				} catch (Exception e2) {
					e2.printStackTrace();
				}	
			}
			return true;		
		}
		public Product searchById(int id) {
			Product item = null;
			PreparedStatement idSearchStatement = null;
			ResultSet rs = null;
			try {
				idSearchStatement = DbConnector.getInstance().getConn().prepareStatement("select id,name,description,price,oldprice,stock,category from product where id = ?");
				idSearchStatement.setInt(1, id);
				rs = idSearchStatement.executeQuery();
				if (rs!=null && rs.next()) {
					item = new Product();
					item.setId(rs.getInt("id"));
					item.setName(rs.getString("name"));
					item.setDescription(rs.getString("description"));
					item.setPrice(rs.getInt("price"));
					item.setOldPrice(rs.getInt("oldprice"));
					item.setStock(rs.getInt("stock"));
					item.setCategory(rs.getString("category"));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}finally {
				try {
					if (rs!=null) {
						rs.close();
					}
					if (idSearchStatement!=null) {
						idSearchStatement.close();
					}
					DbConnector.getInstance().releaseConn();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		return item;
		}
		public LinkedList<Product> filterByPrice(float maxPrice) {	
		LinkedList<Product> productList = new LinkedList<>();
		Product item;
		PreparedStatement filterStatement = null;
		ResultSet rs = null;
		try {
			filterStatement = DbConnector.getInstance().getConn().prepareStatement("select id,name,description,price,oldprice,stock,category from product where price <= ? ");
			filterStatement.setFloat(1, maxPrice);
			rs = filterStatement.executeQuery();
			
			while (rs!=null && rs.next()) {
				item = new Product();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setDescription(rs.getString("description"));
				item.setPrice(rs.getInt("price"));
				item.setOldPrice(rs.getInt("oldprice"));
				item.setStock(rs.getInt("stock"));
				item.setCategory(rs.getString("category"));
				//item.setImage
				productList.add(item);
			}
			
		} catch (SQLException e) {
				e.printStackTrace();
		}finally {
			try {
				if (rs!=null) {
					rs.close();
				}
				if (filterStatement!=null) {
					filterStatement.close();
				}
				DbConnector.getInstance().releaseConn();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	return productList;
	}
		
}
