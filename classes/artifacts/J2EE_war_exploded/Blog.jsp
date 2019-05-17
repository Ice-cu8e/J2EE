<%@ page import="java.sql.*" %>
<%@ page import="static fr.epsi.jeeProject.server.PostgresServer.connection" %>
<%@ page import="fr.epsi.jeeProject.beans.Blog" %>
<%@ page import="fr.epsi.jeeProject.dao.BlogDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="fr.epsi.jeeProject.beans.Reponse" %>
<%@ page import="fr.epsi.jeeProject.beans.Utilisateur" %><%--
  Created by IntelliJ IDEA.
  User: thomas
  Date: 27/02/19
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style type="text/css">
        <%@ include file="styles/style.css" %>
    </style>
    <title>J2EE</title>
</head>
<body>
<header class="nav">
    <h2 class="navTitle">J2EE</h2>
</header>
<%
    String ID = request.getAttribute("ID").toString();
    BlogDao blogDao = new BlogDao();
    Blog blog = blogDao.getBlog(Integer.parseInt(ID));

        %>
        <div class="card">
            <div class="cardHeader">
                <div class="logo" style="text-align: center">
                    <span style="font-size: 18px;"><% out.println(blog.getId()); %></span>
                </div>
                <div class="cardTitle">
                    <h3 style="margin: 0px"><% out.println(blog.getTitre());  %></h3>
                    <h5 style="margin: 0px"><% out.println(blog.getCreateur().getNom()); %></h5>
                </div>
            </div>
            <div>
                <div class="cardText">
                    <p> <% out.println(blog.getDescription()); %>  </p>
                </div>
                <ul>
                    <li> Date de création : <% out.println(blog.getDateCreation());  %> </li>
                    <li> Date de modification : <% out.println(blog.getDateModification());  %> </li>
                </ul>
            </div>
            <div class="commentaire">
                <%
                    for (Reponse reponse: blogDao.getResponses(blog)) {
                %>
                <div class="comContent">
                    <div style="display: flex">
                        <p class="comText comTextHeader"><% out.println(reponse.getBlogger().getNom());  %></p>
                        <p class="comText comTextHeader
                                    " style="margin-left: auto"><% out.println(reponse.getPublication());  %></p>
                    </div>
                    <p class="comText"><% out.println(reponse.getCommentaire());  %></p>
                </div>
                <%
                    }
                %>
                <div>
                    <form action="CreateCom" method="post" style="margin-bottom: 0px">
                        <div style="padding:8px; display: flex">
                            <input class="input" type="text" name="btnCom" placeholder="Votre commentaire">
                            <input class="input" type="text" name="blog" value="<%out.println(blog.getId());%>" style="display:none;">
                            <button class="buttonSend" type="submit" value="ok" >Envoyer</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    <%
%>
