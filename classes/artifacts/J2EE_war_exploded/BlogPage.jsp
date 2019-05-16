<%@ page import="fr.epsi.jeeProject.beans.Utilisateur" %><%--
  Created by IntelliJ IDEA.
  User: thomas
  Date: 15/05/19
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style type="text/css">
        <%@ include file="styles/style.css" %>
    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>J2EE</title>
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand">J2EE</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <% Utilisateur myUser=(Utilisateur)session.getAttribute("myUser");
                                out.println(myUser.getEmail());%>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" data-toggle="modal" data-target="#create">Créer un utilisateur</a>
                            <a class="dropdown-item" href="#">Modifier un utilisateur</a>
                            <a class="dropdown-item" href="#">Supprimer un utilisateur</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="Disconnection">Se déconnecter</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <div id="content">
        <div class='row'>
            <div class="column sideColumn"></div>
            <div class="column">
                <%@ include file="BlogList.jsp" %>
            </div>
            <div class="column sideColumn"></div>
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
                        <div id="modalContent">
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
