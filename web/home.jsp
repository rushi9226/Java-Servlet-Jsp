<%-- 
    Document   : home
    Created on : May 9, 2021, 7:40:37 PM
    Author     : shahz
--%>

<%@page import="book.admin"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
       
    </head>
    <body>
        
         <% 
                     
                 String n=(String)session.getAttribute("uname");  
                   //String n = request.getParameter("name");
                   out.println("WELCOME "+n);
                   
                     
            
         %> 
          
        
           
       <h2>Add Data</h2>
        <a href="addstudent.jsp">Add Student</a><br>
        <a href="addbook.jsp">Add Book</a><br>
        <a href="addcat.jsp">Add Category</a><br>
        <a href="issuebook.jsp">Issue Book</a><br>
        <h2>View Data</h2>
        <a href="viewstudent.jsp">View Student</a><br>
        <a href="viewbook.jsp">View Book</a><br>
        <a href="viewcategory.jsp">View Category</a><br>
        <a href="viewissue.jsp">View Issue Book</a><br>
        
        
    </body>
</html>


    
   
           
