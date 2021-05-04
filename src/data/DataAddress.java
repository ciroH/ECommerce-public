package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Address;


public class DataAddress {

	public boolean add(Address address) {
		PreparedStatement addPstmt = null;
		
		try {

			addPstmt = DbConnector.getInstance().getConn().prepareStatement("INSERT INTO address (street,streetnumber,city,state,country,postalcode) VALUES (?,?,?,?,?,?)");
			addPstmt.setString(1,address.getStreet());
			addPstmt.setString(2,address.getStreetNumber());
			addPstmt.setString(3,address.getCity());
			addPstmt.setString(4,address.getState());
			addPstmt.setString(5,address.getCountry());
			addPstmt.setString(6,address.getPostalCode());

			addPstmt.executeUpdate();
		
	} catch (SQLException e) {
		e.printStackTrace();
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

	public Address getAddress(Address searchedAddress) {
		Address address = new Address();
		PreparedStatement getAddressStatement = null;
		ResultSet rs = null;
		try {
			getAddressStatement = DbConnector.getInstance().getConn().prepareStatement("select id,street,streetnumber,city,state,country,postalcode from address where street=? and streetnumber=? and postalcode=? ");
			getAddressStatement.setString(1, searchedAddress.getStreet());
			getAddressStatement.setString(2, searchedAddress.getStreetNumber());
			getAddressStatement.setString(3, searchedAddress.getPostalCode());
			
			rs = getAddressStatement.executeQuery();
			if (rs!=null && rs.next()) {
				address.setId(rs.getInt("id"));
				address.setStreet(rs.getString("street"));
				address.setStreetNumber(rs.getString("streetnumber"));
				address.setCity(rs.getString("city"));
				address.setState(rs.getString("state"));
				address.setCountry(rs.getString("country"));
				address.setPostalCode(rs.getString("postalcode"));
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}finally {
			try {
				if (rs!=null) {
					rs.close();
				}
				if (getAddressStatement!=null) {
					getAddressStatement.close();
				}
				DbConnector.getInstance().releaseConn();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	return address;
	}
		
	
	
/*	public LinkedList<Address> search(Address ) {	
		//for verifying if the address doesn't exist already **for this user** (searching for the address, then his id, then checking transdetail)
		
	}
*/
	
	
}