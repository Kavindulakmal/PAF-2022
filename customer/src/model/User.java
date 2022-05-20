package model;

import java.sql.*;


public class User {
	
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/yash", "root", ""); 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 } 
	
	//insert user
	public String insertUsers( String fname, String lname, String email, String address,String username,String password) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 // create a prepared statement
	 String query = " insert into customer (`cusID`,`fname`,`lname`,`email`,`address`,`username`,`password`)"
	 + " values (?, ?, ?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, fname); 
	 preparedStmt.setString(3, lname); 
	 preparedStmt.setString(4, email);
	 preparedStmt.setString(5, address);
	 preparedStmt.setString(6, username);
	 preparedStmt.setString(7, password);
	 
	// execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while inserting the item."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
	
	//read customer
	public String readUsers() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>first name</th>" +
	 "<th>last name</th>" + 
	 "<th>email</th>" +
	 "<th>address</th>" +
	 "<th>username</th>" +
	 "<th>password</th>" +
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from customer"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String cusID = Integer.toString(rs.getInt("cusID")); 
	 String fname = rs.getString("fname"); 
	 String lname = rs.getString("lname"); 
	 String email = rs.getString("email");
	 String address = rs.getString("address");
	 String username = rs.getString("username");
	 String password = rs.getString("password");
	 // Add into the html table
	 output += "<tr><td>" + fname + "</td>"; 
	 output += "<td>" + lname + "</td>"; 
	 output += "<td>" + email + "</td>"; 
	 output += "<td>" + address + "</td>";
	 output += "<td>" + username + "</td>";
	 output += "<td>" + password + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"+ "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='itemID' type='hidden' value='" + cusID 
	 + "'>" + "</form></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	//update
	public String updateUsers(String cusID,String fname, String lname, String email, String address,String username,String password)
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE customer SET fname=?,lname=?,email=?,address=?,username=?,password=? WHERE cusID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, fname); 
		 preparedStmt.setString(2, lname); 
		 preparedStmt.setString(3, email);
		 preparedStmt.setString(4, address);
		 preparedStmt.setString(5, username);
		 preparedStmt.setString(6, password);
		 preparedStmt.setInt(7, Integer.parseInt(cusID)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while updating the item."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
	
	
	//delete 
	public String deleteUsers(String cusID) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from customer where cusID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(cusID)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while deleting the item."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 

}
