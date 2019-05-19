package bookspkg;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class home
 */
@WebServlet("/home")
public class home extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    static Hashtable<Integer,Bookdets> books=new Hashtable<>();
    Hashtable<String,Hashtable<Integer,Integer>>cart=new Hashtable<>();
	PreparedStatement pst;
	ResultSet rs;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		
		 
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","shubham@mysql");
	        Statement st=cn.createStatement();
	        rs=st.executeQuery("select * from books");
	        pst = cn.prepareStatement("update books set qoh=? where bookid=? ");
	        while(rs.next())
	        {
	        	books.put(rs.getInt("bookid"),new Bookdets(rs));
	        	System.out.println("added - "+rs.getInt("bookid"));
	        }
	        
	       
	               	        
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("\ndriver not found - "+e.getMessage()+"\n");
		}
		catch(SQLException e)
		{
			System.out.println("\nsql exception - "+e.getMessage()+"\n");
		}
		
		ServletContext sc=getServletContext();
		sc.setAttribute("home", this);
		 
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy()
	{
		// TODO Auto-generated method stub
		try
		{
		  Enumeration<Integer> kz =books.keys();
		  while(kz.hasMoreElements())
		  {
		   int bookid = (Integer)kz.nextElement();
		   Bookdets bd=(Bookdets)books.get(bookid);
		   int qoh=bd.qoh;
		   pst.setInt(2, bookid);
		   pst.setInt(1,qoh);
		   pst.executeUpdate();
		   System.out.println("updating - "+bookid+" : "+qoh);
	      }
		}
		catch(SQLException e)
		{
		  System.out.println("SQL Alert [destroy] - "+e.getMessage());
		}
	
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
        HttpSession sn=request.getSession();		
		//ServletContext sc=getServletContext();
		
		
		String html="";
		html="<!DOCTYPE html><html><head><meta charset=\"ISO-8859-1\">"+
		"<title>Amazon</title>"+
		"</head>"+
		"<style>"+
        "ul {list-style-type: none;margin: 0 135px 0 0;padding: 0;overflow: hidden;background-color: #333;}"+

        "li {float:left;}"+

        "li a {"+
         "display: block;"+
         "color: white;"+
         "text-align: center;"+
           "padding: 14px 16px;"+
         "text-decoration: none;"+
         "}"+

"li a:hover {   background-color: #161;}"+
"</style>"+
"</head>"+
		"<body bgcolor=\"antiquewhite\"><center><h1 style=\"color:teal\" >Welcome to Online Book Store</h1>"+
		"</center><br>"+
		"<ul>"+
		  "<li><a href=\"#home\">Home</a></li>"+
		  "<li><a href=\"#news\">Fictiom</a></li>"+
		  "<li><a href=\"#contact\">Contact us</a></li>"+
		  "<li><a href=\"#about\">About</a></li>"+
		"</ul><br>"+
		"<div style=\"width:1600px;margin:0 auto;\">";


	try{
      	rs.beforeFirst();
      	while(rs.next())
      	{
      		html=html+
             "<div style=\"background-color:lightblue;positon:relative;width:250px;height:350px; float:left;margin:0px 30px 30px 30px;border-style:solid;border-width:7px;border-style:groove;border-color:#73AD21;border-radius:15px;\">"+  
      		 "<img  alt=\"Error\" src=\"Images/" +rs.getInt("bookid")+".jpg\" >"+
      		 "<p>Name :"+ rs.getString("title")+ "<br>Author :"+rs.getString("author")+"</p>"+
      		 "<a href=\"Checkout?name="+rs.getInt("bookid")+"\"><button style=\"background-color:teal;\">Buy Now</button></a>"+
      		 "<a href=\"GetBook?name="+rs.getInt("bookid")+"\"><button style=\"background-color:white;\">Add to cart</button></a>"+
      		 "</div>";
      	}
      	
	   }
	
	  catch(SQLException e)
	  {  
	   System.out.println("\nsql exception - "+e.getMessage()+"\n");
	  }
	
	  html=html+
			  "</div><br></body></html>";
	  
	  response.getWriter().println(html);
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
  
}  
