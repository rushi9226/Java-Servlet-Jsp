<%-- 
    Document   : updateissue
    Created on : May 21, 2021, 11:00:07 PM
    Author     : shahz
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Query"%>
<%@page import="java.util.Iterator"%>
<%@page import="book.student"%>
<%@page import="book.bookdata"%>
<%@page import="book.bookissue"%>
<%@page import="org.hibernate.cfg.Configuration"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.Session"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
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

input[type=date],select {
  width: 95%;
  padding: 10px 10px;
  margin: 8px 0;
  border-radius: 4px;
  border: 1px solid #ccc;
  display: inline-block;

  
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
        <title>JSP Page</title>
    </head>
    <body>
        <div>
          <%
Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "");

Statement st=con.createStatement();
ResultSet rs=st.executeQuery("Select sid,sname from student");

Statement st1=con.createStatement();
ResultSet rs1=st1.executeQuery("Select * from bookdata");

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
            int id =Integer.parseInt(request.getParameter("issueid")) ;
            cr.add(Restrictions.eq("Issue_id",id));
//            String hql="From bookdata b inner join b.catagory";
//            Query query=sess.createQuery(hql);
//            tx=sess.beginTransaction();
            List d=cr.list();
            
          
           
            
            for (Iterator iterator = d.iterator(); iterator.hasNext();){
                     ad = (bookissue) iterator.next();
                   
                   Criteria cr1=sess.createCriteria(bookdata.class);
                   int bookid=ad.getBook_id();
                   //out.println(bid);
                   cr1.add(Restrictions.eq("Bid",bookid));
                   List c1=cr1.list();
                    //List d1 = cr1.list();
                   //iterator = c1.iterator(); iterator.hasNext();
                   bookdata b = (bookdata) c1.get(0);
                   Criteria cr2=sess.createCriteria(student.class);
                   int stuid=ad.getStudent_id();
                   //out.println(sid);
                   cr2.add(Restrictions.eq("Sid",stuid));
                   List c2=cr2.list();
                    //List d1 = cr1.list();
                   //iterator = c1.iterator(); iterator.hasNext();
                   student s = (student) c2.get(0);
             
                    %>

       <h2 align="center">Book Issue</h2>
        <form method="post" action="upissue">
            
            <input type="hidden" name="issueid" value="<%= id %>">
         <label>Student Name:</label>
           <select name="sid">
           <option value="-1" selected>Select Student</option>
        <%  int sid = s.getSid();
               while(rs.next()){ 
               if(sid == rs.getInt(1)){
         %>
            <option value="<%= rs.getInt(1)%>" selected ><%= rs.getString(2) %> </option>
           <% } 
            else{
            %>
            <option value="<%= rs.getInt(1)%>" ><%= rs.getString(2) %> </option>
             <% } %>
        <% } %>
 </select><br><br>
 <label>Book Name:</label>
           <select name="bid">
           <option value="-1" selected>Select Book</option>
        <%  int bid = b.getBid();
               while(rs1.next()){ 
               if(bid == rs1.getInt(1)){
         %>
          <option value="<%= rs1.getInt(1)%>" selected ><%= rs1.getString(3) %> </option>
            <% } 
         else{ %>
         <option value="<%= rs1.getInt(1)%>" ><%= rs1.getString(3) %> </option>
          <% } 
        } %>
 </select><br><br>
           <label>Issue Date:</label>
           <input type="date" name="fromdate" value="<%=ad.getFromdate()%>"><br><br>
           <label>Return Date </label>
           <input type="date" name="todate" value="<%= ad.getTodate()%>"><br><br>
           <label>Actual return Date:</label>
           <input type="date" name="rdate" value="<%= ad.getReturndate()%>"><br><br>
           
           
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
