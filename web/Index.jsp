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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style type="text/css">
        <%@ include file="styles/index.css" %>
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <title>JEE Blog projet</title>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand">J2EE</a>
    </nav>
</header>
<div id="content">

<div class="wrapper fadeInDown">
    <div class="formContent">
        <!-- Tabs Titles -->

        <!-- Icon -->
        <div class="fadeIn first">
            <H1>Connexion :</H1>
        </div>

        <!-- Login Form -->
        <form action="Blogs" method="post">
            <input type="text" id="login" class="fadeIn second" name="login" placeholder="Email">
            <input type="password" id="password" class="fadeIn third" name="password" placeholder="Mot de passe">
            <input type="submit" class="fadeIn fourth" value="Log In">
        </form>

        <div id="formFooter">
            <a class="underlineHover" data-toggle="modal" data-target="#myModal">Créer un compte</a>
        </div>

    </div>
</div>
</div>
<!-- The Modal -->
<div class="modal" id="myModal">
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
                    <div id="modalContent">
                    <input required type="text" id="email" class="" name="email" placeholder="Email">
                    <input required type="text" id="nom" class="" name="nom" placeholder="Nom">
                    <input required type="password" id="createpassword" class="" name="createpassword" placeholder="Mot de passe">
                    <input required type="password" id="secondPassword" class="" name="secondpassword" placeholder="Retaper votre mot de passe">
                    <input required type="submit" class="" value="Log In">
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
    String connexionrefused= (String) session.getAttribute("connexionrefused");
    if(connexionrefused!=null) {
%>
<div class="toast" style="position: absolute; top: 0; right: 0;" data-delay="1500">
    <div class="toast-header">
        <strong class="mr-auto text-primary">Erreur</strong>
        <button type="button" class="ml-2 mb-1 close" data-dismiss="toast">&times;</button>
    </div>
    <div class="toast-body">
        <% out.println(session.getAttribute("connexionrefused"));%>
    </div>
</div>
<%
        session.setAttribute("connexionrefused", null);
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
</body>
</html>