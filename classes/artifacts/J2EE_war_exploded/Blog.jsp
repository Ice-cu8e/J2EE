<%@ page import="java.sql.*" %>
<%@ page import="static fr.epsi.jeeProject.server.PostgresServer.getConnection" %><%--
  Created by IntelliJ IDEA.
  User: thomas
  Date: 27/02/19
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="styles/style.css">
    <title>J2EE</title>
</head>
<body>
<header class="nav">
    <h2 class="navTitle">J2EE</h2>
</header>
<%
    String ID = request.getAttribute("ID").toString();
    Connection c = getConnection();
    PreparedStatement p = c.prepareStatement("SELECT * FROM BLOG Where ID=?");
    p.setInt(1,Integer.parseInt(ID));
    ResultSet resultSet = p.executeQuery();
    while (resultSet.next()) {
        %>
            <div class="card">
                <div class="cardHeader">
                    <div class="logo" style="text-align: center">
                        <span style="font-size: 18px;"><% out.println(resultSet.getString(1));  %></span>
                    </div>
                    <div class="cardTitle">
                        <h3 style="margin: 0px"><% out.println(resultSet.getString(2));  %></h3>
                        <h5 style="margin: 0px"><% out.println(resultSet.getString(4));  %></h5>
                    </div>
                </div>
                <div>
                    <div class="cardText">
                        <p> <% out.println(resultSet.getString(3));  %>  </p>
                    </div>
                    <ul>
                        <li> <% out.println(resultSet.getString(5));  %> </li>
                        <li> <% out.println(resultSet.getString(6));  %> </li>
                    </ul>
                </div>
            </div>
        <%
    }
%>
