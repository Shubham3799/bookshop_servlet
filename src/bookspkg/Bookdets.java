package bookspkg;

import java.sql.*;

public class Bookdets
{
	int bookid;
	int price;
	int qoh;
	String author;
	String title;
	int discount;
	
    Bookdets(ResultSet rs)
    {        
      	try
    	{
         bookid=rs.getInt("bookid");
         title=rs.getString("title");
         author=rs.getString("author");
         discount=rs.getInt("discount");
         qoh=rs.getInt("qoh");
         price=rs.getInt("price");
    	}
      	
    	catch(SQLException e)
    	{
    	 System.out.println("\nsql alert - "+e.getMessage()+"\n");
    	}
    }
    
    int getQoh()
    {
    	return qoh;
    }

}
