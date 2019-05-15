<%@ page import="java.sql.*" %>
<%@ page import="fr.epsi.jeeProject.listener.StartupListener" %><%--
  Created by IntelliJ IDEA.
  User: thomas
  Date: 27/02/19
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <style type="text/css">
        <%@ include file="index.css" %>
    </style>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>JEE Blog projet</title>
</head>

<body>

<!------ Include the above in your HEAD tag ---------->
<nav class="navbar navbar-dark bg-primary">
    <!-- Navbar content -->
</nav>
<div class="wrapper fadeInDown">
    <div id="formContent">
        <!-- Tabs Titles -->

        <!-- Icon -->
        <div class="fadeIn first">
            <H1>Connexion :</H1>
        </div>

        <!-- Login Form -->
        <form action="Home" method="post">
            <input type="text" id="login" class="fadeIn second" name="login" placeholder="Email">
            <input type="password" id="password" class="fadeIn third" name="password" placeholder="Mot de passe">
            <input type="submit" class="fadeIn fourth" value="Log In">
        </form>

        <!-- Remind Passowrd -->
        <div id="formFooter">
            <a class="underlineHover" href="#">Forgot Password?</a>
        </div>

    </div>
</div>
</body>
</html>
