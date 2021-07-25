<%-- 
    Document   : updatecat
    Created on : May 21, 2021, 6:01:52 PM
    Author     : shahz
--%>
<%@page import="book.catagory"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Query"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.cfg.Configuration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
input[type=text], select {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

input[type=submit] {
  width: 30%;
  background-color: #24a0ed;
  color: white;
  padding: 12px 15px;
  margin: 8px 0;
  border: none;
  font-size: 18px;
  border-radius: 4px;
  cursor: pointer;

}

div {
background: white;
margin: 0 auto;
width: 500px;
border: 1px solid black;
margin-top:70px;
padding: 50px;

}
</style>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Category</title>
    </head>
    <body>
        <div>
         <% 
       
          int id =Integer.parseInt(request.getParameter("val"));
         
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
            String hql="FROM catagory Where cid = :catid";
            Query query=sess.createQuery(hql);
            query.setParameter("catid", id);
            tx=sess.beginTransaction();
            List d=query.list();
       
          
           
            
            for (Iterator iterator = d.iterator(); iterator.hasNext();){
                    catagory ad = (catagory) iterator.next();
                   
                    
                   %>
                   
                   
        <h2 align="center">Update Category</h2>
        <form method="post" action="upcat">
            <input type="hidden" name="cid" value="<%= id %>">
           <label>Category Name:</label>
           <input type="text" name="name" value="<%= ad.getCname() %>"><br><br>
           <input type="submit">

            
            
        </form>
           
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
        </div>
    </body>
    
</html>
