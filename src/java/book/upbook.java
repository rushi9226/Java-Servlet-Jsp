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
@WebServlet(name = "upbook", urlPatterns = {"/upbook"})
public class upbook extends HttpServlet {

   
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
        String bname=request.getParameter("bname");
        String author=request.getParameter("author");
        String pyear=request.getParameter("pyear");
        int cid=Integer.parseInt(request.getParameter("cid"));
        String language=request.getParameter("language");
        
        int id = Integer.parseInt(request.getParameter("bid"));
        try
        {
         tx=sess.beginTransaction();
         bookdata b= new bookdata();
         b.setBname(bname);
         b.setAuthor(author);
         b.setP_year(pyear);
         b.setLanguage(language);
         b.setCid(cid);
         b.setBid(id);
         sess.update(b);
         tx.commit();
         out.println("Book updated");
             RequestDispatcher rd = request.getRequestDispatcher("viewbook.jsp");
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
