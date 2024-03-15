<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Matiere, models.Compte" %>
<%@ page import="dao.MatiereDAOImpl, dao.CompteDAOImpl" %>
<%
    MatiereDAOImpl matiereDAO = new MatiereDAOImpl();
    List<Matiere> matieres = matiereDAO.listerToutesLesMatieres();

    CompteDAOImpl compteDAO = new CompteDAOImpl();
    List<Compte> comptes = compteDAO.tousLesComptes();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter une Spécialité</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background: #f8f8f8; /* Couleur de fond légèrement grise */
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh; /* Hauteur minimale pour remplir l'écran */
        }
        .container {
            background: #fff;
            padding: 40px; /* Espacement interne pour le contenu */
            border-radius: 8px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1); /* Ombre légère */
            width: 400px; /* Largeur fixe du conteneur */
        }
        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 10px;
            color: #666; /* Couleur de texte plus claire pour les labels */
        }
        select, input[type="submit"] {
            width: calc(100% - 22px); /* Prendre en compte la largeur du padding et de la bordure */
            padding: 12px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            margin-bottom: 20px;
        }
        input[type="submit"] {
            background-color: #0070c9; /* Bleu Apple */
            color: #fff;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #005499; /* Variation de couleur au survol */
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Ajouter une nouvelle Spécialité</h2>
    <form action="gestionSpecialite" method="post">
        <input type="hidden" name="action" value="ajouter">
        <label for="idCompte">Choisir un Compte :</label>
        <select id="idCompte" name="idCompte">
            <% for (Compte compte : comptes) { %>
            <option value="<%= compte.getId() %>"><%= compte.getLogin() %></option>
            <% } %>
        </select><br><br>
        <label for="idMatiere">Choisir une Matière :</label>
        <select id="idMatiere" name="idMatiere">
            <% for (Matiere matiere : matieres) { %>
            <option value="<%= matiere.getId() %>"><%= matiere.getNom() %></option>
            <% } %>
        </select><br><br>
        <input type="submit" value="Ajouter">
    </form>
</div>
</body>
</html>
