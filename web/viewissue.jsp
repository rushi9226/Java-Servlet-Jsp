<%-- 
    Document   : viewissue
    Created on : May 10, 2021, 8:27:05 PM
    Author     : shahz
--%>

<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="book.bookdata"%>
<%@page import="book.bookissue"%>
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
        <title>View Issue Book</title>
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
        <h2 align="center">Issue Book Data</h2>
        <table style="width: 80%" align="center">
            <tr>
                <th>Book Name</th>
                <th>Student Name</th>
                <th>Contact Number</th>
                <th>Issue Date</th>
                <th>Return Date</th>
                 <th>Actual Return Date</th>
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
            bookissue ad = new bookissue();
            Criteria cr=sess.createCriteria(bookissue.class);
//            String hql="From bookdata b inner join b.catagory";
//            Query query=sess.createQuery(hql);
//            tx=sess.beginTransaction();
            List d=cr.list();
            
          
           
            
            for (Iterator iterator = d.iterator(); iterator.hasNext();){
                     ad = (bookissue) iterator.next();
                   
                   Criteria cr1=sess.createCriteria(bookdata.class);
                   int bid=ad.getBook_id();
                   //out.println(bid);
                   cr1.add(Restrictions.eq("Bid",bid));
                   List c1=cr1.list();
                    //List d1 = cr1.list();
                   //iterator = c1.iterator(); iterator.hasNext();
                   bookdata b = (bookdata) c1.get(0);
                   Criteria cr2=sess.createCriteria(student.class);
                   int sid=ad.getStudent_id();
                   //out.println(sid);
                   cr2.add(Restrictions.eq("Sid",sid));
                   List c2=cr2.list();
                    //List d1 = cr1.list();
                   //iterator = c1.iterator(); iterator.hasNext();
                   student s = (student) c2.get(0);
             
                    %>
        <tr>
                        <td><%= b.getBname()%></td>
                        <td><%= s.getSname() %></td>
                        <td><%= s.getContactno() %></td>
                        <td><%= ad.getFromdate() %></td>
                        <td><%= ad.getTodate()  %></td>
                        <td><%= ad.getReturndate() %></td>
                         <td><a href="updateissue.jsp?issueid=<%= ad.getIssue_id()%>"/>Update</a>
                        <td><a href="deleteissue.jsp?issueid=<%= ad.getIssue_id()%>"/>Delete</a>
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


