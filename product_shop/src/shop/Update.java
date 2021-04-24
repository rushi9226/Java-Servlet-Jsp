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
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
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

				float m_price = Float.parseFloat(request.getParameter("m_price"));
				float l_price = Float.parseFloat(request.getParameter("l_price"));
				String query = "UPDATE product_info set min_price = ? where list_price > ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setFloat(1, m_price);
				ps.setFloat(2,l_price);

				int x = ps.executeUpdate();

				if(x >= 1)
				{
					out.println("<h3>"+x+" Row Updated Successfully</h3>");
				}

				else{
					out.println("<h3>0 Row Updated</h3>");
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

			int cust_id = Integer.parseInt(request.getParameter("cust_id"));
			int status = Integer.parseInt(request.getParameter("status"));
			try{


				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","");


				String query = "UPDATE  orders set order_status = ? where  customer_id = ?";
				PreparedStatement ps = conn.prepareStatement(query);


				ps.setInt(1,status);
				ps.setInt(2, cust_id);

				int x = ps.executeUpdate();

				if(x >= 1)
				{
					out.println("<h3>"+x+" Row Updated Successfully</h3>");
				}

				else{
					out.println("<h3>0 Row Updated</h3>");
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

			int p_id = Integer.parseInt(request.getParameter("p_id"));

			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","");

				String query = "UPDATE  inventory set location = upper(?) where  p_id = ?";
				PreparedStatement ps = conn.prepareStatement(query);


				ps.setString(1,request.getParameter("loc"));
				ps.setInt(2, p_id);

				int x = ps.executeUpdate();


				if(x >= 1)
				{
					out.println("<h3>"+x+" Row Updated Successfully</h3>");
				}

				else{
					out.println("<h3>0 Row Updated</h3>");
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
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","");

				String query = "UPDATE  customer set email = ? where  cust_first_name = upper(?)";
				PreparedStatement ps = conn.prepareStatement(query);


				ps.setString(1,request.getParameter("email"));
				ps.setString(2, request.getParameter("c_name"));

				int x = ps.executeUpdate();


				if(x >= 1)
				{
					out.println("<h3>"+x+" Row Updated Successfully</h3>");
				}

				else{
					out.println("<h3>0 Row Updated</h3>");
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

				String query = "UPDATE  order_items set quantity= ? where  product_id = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				int p_id = Integer.parseInt(request.getParameter("p_id"));
				int qty = Integer.parseInt(request.getParameter("qty"));

				ps.setInt(1, qty);
				ps.setInt(2,p_id);

				int x = ps.executeUpdate();

				if(x >= 1)
				{
					out.println("<h3>"+x+" Row Updated Successfully</h3>");
				}

				else{
					out.println("<h3>0 Row Updated</h3>");
				}

			}

			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
