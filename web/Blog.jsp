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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>J2EE</title>
</head>
<body>
<header class="nav">
    <nav class="navbar navbar-expand-lg navbar-light bg-light" style="padding-bottom: 0px; padding-top: 0px;">
        <a class="navbar-brand" href="BlogPage.jsp">J2EE</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <% Utilisateur myUser=(Utilisateur)session.getAttribute("myUser");
                            out.println(myUser.getEmail());%>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">

                        <%
                            if (myUser.getAdmin()){
                        %>
                        <a class="dropdown-item" data-toggle="modal" data-target="#create">Créer un utilisateur</a>
                        <a class="dropdown-item disabled" data-toggle="modal" data-target="#modif">Modifier un utilisateur</a>
                        <a class="dropdown-item" data-toggle="modal" data-target="#delete">Supprimer un utilisateur</a>
                        <div class="dropdown-divider"></div>

                        <%}%>
                        <a class="dropdown-item" href="Disconnection">Se déconnecter</a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
</header>
<div style="margin-top: 60px">
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
                        <div style="padding:5px; display: flex">
                            <input class="input" type="text" name="btnCom" placeholder="Votre commentaire" required>
                            <input class="input" type="text" name="blog" value="<%out.println(blog.getId());%>" style="display:none;">
                            <button class="buttonSend" type="submit" value="ok" >Envoyer</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    <%
%>
