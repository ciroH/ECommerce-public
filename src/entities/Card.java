package entities;

import java.sql.Date;

public class Card {
int id,securityCode;
String number,logo;
Date date;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getSecurityCode() {
	return securityCode;
}
public void setSecurityCode(int securityCode) {
	this.securityCode = securityCode;
}
public String getNumber() {
	return number;
}
public void setNumber(String number) {
	this.number = number;
}
public String getLogo() {
	return logo;
}
public void setLogo(String logo) {
	this.logo = logo;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}

}
