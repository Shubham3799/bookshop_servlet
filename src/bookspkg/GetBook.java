package bookspkg;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
/**
 * Servlet implementation class GetBook
 */
@WebServlet("/GetBook")
public class GetBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Hashtable <Integer,Hashtable<Integer,Integer> > kart=new Hashtable<>();
	String html;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext sc=getServletContext();
		
		home h=(home)sc.getAttribute("home");
		
		
		
		int bookid=Integer.parseInt(request.getParameter("name"));
		
		int qnt=1;
		int cnt=0;
		
		String session;
		//System.out.println(arr.length);
		
		
		
		HttpSession sn=request.getSession(false);
		 if(sn==null)
		 {
			 html="<html><body><h1>Session Expired 404..</h1></body></html>";
			 h.cart.clear();
		 }
		 
		 else
		 {
			 
			 session=sn.getId();
			 if(!(h.cart.containsKey(session)))
				{   
					System.out.println("IF");
					Hashtable<Integer,Integer> ht=new Hashtable<Integer,Integer>();
					ht.put(bookid, qnt);
					h.cart.put(session,ht);
					//h.kart.put(cartid,ht);	
				}
				
				else
					
				{
					 System.out.println("ELSE");
					
					Hashtable <Integer,Integer> ht=h.cart.get(session);
					
					if(ht.containsKey(bookid))
					{
						qnt=qnt+ht.get(bookid);
						ht.replace(bookid, qnt);
					}
					else
					{
						ht.put(bookid,qnt);
					}
					h.cart.put(session,ht);
					
				}
				
				Hashtable<Integer,Integer>ht=new Hashtable<>();
				Enumeration<String> kz =h.cart.keys();
				
				while(kz.hasMoreElements())
				  {
				   String str = kz.nextElement();
				   ht=h.cart.get(str);
				   Enumeration <Integer> ks = ht.keys();
				   while(ks.hasMoreElements())
				   {
				    int id=ks.nextElement();
				    cnt+=ht.get(id);
				   }
				  }

		 html="<html>"
				+ "<head><link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">"
				+ "<link href=\"css/bootstrap.min.css\" rel=\"stylesheet\" />"
				+ "</head>"
		 		+ "<body bgcolor=blue>"
				
				+ "<div align=right>"
		 		+ "<form action =logout method=get>"
				+ "<input type=submit value=logout>"
		 		+ "</form>"
				+"</div>"
		 		
		 		+ "<i><a href=\"#\"><span class=\"glyphicon glyphicon-shopping-cart\" style=\"font-size: 35px\"></span><span class=\"badge\">"+cnt+"</span></a></i>"
		 		+"<br>"
		 		+ "<h1 align =center style=\"color:teal;\">Your selected book</h1>"
		 		+"<div align=center>"
		 		+"<img alt=\"Error\" src=\"Images/"+bookid+".jpg\"><br><br><br>"+
	             "<font face=\"lucida console\">Title  : "+home.books.get(bookid).title+"</font><br>"+
	             "<font face=\"lucida console\">Author : "+home.books.get(bookid).author+"</font><br>"+
	             "<font face=\"lucida console\">Price : Rs."+home.books.get(bookid).price+"</font>"+
	             "<font style=\"color:teal;\"face=\"lucida console\">&nbsp;Discount : Rs."+home.books.get(bookid).discount+"</font>"+
	             "</div>"+
	             "</body></html>";
		 }
		 
		 response.getWriter().println(html);
		
	   	 home.books.get(bookid).qoh=home.books.get(bookid).qoh-1;
	     System.out.println(home.books.get(bookid).qoh);
		
		
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
	}

}
