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
@WebServlet(name = "addcat", urlPatterns = {"/addcat"})
public class addcat extends HttpServlet {

    
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
        
        try
        {
         tx=sess.beginTransaction();
         catagory x = new catagory();
         x.setCname(name);
         sess.save(x);
         tx.commit();
         out.println("Category Inserted");
         RequestDispatcher rd = request.getRequestDispatcher("viewcategory.jsp");
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

    
