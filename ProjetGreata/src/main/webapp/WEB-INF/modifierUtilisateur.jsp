<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.Compte" %>
<%@ page import="dao.CompteDAOImpl" %>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    CompteDAOImpl dao = new CompteDAOImpl();
    Compte compte = dao.trouverParId(id); // Assurez-vous que cette méthode existe dans votre DAO
%>
<html>
<head>
    <title>Modifier un utilisateur</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background: #f8f8f8; /* Couleur de fond légèrement grise */
            color: #333;
        }
        h2 {
            text-align: center;
            color: #333;
            margin: 20px 0; /* Ajoute un peu d'espace au-dessus et en dessous pour l'aération */
        }
        form {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
            width: fit-content;
            margin: 0 auto; /* Centre le formulaire horizontalement */
        }
        div {
            margin-bottom: 10px;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"], input[type="password"], button {
            width: 100%;
            padding: 10px;
            margin: 5px 0 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        button {
            background-color: #4CAF50; /* Vert Apple */
            color: white;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049; /* Variation de couleur au survol */
        }
        p {
            font-size: 0.9em;
            color: #666;
        }
    </style>
</head>
<body>
<h2>Modifier l'utilisateur</h2>

<form action="GestionUtilisateurServlet" method="post">
    <input type="hidden" name="action" value="modifier" />
    <input type="hidden" name="id" value="<%= compte.getId() %>" />
    <div>
        <label for="username">Nom d'utilisateur:</label>
        <input type="text" id="username" name="username" value="<%= compte.getLogin() %>" required> <!-- Ajout de l'attribut required -->
    </div>
    <div>
        <label for="password">Nouveau mot de passe:</label>
        <input type="password" id="password" name="password" > <!-- Retiré la value par défaut pour le mot de passe -->
        <p>Laissez vide si vous ne souhaitez pas changer le mot de passe.</p>
    </div>
    <div>
        <button type="submit">Modifier</button>
    </div>
</form>

</body>
</html>
