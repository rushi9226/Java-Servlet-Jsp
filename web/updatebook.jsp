<%-- 
    Document   : updatebook
    Created on : May 21, 2021, 11:00:28 PM
    Author     : shahz
--%>

<%@page import="org.hibernate.Criteria"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="book.catagory"%>
<%@page import="book.bookdata"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Query"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.cfg.Configuration"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
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
        <title>Update Book</title>
    </head>
    <body>
        <div>
        
       <%
Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "");

Statement st=con.createStatement();
ResultSet rs=st.executeQuery("Select * from Category");



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
            int id =Integer.parseInt(request.getParameter("bookid"));
            cr.add(Restrictions.eq("Bid",id));
//            String hql="From bookdata b inner join b.catagory";
//            Query query=sess.createQuery(hql);
//            tx=sess.beginTransaction();
            List d=cr.list();
            
          
           
            
            for (Iterator iterator = d.iterator(); iterator.hasNext();){
                    ad = (bookdata) iterator.next();
                   
                   Criteria cr1=sess.createCriteria(catagory.class);
                   int cat_id=ad.getCid();
                   cr1.add(Restrictions.eq("Cid",cat_id));
                   List c1=cr1.list();
                    //List d1 = cr1.list();
                   //iterator = c1.iterator(); iterator.hasNext();
                   catagory c = (catagory) c1.get(0);
                    %>
                 
       
       <h2 align="center">Update Book</h2>
        <form method="post" action="upbook">
          <input type="hidden" name="bid" value="<%= id %>">
           <label>Book Name:</label>
           <input type="text" name="bname" value="<%= ad.getBname() %>"><br><br>
           <label>Author:</label>
           <input type="text" name="author" value="<%= ad.getAuthor() %>"><br><br>
           <label>Publication Year:</label>
           <input type="text" name="pyear" value="<%= ad.getP_year() %>"><br><br>
           <label>Category Name:</label>
           <select name="cid">
           <option value="-1" selected>Select Category</option>
           
        <%  
            int cid = ad.getCid();
               while(rs.next()){ 
               if(cid == rs.getInt(1)){
        %>
            <option value="<%= rs.getInt(1)%>" selected ><%= rs.getString(2) %> </option>
            <% } %>
            <option value="<%= rs.getInt(1)%>" ><%= rs.getString(2) %> </option>
        <% } %>
 </select><br><br>
<% String l = ad.getLanguage();
%>
           <label>Language:</label>
           <select name="language">
               <option value="-1" selected>Select Language</option>
               <option value="English" 
                       <% if(l.equals("English")){
                           out.println("selected"); } %>>English</option>
               <option value="Hindi"
                       <% if(l.equals("Hindi")){
                           out.println("selected"); } %>>Hindi</option>
               <option value="Gujarti"
                       <% if(l.equals("Gujarati")){
                           out.println("selected"); } %>>Gujarati</option>
           </select><br><br>
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
