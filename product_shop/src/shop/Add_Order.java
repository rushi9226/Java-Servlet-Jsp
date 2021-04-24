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
 * Servlet implementation class Add_Order
 */
@WebServlet("/Add_Order")
public class Add_Order extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out; 

		response.setContentType("text/html");

		out = response.getWriter();

		int custid = Integer.parseInt(request.getParameter("cid"));
		int ostatus =  Integer.parseInt(request.getParameter("status"));
		int total =  Integer.parseInt(request.getParameter("total"));
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","");
			
			String query = " insert into orders(order_date, customer_id,order_status,order_total) values(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);

			ps.setString(1, request.getParameter("odate"));
			ps.setInt(2,custid);

			ps.setInt(3,ostatus);

			ps.setFloat(4,total);

			int x = ps.executeUpdate();
			if(x == 1)
			{
				out.println("<h2 align='center'>Order Data</h2><br>");

				try{
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("Select  customer.*,orders.* from orders INNER JOIN customer ON  customer.cust_id = orders.customer_id");

					out.println("<html><body>");
					out.println("<table align='center' border = '3px'  width= 70%  >");
					out.println("<tr><th>Order id</th><th>Customer name</th><th>Contact number</th><th>Order Date</th><th>Order status</th><th>Order total</th></tr>");

					while(rs.next())
					{
						int oid = rs.getInt("order_id");
						String c_fname = rs.getString("cust_first_name");
						String c_lname = rs.getString("cust_last_name");
						String c_number = rs.getString("contact");
						String odate= rs.getString("order_date");
						int ostate = rs.getInt("order_status");
						float ototal = rs.getFloat("order_total");
						String state;

						if(ostate == 0)
						{
							state = "Pending";
						}

						else{
							state = "Delivered";
						}

						out.println("<tr>");
						out.println("<td>"+  oid + "</td> ");
						out.println("<td>"+c_fname +"  "+ c_lname+ "</td> ");

						out.println("<td>"+  c_number+ "</td> ");
						out.println("<td>"+  odate + "</td> ");
						out.println("<td>"+  state+ "</td> ");
						out.println("<td>"+ototal+"</td>");                                                                                       



						out.println("</tr>");

					}


					out.println("</table></body></html>");
				}

				catch(Exception e)
				{
					e.printStackTrace();
				}


			}


			else
			{
				out.println("Please Try Again Later");
			}

		}

		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
