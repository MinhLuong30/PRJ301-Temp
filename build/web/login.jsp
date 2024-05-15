<%-- 
    Document   : login
    Created on : Mar 7, 2024, 6:54:10 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <form action="MainController" method="POST">
            Username<input type="text" name="name"  /></br>
            Password<input type="text" name="pass" /></br>
            <input type="submit" value="Login" name="action" />
        </form>
        <% 
            String error= (String)request.getAttribute("ERROR");
            if(error== null) error= "";
        %>
         <h1><%= error %></h1>
    </body>
</html>
