<%-- 
    Document   : deletestu
    Created on : May 22, 2021, 12:24:25 PM
    Author     : shahz
--%>

<%@page import="org.hibernate.Query"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.cfg.Configuration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Student</title>
    </head>
    <body>
        <% 
            int id= Integer.parseInt(request.getParameter("value"));
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
            String hql="Delete From student  Where sid = :stuid";
            Query query=sess.createQuery(hql);
            query.setParameter("stuid", id);
            tx=sess.beginTransaction();
            int row=query.executeUpdate();
            if (row==0)
            {
                %> 
                <script type="text/javascript">
                alert('Please Try Again Later');
                document.location.href = 'viewstudent.jsp';
      </script>

        
                <%
            }
        else{
                %>
                <script type="text/javascript">
                alert('STUDENT DELETED SUCCESSFULLY');
                document.location.href = 'viewstudent.jsp';
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
