<%@ page import="fr.epsi.jeeProject.beans.Utilisateur" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.*" %>
<%@ page import="fr.epsi.jeeProject.server.HsqlServer" %><%--
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
        HsqlServer o = new HsqlServer();

        o.startDBServer();
        // some usefull server work here
        Connection c = o.getDBConn();
        List<Utilisateur> lesUsers = new ArrayList<>();

        out.println("Liste des utilisateurs");
        %> <br> <%

        try {
            Statement stmt = c.createStatement();
            //PreparedStatement prep = c.prepareStatement("SELECT * FROM USERS");
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM USERS");
            //ResultSet resultSet = prep.executeQuery();
            while (resultSet.next()) {
                %> <br> <%
                out.println(resultSet.getString(1));
                out.println(resultSet.getString(2));
                %> <br <%
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    %>
</body>
</html>
