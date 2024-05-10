<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Library</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<%@ page import ="java.util.ArrayList" %>
<%@ page import ="model.Utilisateur" %>
<%! String search; String login;%>
<% search=request.getParameter("search");
login = request.getParameter("login");%>

<div class="container mt-5">
    <h2>Bienvenue à l'espace client</h2>
    <a href="panier.jsp?login=<%= login %>" class="btn btn-outline-info"> Voir Panier </a>
    <form action="SearchServlet" method="POST" class="mb-3">
        <div class="input-group">
            <input type="text" name="chercher" id="chercher" class="form-control" placeholder="Chercher livre par auteur">
            <button type="submit" class="btn btn-outline-secondary">Chercher</button>
        </div>
    </form>
    
    <div class="table-responsive">
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>ISBN</th>
                    <th>Titre</th>
                    <th>Auteur</th>
                    <th>Année de parution</th>
                    <th>Prix</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
            	<%!ArrayList<String[]> t; %>
                <% if(search.equals("newClient")){
                t=Utilisateur.affiche_liste();}
                else{
                t=Utilisateur.chercher_livre(search);}%>
                <% for(String[] row : t) { %>
                <tr>
                    <% for(String cell : row) { %>
                        <td><%= cell %></td>
                    <% } %>
                    <td><a href="CRUDServlet?isbn=<%= row[0] %>&login=<%= login %>" class="btn btn-warning">Ajouter au panier</a></td>
                </tr>
                <% } %>
            </tbody>
        </table>
        
    </div>
</div>
</body>
</html>
