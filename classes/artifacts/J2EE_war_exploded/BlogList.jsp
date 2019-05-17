<%@ page import="fr.epsi.jeeProject.beans.Blog" %>
<%@ page import="static fr.epsi.jeeProject.server.PostgresServer.connection" %>
<%@ page import="fr.epsi.jeeProject.dao.BlogDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="fr.epsi.jeeProject.beans.Reponse" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="fr.epsi.jeeProject.beans.Utilisateur" %>
<%--
  Created by IntelliJ IDEA.
  User: thomas
  Date: 27/02/19
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Blog> blogs;
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
                <%
                    Utilisateur user=(Utilisateur)session.getAttribute("myUser");
                    if (blog.getCreateur().getEmail().equals(user.getEmail())||(user.getAdmin())) {%>
                <div class="delete">
                   <a href="DeleteBlog?ID=<%out.println(blog.getId());%>"> <i class="material-icons red">delete</i></a>
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
            <div class="commentaire">
                <div class="comContent" style="border-bottom: 0px; display: flex">
                    <a class="comText showMore" href="<%=request.getContextPath()+"/Blog?ID=" + blog.getId()%>">Voir plus</a>
                </div>

                    <%
                        if (blogDao.getResponses(blog).size() != 0) {
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
    }
%>
