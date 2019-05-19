package bookspkg;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checkout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int bookid=Integer.parseInt(request.getParameter("name"));
        ServletContext sc=getServletContext();
		
		home h=(home)sc.getAttribute("home");
		
		String html="<!DOCTYPE html>"+    
		            "<html>"+
		            "<head>"+
		            "<meta charset=\"utf-8\">"+
				    "<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">"+
		            "</head>"+
				    "<body class=\"bg-danger\">"+
		            "<div class=\"container \" >"+
				    "<div align=\"center\" class=\"row align-items-center\">"+
				    "<h2 class= \"text-dark\">Your checkoutList</h2><br>"+
				    "<img alt=\"Error\" class=\"img-rounded\" src=\"Images/"+bookid+".jpg\"><br><br>"+
		            "<font face=\"lucida console\">Title  : "+home.books.get(bookid).title+"</font><br>"+
		            "<font face=\"lucida console\">Author : "+home.books.get(bookid).author+"</font><br>"+
		            "<button type=\"button\" class=\"btn btn-primary\">Procced to Payment</button>"+
		            "</div>"+
		            "</div>"+
		            "</body>"+
		            "</html>";
		response.getWriter().println(html);
		             
		            
				    
		            
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
