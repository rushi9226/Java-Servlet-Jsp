<%-- 
    Document   : issuebook
    Created on : May 9, 2021, 11:07:33 PM
    Author     : shahz
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
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
  padding: 10px 3px;
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
        <title>Book Issue</title>
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

%>
       <h2 align="center">Book Issue</h2>
        <form method="post" action="issuebook">
         <label>Student Name:</label>
           <select name="sid">
           <option value="-1" selected>Select Student</option>
        <%  while(rs.next()){ %>
            <option value="<%= rs.getInt(1)%>" ><%= rs.getString(2) %> </option>
        <% } %>
 </select><br><br>
 <label>Book Name:</label>
           <select name="bid">
           <option value="-1" selected>Select Book</option>
        <%  while(rs1.next()){ %>
            <option value="<%= rs1.getInt(1)%>" ><%= rs1.getString(3) %> </option>
        <% } %>
 </select><br><br>
 
           <label>Issue Date:</label>
           <input type="date" name="fromdate"><br><br>
           <label>Return Date:</label>
           <input type="date" name="todate"><br><br>
           <label>Actual return Date:(This date is only added when student return the book)</label>
           <input type="date" name="rdate"><br><br>
           
           
            <input type="submit">

            
            
        </form>
        </div>
    </body>
</html>
