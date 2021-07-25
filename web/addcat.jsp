<%-- 
    Document   : addcat
    Created on : May 9, 2021, 11:07:02 PM
    Author     : shahz
--%>

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
        <title>Add Category</title>
    </head>
    <body>
        <div>
            
       <h2 align="center">Add Category</h2>
        <form method="post" action="addcat">
           <label>Category Name:</label>
           <input type="text" name="name" placeholder="Enter Category Name"><br><br>
           <input type="submit">

            
            
        </form>
        </div>
    </body>
</html>
