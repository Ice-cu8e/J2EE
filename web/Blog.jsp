<%@ page import="java.sql.*" %><%--
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
    String ID = (String) request.getAttribute("ID");
    String title;
    String description;
    String email;
    String date_creation;
    String date_modification;
    String statut;
    Connection c = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003", "SA", "");
    PreparedStatement prep = c.prepareStatement("SELECT * FROM BLOG Where ID=?");
    prep.setString(1,ID);
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
