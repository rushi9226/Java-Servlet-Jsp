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
 * Servlet implementation class Add_Customer
 */
@WebServlet("/Add_Customer")
public class Add_Customer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter out;
		response.setContentType("text/html");
		out = response.getWriter();
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shop", "root", "");
			String query = "Insert into customer(cust_first_name,cust_last_name,contact,email,dob,gender) values (upper(?),upper(?),?,?,?,?)";
			
			  PreparedStatement ps = con.prepareStatement(query);
			  ps.setString(1,request.getParameter("fname"));
			  ps.setString(2,request.getParameter("lname"));
			  ps.setString(3,request.getParameter("c"));
			  ps.setString(4,request.getParameter("email"));
			  ps.setString(5,request.getParameter("dob"));
			  ps.setString(6,request.getParameter("gen"));
					
			  int x = ps.executeUpdate();
					
			  if(x == 1)
				{
					out.println("<h2 align='center'>Customer Data</h2><br>");

					try{
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery("Select * from customer");

						out.println("<html><head><title>Customer Data</title></head><body>");
						out.println("<table align='center' padding='5px' border = '3px'  width= 70% >");
						out.println("<tr><th>Customer Name</th><th>Contact Number</th><th>Email</th><th>Date of Birth</th><th>Gender</th></tr>");



						while(rs.next())
						{
							String c_first_name = rs.getString("cust_first_name");
                            String c_last_name = rs.getString("cust_last_name");
                            String phone_number = rs.getString("contact");
                            String c_email = rs.getString("email");
                            String date_of_birth = rs.getString("dob");
                            String gender = rs.getString("gender");
                            
                            
                             out.println("<tr>");
                            out.println("<td>"+  c_first_name + "   " + c_last_name+ "</td> ");
                            out.println("<td>"+ phone_number +  "</td> ");
                            out.println("<td>"+  c_email+ "</td> ");
                             out.println("<td>"+  date_of_birth + "</td> ");
                                                                                                                       
                            
                            if(gender.equals("f"))
                            {
                                        String gen = "Female";
                                        out.println("<td>"+ gen + "</td>" );
                            }
                            
                            else
                            {
                                    String gen = "Male";
                                    out.println("<td>"+ gen + "</td>" );
                            }
            
                             out.println("</tr>");
						}
						out.println("</table></body></html>");
					}

					catch(Exception e)
					{
						e.printStackTrace();
					}

				}
				else {
					out.println("Please Try Again Later");
				}
			
		}

		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
