package book;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author shahz
 */
@WebServlet(name = "log", urlPatterns = {"/log"})
public class log extends HttpServlet {

    
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
        try
        {
            String n=request.getParameter("username");  
            String p=request.getParameter("userpass"); 
            String hql="FROM admin WHERE  Email = :email and Pass = :password";
            

            Query query=sess.createQuery(hql);
            query.setParameter("email",n);
            query.setParameter("password",p);
            
            
            tx=sess.beginTransaction();
            List a=query.list();
             
             
         
            if(a!=null)
                    {
                    
                    for (Iterator iterator = a.iterator(); iterator.hasNext();){
                    admin ad = (admin) iterator.next(); 
                    String name = ad.getFname()+ ad.getLname();
                    
                       HttpSession session=request.getSession();  
                     session.setAttribute("uname",name);    
		        RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
		        rd.forward(request, response);
                    }
                    
                    }
            
                out.println("Invalid Data");
            
            tx.commit();   
            sess.close();
            
            
       
        }
        catch(IOException | ServletException | HibernateException ex)
        {
             out.println(ex.getMessage());
        }
 }
    }       
 