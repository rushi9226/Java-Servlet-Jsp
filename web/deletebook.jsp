<%-- 
    Document   : deletebook
    Created on : May 22, 2021, 12:30:48 PM
    Author     : shahz
--%>
<%@page import="org.hibernate.Query"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.cfg.Configuration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Book</title>
    </head>
    <body>
        <% 
            int id= Integer.parseInt(request.getParameter("bookid"));
            response.setContentType("text/html");  
      
          
    
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
            String hql="Delete From bookdata Where bid = :bookid";
            Query query=sess.createQuery(hql);
            query.setParameter("bookid", id);
            tx=sess.beginTransaction();
            int row=query.executeUpdate();
            if (row==0)
            {
                %> 
                <script type="text/javascript">
                alert('Please Try Again Later');
                document.location.href = 'viewbook.jsp';
      </script>

        
                <%
            }
        else{
                %>
                <script type="text/javascript">
                alert('BOOK DELETED SUCCESSFULLY');
                document.location.href = 'viewbook.jsp';
      </script>

                <%
            }
            tx.commit();              
            
            
       
        }
        catch(Exception ex)
        {
             ex.printStackTrace();
        }
        finally
        {
            sess.close();
        }
    
        %>
    </body>
</html>
