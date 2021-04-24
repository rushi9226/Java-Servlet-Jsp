<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shop", "root", "");

Statement st=con.createStatement();
ResultSet r1=st.executeQuery("Select order_id from orders");

%>

<form method="post" action="Add_Item">
<h3>Order Item Information</h3>
<label>Order Id :</label>
<select name="oid" style="width: 70px;">
	<%  while(r1.next()){ %>
            <option value="<%= r1.getInt(1)%>" ><%= r1.getInt(1) %> </option>
   <% } %>
</select><br><br>
<% ResultSet r2=st.executeQuery("Select p_id,product_name from product_info"); %>
<label>Product Name :</label>
<select name="pid" style="width: 70px;">
	<%  while(r2.next()){ %>
            <option value="<%= r2.getInt(1)%>" ><%= r2.getString(2) %> </option>
   <% } %>
</select><br><br>

<label>Unit Price :</label>
<input type="text" name="u_price" placeholder="Enter Unit Price"><br><br>

<label>Quantity</label>
<input type="text" name="qty" placeholder="Enter Quantity"><br><br>

<input type="submit" name="submit" value="Add Order Items">&nbsp;&nbsp;&nbsp;
<input type="reset" name="reset" value="Clear">
</form>
</body>
</html>