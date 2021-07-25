/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author shahz
 */
@WebServlet(name = "upstu", urlPatterns = {"/upstu"})
public class upstu extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
          
    
    Configuration cfg = new Configuration();
        System.out.println("Config created");
        cfg.configure();
        System.out.println("configure called");
        SessionFactory sf=cfg.buildSessionFactory();
        System.out.println("sf created");
        Session sess=sf.openSession();
        Transaction tx=null;
        String name=request.getParameter("name");
        String contact=request.getParameter("contactno");
        String dob=request.getParameter("dob");
        String email=request.getParameter("email");
        String gender=request.getParameter("gender");
        int stuid= Integer.parseInt(request.getParameter("sid"));
        try
        {
         tx=sess.beginTransaction();
         student x = new student();
         x.setSname(name);
         x.setContactno(contact);
         x.setDob(dob);
         x.setEmail(email);
         x.setGender(gender);
         x.setSid(stuid);
         
         sess.update(x);
         tx.commit();
         RequestDispatcher rd = request.getRequestDispatcher("viewstudent.jsp");
		        rd.forward(request, response);
                    
         out.println("catagory update");
        }
     catch(Exception ex)
        {
            if (tx!=null)
            {
                tx.rollback();
            }
             ex.printStackTrace();
        }
        finally
        {
            sess.close();
        }

        
    }

   
}
