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
    Connection c = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003", "SA", "");
    PreparedStatement prep = c.prepareStatement("SELECT * FROM BLOG Where ID=?");
    prep.setString(1,ID);
    ResultSet resultSet = prep.executeQuery();
    while (resultSet.next()) {
        String id=resultSet.getString(1);
        String title=resultSet.getString(2);
        String description=resultSet.getString(3);
        String email=resultSet.getString(4);
        String date_creation=resultSet.getString(5);
        String date_modification=resultSet.getString(6);
        String statut=resultSet.getString(7);

%> <br> <%
    out.println(title);
    out.println(description);
%> <br <%
    }
%>
</body>
</html>
