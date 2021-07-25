<%-- 
    Document   : addstudent
    Created on : May 9, 2021, 10:23:46 PM
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
input[type=password], select {
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
        <title>Add Student</title>
        

    </head>
    <body>
        <div>
       <h2 align="center">Add Student</h2>
        <form method="post" action="addstudent">
           <label>Name:</label>
           <input type="text" name="name" placeholder="Enter Name"><br><br>
           <label>Contact No:</label>
           <input type="text" name="contactno" placeholder="Enter Number"><br><br>
           <label>Date Of Birth:</label><br>
           <input type="date" name="dob"><br><br>
           <label>Email:</label>
           <input type="text" name="email" placeholder="Enter Email"><br><br>
           <label>Gender:</label>
           <input type="radio" name="gender" value="Female">Female
           <input type="radio" name="gender" value="Male">Male<br><br>
            <input type="submit">

            
            
        </form>
        </div>
    </body>
</html>
