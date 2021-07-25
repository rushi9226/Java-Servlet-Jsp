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
@WebServlet(name = "upissue", urlPatterns = {"/upissue"})
public class upissue extends HttpServlet {


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
        String fromdate=request.getParameter("fromdate");
        String todate=request.getParameter("todate");
        String rdate=request.getParameter("rdate");
        int sid=Integer.parseInt(request.getParameter("sid"));
        int bid;
        bid = Integer.parseInt(request.getParameter("bid"));
       
        int id =Integer.parseInt(request.getParameter("issueid"));
        
        try
        {
         tx=sess.beginTransaction();
        bookissue c= new bookissue();
        c.setFromdate(fromdate);
        c.setReturndate(rdate);
        c.setTodate(todate);
        c.setStudent_id(sid);
        c.setBook_id(bid);
        c.setIssue_id(id);
        sess.update(c);
         tx.commit();
         RequestDispatcher rd = request.getRequestDispatcher("viewissue.jsp");
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

    
       