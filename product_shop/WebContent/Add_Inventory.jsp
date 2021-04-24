<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Inventories</title>
</head>
<body>
<form action="Add_Inventory" method="post">
<h3>Inventory Information</h3>
<%
Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shop", "root", "");

Statement st=con.createStatement();
ResultSet rs=st.executeQuery("Select * from product_info");

%>

<label>Product :</label>
<select name="pid">
        <%  while(rs.next()){ %>
            <option value="<%= rs.getInt(1)%>" ><%= rs.getString(2)%></option>
        <% } %>
 </select><br><br>

<label>Location :</label>
<input type="text" name="loc" placeholder="Enter Location"><br><br>

<label>Quantity On Hand :</label>
<input type="text" name="qoh" placeholder="Enter Quantity on Hand"><br><br>

<input type="submit" name="submit" value="Add Inventory">&nbsp;&nbsp;&nbsp;
<input type="reset" name="reset" value="Clear"/>
</form>
</body>
</html>