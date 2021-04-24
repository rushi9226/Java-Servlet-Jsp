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
 * Servlet implementation class Add_Product
 */
@WebServlet("/Add_Product")
public class Add_Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out;
		response.setContentType("text/html");
		out = response.getWriter();
		
		float l = Float.parseFloat(request.getParameter("list"));
		float m = Float.parseFloat(request.getParameter("min"));
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shop", "root", "");
			String query = "Insert into product_info(product_name,product_desc,supplier,list_price,min_price) values (upper(?),?,upper(?),?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,request.getParameter("name"));
			ps.setString(2,request.getParameter("desc"));
			ps.setString(3, request.getParameter("sup"));
			ps.setFloat(4,l);
			ps.setFloat(5,m);
			int x = ps.executeUpdate();

			if(x == 1)
			{
				out.println("<h2 align='center'>Product Data</h2><br>");

				try{
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("Select * from product_info");

					out.println("<html><head><title>Product Data</title></head><body>");
					out.println("<table align='center' border ='3px'  width=70% >");
					out.println("<tr><th>Product Name</th><th>Description</th><th>Supplier name</th><th>List Price</th><th>Min Price</th></tr>");

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
