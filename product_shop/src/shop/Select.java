package shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Select
 */
@WebServlet("/Select")
public class Select extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		if(request.getParameter("q1") != null) {
			out.println("<h2 align='center'>Product Data</h2><br>");

			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("Select * from product_info");

				out.println("<html><head><title>Select Query</title></head><body>");
				out.println("<table align='center' border ='3px'  width=70% >");
				out.println("<tr><th>Product Name</th><th>Description</th><th>Supplier name</th><th>List Price</th><th>Min Price</th></tr>");
				
				if(rs.next()) {
					rs.beforeFirst();
				while(rs.next())
				{
					String p_name = rs.getString("product_name");
					String p_description = rs.getString("product_desc");
					String s_name = rs.getString("supplier");
					float l_price= rs.getFloat("list_price");
					float m_price = rs.getFloat("min_price");
					
					out.println("<tr>");
					out.println("<td>"+  p_name + "</td> ");
					out.println("<td>"+p_description +  "</td> ");
					out.println("<td>"+  s_name+ "</td> ");
					out.println("<td>"+  l_price + "</td> ");
					out.println("<td>"+  m_price + "</td> ");

					out.println("</tr>");

				}


				out.println("</table></body></html>");
			}
				else {
					out.println("<h2 align='center'>No Data Found</h2");
				}
				
		}

			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		// 2nd query ==============================================================================================
		else if(request.getParameter("q2") != null)
		{ 
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","");

				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("Select DISTINCT customer.cust_first_name from orders INNER JOIN customer ON  customer.cust_id = orders.customer_id");
				if(rs.next()) {
					rs.beforeFirst();
				out.println("<html><head><title>Select Query 2</title></head><body>");
				
				out.println("<table align='center' border = '3px'  width= 20%  >");
				out.println("<tr><th>Customer name</th></tr>");
				
				while(rs.next())
				{

					String c_fname = rs.getString("cust_first_name");

					out.println("<tr>");

					out.println("<td align = center>"+c_fname + "</td> ");

					out.println("</tr>");

				}


				out.println("</table></body></html>");
			}
			else {
				out.println("<h2 align='center'>No Data Found</h2");
			}
		}
			catch(Exception e)
			{
				e.printStackTrace();
			}

		}

		// 3rd query ======================================================================================
		else if(request.getParameter("q3") != null)
		{

			int prod_id = Integer.parseInt(request.getParameter("prod_id"));
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","");

				String query = "SELECT  product_name, supplier, list_price from product_info where  p_id= ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setInt(1,prod_id);

				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					rs.beforeFirst();
				out.println("<html><head><title>Select Query 3</title></head><body>");
				out.println("<h2 align='center'>Product Data</h2><br>");
				out.println("<table align='center' border = '3px'  width= 70%  >");
				out.println("<tr><th>Product name</th><th>Supplier name</th><th>List price</th></tr>");

				while(rs.next())
				{

					String pname = rs.getString("product_name");
					String sname = rs.getString("supplier");
					float l_price = rs.getFloat("list_price");

					out.println("<tr>");

					out.println("<td align = center>"+pname + "</td> ");
					out.println("<td align = center>"+ sname+ "</td> ");
					out.println("<td align = center>"+l_price+ "</td> ");                        
					out.println("</tr>");

				}


				out.println("</table></body></html>");
			}
			else {
				out.println("<h2 align='center'>No Data Found</h2");
			}
			
		}

			catch(Exception e)
			{
				e.printStackTrace();
			}

		}

		// 4th query =================================================================================================
		else if(request.getParameter("q4") != null)
		{

			int qty = Integer.parseInt(request.getParameter("quantity"));
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","");

				String query = "SELECT  * from inventory where  quantity_on_hand < ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setInt(1,qty);
				
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					rs.beforeFirst();
				out.println("<html><head><title>Select Query 4</title></head><body>");
				out.println("<h2 align='center'>Inventory Data</h2><br>");
				out.println("<table align='center' border = '3px'  width= 70%  >");
				out.println("<tr><th>Product id</th><th>Location</th><th>Quantity on hand</th></tr>");
			
				while(rs.next())
				{

					int pid = rs.getInt("p_id");
					String loc= rs.getString("location");
					int  qt = rs.getInt("quantity_on_hand");

					out.println("<tr>");

					out.println("<td align = center>"+pid + "</td> ");
					out.println("<td align = center>"+ loc+ "</td> ");
					out.println("<td align = center>"+qt+ "</td> ");                        
					out.println("</tr>");

				}

				out.println("</table></body></html>");
			}
			
			else {
				out.println("<h2 align='center'>No Data Found</h2");
			}
		}

			catch(Exception e)
			{
				e.printStackTrace();
			}


		}
		// Fifth query =======================================================================================
		else if(request.getParameter("q5") != null)
		{
			int cust_id = Integer.parseInt(request.getParameter("customer_id"));
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","");

				String query = "SELECT   * from orders where customer_id = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setInt(1,cust_id);
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					rs.beforeFirst();
				out.println("<html><head><title>Select Query 5</title></head><body>");
				out.println("<h2 align='center'>Order Data</h2><br>");
				out.println("<table align='center' border = '3px'  width= 60%  >");
				out.println("<tr><th>Customer id</th> <th>Order date</th>  <th>Order status</th>  <th> Order total </th></tr>");


			
				while(rs.next())
				{

					int c_id = rs.getInt("customer_id");
					String odate = rs.getString("order_date");
					int ostatus = rs.getInt("order_status");
					float  ototal = rs.getFloat("order_total");


					String state;
					if(ostatus == 0)
					{
						state = "Pending";
					}

					else{
						state = "Delivered";
					}
					out.println("<tr>");

					out.println("<td align = center>"+c_id + "</td> ");
					out.println("<td align = center>"+ odate+ "</td> ");
					out.println("<td align = center>"+state+"</td>");
					out.println("<td align = center>"+ototal+ "</td> ");                        



					out.println("</tr>");

				}


				out.println("</table></body></html>");
			}
			
			else {
				out.println("<h2 align='center'>No Data Found</h2");
			}
		}

			catch(Exception e)
			{
				e.printStackTrace();
			}

		}
	}
}
