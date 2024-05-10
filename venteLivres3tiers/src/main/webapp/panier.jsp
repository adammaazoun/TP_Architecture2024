<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

</head>
<body>
<%@ page import ="model.Client" %>
<%@ page import ="java.util.ArrayList" %>
<%! String login; ArrayList<String[]> list; %>
<% login = request.getParameter("login");
list = Client.affichePanier(login);%>
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
                <% for(String[] row : list) { %>
                <tr>
                    <% for(String cell : row) { %>
                        <td><%= cell %></td>
                    <% } %>
                   
                </tr>
                <% } %>
            </tbody>
        </table>
        
    </div>
</div>
</body>
</html>