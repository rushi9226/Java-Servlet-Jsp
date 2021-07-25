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
@WebServlet(name = "addstudent", urlPatterns = {"/addstudent"})
public class addstudent extends HttpServlet {

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
        
        
        try
        {
         tx=sess.beginTransaction();
         student x = new student();
         x.setContactno(contact);
         x.setDob(dob);
         x.setSname(name);
         x.setEmail(email);
         x.setGender(gender);
         sess.save(x);
         tx.commit();
         out.println("Student Inserted");
         RequestDispatcher rd = request.getRequestDispatcher("viewstudent.jsp");
		        rd.forward(request, response);
         
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
