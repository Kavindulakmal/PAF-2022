package service;

import model.User;


//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/customer")
public class UserService {
	
	User userObj = new User(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readUsers() 
	 { 
		return userObj.readUsers();
	 } 
	
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertUsers(
	 @FormParam("fname") String fname, 
	 @FormParam("lname") String lname, 
	 @FormParam("email") String email, 
	 @FormParam("address") String address,
	 @FormParam("username") String username,
	 @FormParam("password") String password)
	{ 
	 String output = userObj.insertUsers(fname, lname, email, address,username,password); 
	return output; 
	}
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateUsers(String UserData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject fundObject = new JsonParser().parse(UserData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String cusID = fundObject.get("cusID").getAsString(); 
	 String fname = fundObject.get("fname").getAsString(); 
	 String lname = fundObject.get("lname").getAsString(); 
	 String email = fundObject.get("email").getAsString(); 
	 String address = fundObject.get("address").getAsString();
	 String username = fundObject.get("username").getAsString();
	 String password = fundObject.get("password").getAsString();
	String output = userObj.updateUsers(cusID, fname, lname, email, address,username,password); 
	return output; 
	}
	
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteProduct(String UserData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(UserData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String cusID = doc.select("cusID").text(); 
	 String output = userObj.deleteUsers(cusID); 
	return output; 
	}

}
