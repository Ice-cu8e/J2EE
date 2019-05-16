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
                            <a class="dropdown-item" href="#">Créer un utilisateur</a>
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
</body>
</html>
