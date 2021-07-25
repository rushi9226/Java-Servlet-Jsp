<%-- 
    Document   : viewcategory
    Created on : May 10, 2021, 8:26:31 PM
    Author     : shahz
--%>

<%@page import="book.catagory"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.cfg.Configuration"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="org.hibernate.Query"%>
<%@page import="book.student"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Category</title>
        <style>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
th, td {
  padding: 15px;
  text-align: center;
}
a{
  width: 30%;
  background-color: #24a0ed;
  color: white;
  padding: 5px 5px;
  margin: 8px 0;
  border: none;
  font-size: 15px;
  text-decoration: none;
  border-radius: 4px;
  cursor: pointer;

}
</style>

    </head>
    
    <body>
        <h2 align="center">Category Data</h2>
        <table style="width: 30%" align="center">
            <tr>
                <th>Category Name</th>
                <th>Update</th>
                <th>Delete</th>
                
            </tr>
            
        
         <% 
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
            String hql="FROM catagory";
            Query query=sess.createQuery(hql);
            tx=sess.beginTransaction();
            List d=query.list();
       
          
           
            
            for (Iterator iterator = d.iterator(); iterator.hasNext();){
                    catagory ad = (catagory) iterator.next();
                   
                    
                    %>
                    <tr>
                        <td><%= ad.getCname() %></td>
                        <td><a href="updatecat.jsp?val=<%= ad.getCid()%>"/>Update</a>
                        <td><a href="deletecat.jsp?val=<%= ad.getCid()%>"/>Delete</a>
</td>
                        
                    </tr> 
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
        </table>
            </body>
</html>


