<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Ajouter une Matière</title>
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
    input[type="text"], input[type="submit"] {
      width: 100%;
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
  <h2>Ajouter une nouvelle Matière</h2>
  <form action="gestionMatiere" method="post">
    <input type="hidden" name="action" value="ajouter">
    <label for="nom">Nom de la matière :</label>
    <input type="text" id="nom" name="nom" required><br><br>
    <input type="submit" value="Ajouter">
  </form>
</div>
</body>
</html>
