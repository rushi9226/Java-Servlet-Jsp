<%-- 
    Document   : deletecat
    Created on : May 21, 2021, 6:17:56 PM
    Author     : shahz
--%>

<%@page import="book.student"%>
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
input[type=date],select {
  width: 95%;
  padding: 10px 10px;
  margin: 8px 0;
  border-radius: 4px;
  border: 1px solid #ccc;
  display: inline-block;

  
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
        <title>Update Student</title>
    </head>
    <body>
        <div>
        
          <% 
    response.setContentType("text/html");  
      
          int id =Integer.parseInt(request.getParameter("value"));
    
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
            String hql="FROM student  WHERE  sid = :studentid";
            Query query=sess.createQuery(hql);
            query.setParameter("studentid",id);
            tx=sess.beginTransaction();
            List d=query.list();
       
          
           
            
            for (Iterator iterator = d.iterator(); iterator.hasNext();){
                    student ad = (student) iterator.next();
                   
                    
            %>        
           
                
        <h2 align="center">Update Student</h2>
        <form method="post" action="upstu">
             <input type="hidden" name="sid" value="<%= id %>">
           <label>Name:</label>
           <input type="text" name="name" value="<%= ad.getSname() %>"><br><br>
           <label>Contact No:</label>
           <input type="text" name="contactno" value="<%= ad.getContactno() %>"><br><br>
           <label>Date Of Birth:</label>
           <input type="date" name="dob" value="<%= ad.getDob() %>"><br><br>
           <label>Email:</label>
           <input type="text" name="email" value="<%= ad.getEmail() %>"><br><br>
           <label>Gender:</label>
           <% String g = ad.getGender(); %>
           <input type="radio" name="gender" value="Female"
               <% if(g.equals("Female")){
                   out.println("Checked");
           } %>>Female
           <input type="radio" name="gender" value="Male"
                   <% if(g.equals("Male")){
                   out.println("Checked");
           } %>
                  >Male<br><br>
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
