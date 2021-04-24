package shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		// 1st query ==============================================================================================
		if(request.getParameter("q1") != null)
		{ 
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","");                             
				String query = "DELETE FROM customer where dob  = ?";
				PreparedStatement ps = conn.prepareStatement(query);

				ps.setString(1, request.getParameter("dob"));

				int x = ps.executeUpdate(); 

				if(x >= 1)
				{
					out.println("<h3>"+x+" Row Deleted Successfully</h3>");
				}

				else{
					out.println("<h3>No Data Found</h3>");
					out.println("<h3>0 Row Deleted</h3>");
				}
			}

			catch(Exception e)
			{
				e.printStackTrace();
			}

		}

		// 2nd query ======================================================================================
		else if(request.getParameter("q2") != null)
		{
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","");

				String query = "DELETE FROM inventory where  location = upper(?)";
				PreparedStatement ps = conn.prepareStatement(query);

				ps.setString(1,request.getParameter("loc"));

				int x = ps.executeUpdate();

				if(x >= 1)
				{
					out.println("<h3>"+x+" Row Deleted Successfully</h3>");
				}

				else{
					out.println("<h3>No Data Found</h3>");
					out.println("<h3>0 Row Deleted</h3>");
				}
			}

			catch(Exception e)
			{
				e.printStackTrace();
			}

		}

		// 3rd query =================================================================================================
		else if(request.getParameter("q3") != null)
		{

			int o_total = Integer.parseInt(request.getParameter("o_total"));

			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","");

				String query = "DELETE  FROM orders  where  order_total  < ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setInt(1, o_total);

				int x = ps.executeUpdate();

				if(x >= 1)
				{
					out.println("<h3>"+x+" Row Deleted Successfully</h3>");
				}

				else{
					out.println("<h3>No Data Found</h3>");
					out.println("<h3>0 Row Deleted</h3>");
				}
			}

			catch(Exception e)
			{
				e.printStackTrace();
			}

		}


		// Query 4==============================================================================================

		else if(request.getParameter("q4") != null)
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","");

				String query = "DELETE FROM product_info where supplier= upper(?)";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1,request.getParameter("s_name"));

				int x = ps.executeUpdate();

				if(x >= 1)
				{
					out.println("<h3>"+x+" Row Deleted Successfully</h3>");
				}

				else{
					out.println("<h3>No Data Found</h3>");
					out.println("<h3>0 Row Deleted</h3>");
				}
			}

			catch(Exception e)
			{
				e.printStackTrace();
			}

		}

		//5th query==========================================================================================
		else if(request.getParameter("q5") != null)
		{
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","");
				
				String query = "DELETE  FROM order_items where  unit_price = ? AND quantity = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				float u_price = Float.parseFloat(request.getParameter("u_price"));
				int qty = Integer.parseInt(request.getParameter("qty"));
				ps.setFloat(1, u_price);
				ps.setInt(2,qty);

				int x = ps.executeUpdate();

				if(x >= 1)
				{
					out.println("<h3>"+x+" Row Deleted Successfully</h3>");
				}

				else{
					out.println("<h3>No Data Found</h3>");
					out.println("<h3>0 Row Deleted</h3>");
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
