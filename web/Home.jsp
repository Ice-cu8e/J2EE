<%@ page import="java.sql.*" %>
<%@ page import="fr.epsi.jeeProject.beans.Utilisateur" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>HOME</h1>
<% Utilisateur user=(Utilisateur) session.getAttribute("myUser"); out.println(user.getNom());%>
    </body>
</html>
