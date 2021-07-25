<%-- 
    Document   : viewbook
    Created on : May 10, 2021, 8:25:52 PM
    Author     : shahz
--%>

<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="book.catagory"%>
<%@page import="book.bookdata"%>
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
        <title>View Book</title>
        <style>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
th, td {
  padding: 15px;
  text-align: left;
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
        <h2 align="center">Book Data</h2>
        <table style="width: 80%" align="center">
            <tr>
                <th>Book Name</th>
                <th>Author</th>
                <th>Publication Year</th>
                <th>Category Name</th>
                <th>Language</th>
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
            bookdata ad = new bookdata();
            Criteria cr=sess.createCriteria(bookdata.class);
//            String hql="From bookdata b inner join b.catagory";
//            Query query=sess.createQuery(hql);
//            tx=sess.beginTransaction();
            List d=cr.list();
            
          
           
            
            for (Iterator iterator = d.iterator(); iterator.hasNext();){
                    ad = (bookdata) iterator.next();
                   
                   Criteria cr1=sess.createCriteria(catagory.class);
                   int cid=ad.getCid();
                   cr1.add(Restrictions.eq("Cid",cid));
                   List c1=cr1.list();
                    //List d1 = cr1.list();
                   //iterator = c1.iterator(); iterator.hasNext();
                   catagory c = (catagory) c1.get(0);
                    %>
                    <tr>
                        <td><%= ad.getBname() %></td>
                        <td><%= ad.getAuthor() %></td>
                        <td><%= ad.getP_year() %></td>
                        <td><%= c.getCname() %></td>
                        <td><%= ad.getLanguage() %></td>
                         <td><a href="updatebook.jsp?bookid=<%= ad.getBid()%>"/>Update</a>
                        <td><a href="deletebook.jsp?bookid=<%= ad.getBid()%>"/>Delete</a>
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


