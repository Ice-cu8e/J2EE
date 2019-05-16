<%@ page import="java.sql.*" %>
<%@ page import="static fr.epsi.jeeProject.server.PostgresServer.getConnection" %><%--
  Created by IntelliJ IDEA.
  User: thomas
  Date: 27/02/19
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Connection c = null;
    try {
        c = getConnection();
        PreparedStatement prep = c.prepareStatement("SELECT * FROM BLOG");
        ResultSet resultSet = prep.executeQuery();
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
    } catch (SQLException e) {
        e.printStackTrace();
            %>
                <div class="center">
                    <p> <% out.println("Aucunne connexion");  %> </p>
                </div>
            <%
    }
%>
