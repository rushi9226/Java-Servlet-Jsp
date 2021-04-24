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
 * Servlet implementation class Add_Inventory
 */
@WebServlet("/Add_Inventory")
public class Add_Inventory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add_Inventory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out; 

		response.setContentType("text/html");

		out = response.getWriter();

		int pid = Integer.parseInt(request.getParameter("pid"));

		int qoh =  Integer.parseInt(request.getParameter("qoh"));
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","");
	          
	          String query = " insert into inventory(location,quantity_on_hand,p_id) values(upper(?),?,?)";
	          PreparedStatement ps = conn.prepareStatement(query);
	          ps.setString(1,request.getParameter("loc"));
	          ps.setInt(2,qoh);
	          ps.setInt(3,pid);
	          
	          
	           
	    int x = ps.executeUpdate();
	    
	    if(x == 1){
	       out.println("<h2 align='center'>Inventory Data</hr><br><br>");
	              
	        try{
	           Statement stmt = conn.createStatement();
	           ResultSet rs = stmt.executeQuery("Select product_info.*,inventory.* from inventory INNER JOIN product_info ON product_info.p_id =inventory.p_id");
	                                
	           out.println("<html><head><title>Inventory Data</title> </head><body>");
	           out.println("<table align='center' border = '3px'  width= 70% >");
	           out.println("<tr><th>Product Name</th><th>Description</th><th>Quantity on hand</th><th>Location</th><th>List Price</th><th>Min Price</th></tr>");
	                                
	           while(rs.next()){
	                   
	        	   String p_name = rs.getString("product_name");
	        	   String p_description = rs.getString("product_desc");
	        	   int quantity = rs.getInt("quantity_on_hand");
	        	   String location = rs.getString("location");
	        	   float l_price= rs.getFloat("list_price");
	        	   float m_price = rs.getFloat("min_price");
	                                              
	            out.println("<tr>");
	            out.println("<td>"+  p_name + "</td> ");
	            out.println("<td>"+p_description +  "</td> ");
	            out.println("<td>"+quantity+"</td>");
	            out.println("<td>"+location+"</td>");
	            out.println("<td>"+  l_price + "</td> ");
	            out.println("<td>"+  m_price + "</td> ");
	            out.println("</tr>");
	                                               
	     }
	              
	                                
	     out.println("</table></body></html>");
	   }
	              
	   catch(Exception e){
	        e.printStackTrace();
	   }

	}
	          
	else{
	   out.println("Please Try Again Later");
	}
	          
  }
	        
	 catch(Exception e){
	      e.printStackTrace();
	}
	     
	}

}
