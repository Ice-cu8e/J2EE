<%@ page import="fr.epsi.jeeProject.beans.Blog" %>
<%@ page import="static fr.epsi.jeeProject.server.PostgresServer.getConnection" %>
<%@ page import="fr.epsi.jeeProject.dao.BlogDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="fr.epsi.jeeProject.beans.Reponse" %>
<%--
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
            <div class="commentaire">
                <div class="comContent" style="border-bottom: 0px; display: flex">
                    <a class="comText showMore" href="<%=request.getContextPath()+"/Blog?ID=" + blog.getId()%>">Voir plus</a>
                </div>
                <%
                    blogDao.getResponses(blog);
                    Reponse reponse = blogDao.getResponses(blog).get(0);
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

                %>
                <div>
                    <div style="padding:8px; display: flex">
                        <input class="input" type="text" placeholder="Votre commentaire">
                        <button class="buttonSend">Envoyer</button>
                    </div>
                </div>
            </div>
        </div>
        <%
    }
%>
