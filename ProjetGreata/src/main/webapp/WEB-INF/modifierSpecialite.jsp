<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Matiere" %>

<html>
<head>
    <title>Modifier Spécialité</title>
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
        input[type="text"], input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box; /* Assure que le padding ne dépasse pas la largeur */
        }
        input[type="submit"] {
            background-color: #4CAF50; /* Vert Apple */
            color: white;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049; /* Variation de couleur au survol */
        }
    </style>
</head>
<body>
<h2>Modifier Spécialité</h2>
<form action="gestionSpecialite" method="post">
    <input type="hidden" name="action" value="modifier">
    <input type="hidden" name="idCompteAncien" value="${specialite.idCompte}">
    <input type="hidden" name="idMatiereAncien" value="${specialite.idMatiere}">
    <!-- Champs pour les nouvelles valeurs -->
    <input type="text" name="idCompte" value="${specialite.idCompte}">
    <input type="text" name="idMatiere" value="${specialite.idMatiere}">
    <input type="submit" value="Modifier">
</form>
</body>
</html>
