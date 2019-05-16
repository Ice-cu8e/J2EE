<%@ page import="java.sql.*" %>
<%@ page import="static fr.epsi.jeeProject.server.PostgresServer.getConnection" %>
<%@ page import="fr.epsi.jeeProject.beans.Blog" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="fr.epsi.jeeProject.dao.BlogDao" %><%--
  Created by IntelliJ IDEA.
  User: thomas
  Date: 27/02/19
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Blog> blogs = new ArrayList<Blog>();
    BlogDao blogDao = new BlogDao();
    blogs = blogDao.getAllBlogs();
    for (Blog blog: blogs) {
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
        </div>
        <%
    }
%>
