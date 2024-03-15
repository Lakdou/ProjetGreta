<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.Compte, models.Matiere, models.Specialite" %>
<%@ page import="dao.CompteDAO, dao.MatiereDAO, dao.SpecialiteDAO" %>
<%@ page import="dao.CompteDAOImpl, dao.MatiereDAOImpl, dao.SpecialiteDAOImpl" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Accueil - Liste des utilisateurs, des matières et des spécialités</title>
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
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #0070c9; /* Bleu Apple */
            color: white;
        }
        td a {
            color: black; /* Texte en noir */
            text-decoration: none; /* Supprime le soulignement */
            background-color: #f2f2f2; /* Fond légèrement gris pour les distinguer */
            padding: 4px 8px; /* Espacement autour du texte */
            border-radius: 4px; /* Bordures arrondies */
            display: inline-block; /* Permet l'espacement et le padding */
        }
        td a:hover {
            background-color: #ddd; /* Changement de fond au survol */
        }
        .button-link {
            display: inline-block;
            background-color: #4CAF50; /* Vert Apple */
            color: white;
            padding: 8px 12px;
            margin: 10px auto;
            text-decoration: none;
            border-radius: 4px;
        }
        .button-link:hover {
            background-color: #45a049; /* Variation de couleur au survol */
        }
        .center {
            text-align: center;
            margin: 20px 0; /* Ajoute un peu d'espace au-dessus et en dessous pour l'aération */
        }
    </style>
</head>
<body>

<!-- Section des utilisateurs -->
<h2>Liste des Utilisateurs</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Login</th>
        <th>Mot de Passe</th>
        <th>Action</th>
    </tr>
    <%
        CompteDAO compteDAO = new CompteDAOImpl();
        List<Compte> listeComptes = compteDAO.tousLesComptes();
        for (Compte compte : listeComptes) {
    %>
    <tr>
        <td><%= compte.getId() %></td>
        <td><%= compte.getLogin() %></td>
        <td><%= compte.getMotDePasse() %></td>
        <td><a href='GestionUtilisateurServlet?action=modifierForm&id=<%= compte.getId() %>'>Modifier</a> | <a href='GestionUtilisateurServlet?action=supprimer&id=<%= compte.getId() %>' onclick='return confirm("Êtes-vous sûr de vouloir supprimer cet utilisateur ?");'>Supprimer</a></td>
    </tr>
    <%
        }
    %>
</table>
<div class="center">
    <a href="<%=request.getContextPath()%>/GestionUtilisateurServlet?action=nouveau" class="button-link">Ajouter un Utilisateur</a>
</div>

<!-- Section des matières -->
<h2>Liste des Matières</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Action</th>
    </tr>
    <%
        MatiereDAO matiereDAO = new MatiereDAOImpl();
        List<Matiere> listeMatieres = matiereDAO.listerToutesLesMatieres();
        for (Matiere matiere : listeMatieres) {
    %>
    <tr>
        <td><%= matiere.getId() %></td>
        <td><%= matiere.getNom() %></td>
        <td><a href='gestionMatiere?action=modifierForm&id=<%= matiere.getId() %>'>Modifier</a> | <a href='gestionMatiere?action=supprimer&id=<%= matiere.getId() %>' onclick='return confirm("Êtes-vous sûr de vouloir supprimer cette matière ?");'>Supprimer</a></td>
    </tr>
    <%
        }
    %>
</table>
<div class="center">
    <a href="<%=request.getContextPath()%>/gestionMatiere?action=nouveau" class="button-link">Ajouter une Matière</a>
</div>

<!-- Section des spécialités -->
<h2>Liste des Spécialités</h2>
<table>
    <tr>
        <th>ID Compte</th>
        <th>ID Matière</th>
        <th>Action</th>
    </tr>
    <%
        SpecialiteDAO specialiteDAO = new SpecialiteDAOImpl();
        CompteDAO comptesDAO = new CompteDAOImpl();
        MatiereDAO matieresDAO = new MatiereDAOImpl();
        List<Specialite> listeSpecialites = specialiteDAO.listerToutesLesSpecialites();

        for (Specialite specialite : listeSpecialites) {
            Compte compte = comptesDAO.trouverParId(specialite.getIdCompte());
            Matiere matiere = matieresDAO.trouverParId(specialite.getIdMatiere());
            String nomCompte = (compte != null) ? compte.getLogin() : "Inconnu";
            String nomMatiere = (matiere != null) ? matiere.getNom() : "Inconnue";
    %>
    <tr>
        <td><%= nomCompte %></td>
        <td><%= nomMatiere %></td>
        <td><a href='gestionSpecialite?action=modifier&idCompte=<%= specialite.getIdCompte() %>&idMatiere=<%= specialite.getIdMatiere() %>'>Modifier</a> | <a href='gestionSpecialite?action=supprimer&idCompte=<%= specialite.getIdCompte() %>&idMatiere=<%= specialite.getIdMatiere() %>' onclick='return confirm("Êtes-vous sûr de vouloir supprimer cette spécialité ?");'>Supprimer</a></td>
    </tr>
    <%
        }
    %>
</table>
<div class="center">
    <a href="<%=request.getContextPath()%>/gestionSpecialite?action=nouveau" class="button-link">Ajouter une Spécialité</a>
</div>

<div class="center">
    <a href="deconnexion" class="button-link">Déconnexion</a>
</div>

</body>
</html>
