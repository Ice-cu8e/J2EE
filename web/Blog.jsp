<%@ page import="java.sql.*" %>
<%@ page import="static fr.epsi.jeeProject.server.PostgresServer.connection" %>
<%@ page import="fr.epsi.jeeProject.beans.Blog" %>
<%@ page import="fr.epsi.jeeProject.dao.BlogDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="fr.epsi.jeeProject.beans.Reponse" %>
<%@ page import="fr.epsi.jeeProject.beans.Utilisateur" %>
<%@ page import="fr.epsi.jeeProject.dao.IUtilisateurDao" %>
<%@ page import="fr.epsi.jeeProject.dao.UtilisateurDao" %><%--
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
                <%    Utilisateur user=(Utilisateur)session.getAttribute("myUser");

                    if (blog.getCreateur().getEmail().equals(user.getEmail())||(user.getAdmin())) {%>
                <div class="delete">
                    <%
                        if(!(blog.getStatut().getId()==2)){
                    %>
                    <a href="ModifyBlog?ID=<%out.println(blog.getId());%>&type=2" data-toggle="tooltip" title="Archiver"data-placement="left"> <i class="material-icons blue">archive</i></a>
                    <%
                    }else{
                    %>
                    <a href="ModifyBlog?ID=<%out.println(blog.getId());%>&type=1"data-toggle="tooltip" title="Publier"data-placement="left"> <i class="material-icons green"data-placement="left">unarchive</i></a>
                    <%
                        }
                    %>
                    <a href="DeleteBlog?ID=<%out.println(blog.getId());%>"> <i class="material-icons red" data-toggle="tooltip" title="Supprimer"data-placement="left">delete</i></a>
                </div>
                <%}%>
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
            <div>
                <p class="comText comTextHeader" style="margin:3px"><%out.println(blog.getStatut().getDescription());%>
                </p>
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
    <div class="modal" id="create">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Création d'utilisateur</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <!-- Modal body -->
                <div class="modal-body">
                    <form action="CreateUser" method="post">
                        <div class="modalContent">
                            <input required type="email" id="email" class="" name="email" placeholder="Email">
                            <input required type="text" id="nom" class="" name="nom" placeholder="Nom">
                            <input required type="password" id="createpassword" class="" name="createpassword" placeholder="Mot de passe">
                            <input required type="password" id="secondPassword" class="" name="secondpassword" placeholder="Retaper votre mot de passe">
                            <p id="checkbox">Est-ce un administrateur ?</p>
                            <label for="oui">Oui</label>
                            <input type="radio" id="oui" class="" name="radio" value="true" >
                            <label for="non">Non</label>
                            <input type="radio" id="non" class="" name="radio" value="false" checked >
                            <input required type="submit" class="" value="Créer l'utilisateur">
                        </div>
                    </form>
                </div>
                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal" id="delete">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Supprimer un utilisateur</h4>

                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <!-- Modal body -->
                <div class="modal-body">
                    <form action="DeleteUser" method="post">
                        <div class="modalContent">
                            <SELECT name="nom" size="1">
                                <% IUtilisateurDao myUsers=new UtilisateurDao();
                                    List<Utilisateur> users = myUsers.getListOfUtilisateur();
                                    for (Utilisateur aUser:users) {
                                        if(!aUser.getAdmin()){
                                %><option name="user" value=<%out.println(aUser.getEmail());%>> <% out.println(aUser.getEmail());
                                        }
                                    }

                                %>
                            </SELECT>
                            <input required type="submit" class="" value="Supprimer l'utilisateur">
                        </div>
                    </form>
                </div>
                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal" id="modif">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Supprimer un d'utilisateur</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <!-- Modal body -->
                <div class="modal-body">

                </div>
                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    <%
        String createuser= (String) session.getAttribute("createuser");
        if(createuser!=null) {
    %>
    <div class="toast" style="position: absolute; top: 0; right: 0;" data-delay="1500">
        <div class="toast-header">
            <strong class="mr-auto text-primary">Erreur</strong>
            <button type="button" class="ml-2 mb-1 close" data-dismiss="toast">&times;</button>
        </div>
        <div class="toast-body">
            <% out.println(session.getAttribute("createuser"));%>
        </div>
    </div>
    <%
            session.setAttribute("createuser", null);
        } %>

    <%
        String usercreated= (String) session.getAttribute("usercreated");
        if(usercreated!=null) {
    %>
    <div class="toast" style="position: absolute; top: 0; right: 0;" data-delay="1500">
        <div class="toast-header">
            <strong class="mr-auto text-primary">Réussite !</strong>
            <button type="button" class="ml-2 mb-1 close" data-dismiss="toast">&times;</button>
        </div>
        <div class="toast-body">
            <% out.println(session.getAttribute("usercreated"));%>
        </div>
    </div>
    <%
            session.setAttribute("usercreated", null);
        } %>
</div>

<script>
    $(document).ready(function(){
        $('.toast').toast('show');
    });
</script>
    <%
%>
