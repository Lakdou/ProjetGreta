<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Ajouter un utilisateur</title>
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
      background: white;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
      width: 300px; /* Largeur fixe du conteneur */
    }
    h2 {
      text-align: center;
      color: #333;
    }
    div {
      margin-bottom: 10px;
    }
    label {
      display: block;
      margin-bottom: 5px;
      color: #666; /* Couleur de texte plus claire pour les labels */
    }
    input[type="text"], input[type="password"], button {
      width: calc(100% - 22px); /* Prendre en compte la largeur du padding et de la bordure */
      padding: 10px;
      margin: 5px 0 15px;
      border: 1px solid #ccc;
      border-radius: 4px;
      box-sizing: border-box;
    }
    button {
      background-color: #0070c9; /* Bleu Apple */
      color: white;
      cursor: pointer;
      width: auto;
      padding: 10px 20px;
      border: none;
      border-radius: 4px;
    }
    button:hover {
      background-color: #005499; /* Variation de couleur au survol */
    }
  </style>
</head>
<body>
<div class="container">
  <h2>Ajouter un nouvel utilisateur</h2>
  <form action="GestionUtilisateurServlet" method="post">
    <input type="hidden" name="action" value="ajouter" />
    <div>
      <label for="username">Nom d'utilisateur:</label>
      <input type="text" id="username" name="username" required>
    </div>
    <div>
      <label for="password">Mot de passe:</label>
      <input type="password" id="password" name="password" required>
    </div>
    <div>
      <button type="submit">Ajouter</button>
    </div>
  </form>
</div>
</body>
</html>
