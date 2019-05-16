<%--
  Created by IntelliJ IDEA.
  User: thomas
  Date: 15/05/19
  Time: 18:32
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
