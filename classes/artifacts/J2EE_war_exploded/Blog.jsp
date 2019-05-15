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
    <title>Title</title>
</head>
<body>
<%
    String ID = request.getAttribute("ID").toString();
    String title;
    String description;
    String email;
    String date_creation;
    String date_modification;
    String statut;
    Connection connection = StartupListener.c;
    PreparedStatement prep = connection.prepareStatement("SELECT * FROM BLOG Where ID=?");
    prep.setInt(1,Integer.parseInt(ID));
    ResultSet resultSet = prep.executeQuery();
    while (resultSet.next()) {
        title = resultSet.getString(2);
        description = resultSet.getString(3);
        email = resultSet.getString(4);
        date_creation = resultSet.getString(5);
        date_modification = resultSet.getString(6);
        statut = resultSet.getString(7);

%> <br>
    <div><%out.println(title);%></div>
    <div><%out.println(description); }%></div>
 <br>
</body>
</html>
