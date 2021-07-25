<%-- 
    Document   : addbook
    Created on : May 9, 2021, 11:06:35 PM
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
        <title>Add Book</title>
    </head>
    <body>
        <div>
        <%
Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "");

Statement st=con.createStatement();
ResultSet rs=st.executeQuery("Select * from Category");

%>
       <h2 align="center">Add Book</h2>
        <form method="post" action="addbook">
           <label>Book Name:</label>
           <input type="text" name="bname" placeholder="Enter Book Name"><br><br>
           <label>Author:</label>
           <input type="text" name="author" placeholder="Enter Author Name "><br><br>
           <label>Publication Year:</label>
           <input type="text" name="pyear" placeholder="Enter Publication Year"><br><br>
           <label>Category Name:</label>
           <select name="cid">
           <option value="-1" selected>Select Language</option>
        <%  while(rs.next()){ %>
            <option value="<%= rs.getInt(1)%>" ><%= rs.getString(2) %> </option>
        <% } %>
 </select><br><br>
           <label>Language:</label>
           <select name="language">
               <option value="-1" selected>Select Language</option>
               <option value="English">English</option>
               <option value="Hindi">Hindi</option>
               <option value="Gujarti">Gujarati</option>
           </select><br><br>
            <input type="submit">

            
            
        </form>
        </div>
    </body>
</html>
