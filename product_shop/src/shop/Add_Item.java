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
 * Servlet implementation class Add_Item
 */
@WebServlet("/Add_Item")
public class Add_Item extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out; 

		response.setContentType("text/html");

		out = response.getWriter();

		int oid = Integer.parseInt(request.getParameter("oid"));
		int pid =  Integer.parseInt(request.getParameter("pid"));
		float uprice=  Float.parseFloat(request.getParameter("u_price"));
		int qty=  Integer.parseInt(request.getParameter("qty"));
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","");

			
			String query = " insert into order_items(order_id, product_id,unit_price,quantity) values(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);

			ps.setInt(1,oid);
			ps.setInt(2,pid);

			ps.setFloat(3,uprice);

			ps.setInt(4,qty);


			int x = ps.executeUpdate();
			if(x == 1)
			{
				out.println("<h2 align='center'>Order Item Data<h2><br>");

				try{
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("Select  order_items.*,orders.*,product_info.* from((order_items  INNER JOIN  orders ON order_items.order_id = orders.order_id) INNER JOIN product_info ON order_items.product_id = product_info.p_id)");

					out.println("<html><body>");
					out.println("<table align='center' border = '3px'  width= 70%  >");
					out.println("<tr><th>Order id</th><th>Product name</th><th>Unit price</th><th>Quantity</th><th>Order Date</th></tr>");



					while(rs.next())
					{
						int orid = rs.getInt("order_id");
						String p_name  = rs.getString("product_name");
						float u_price = rs.getFloat("unit_price");
						int qt = rs.getInt("quantity");
						String odate= rs.getString("order_date");


						out.println("<tr>");
						out.println("<td >"+  orid + "</td> ");
						out.println("<td >"+p_name+ "</td> ");

						out.println("<td>"+  u_price+ "</td> ");
						out.println("<td>"+  qt + "</td> ");
						out.println("<td>"+  odate+ "</td> ");


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
				out.println("\n Record not inserted....");
			}

		}

		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
