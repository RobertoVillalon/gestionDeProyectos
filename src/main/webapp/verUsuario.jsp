<%@ page import="cl.ipchile.jira.entity.Usuario" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalle del Usuario</title>
    <link rel="stylesheet" href="styles.css"> <!-- Enlace a tu archivo CSS -->
</head>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        color: #333;
        margin: 0;
        padding: 20px;
    }

    .container {
        max-width: 600px;
        margin: 0 auto;
        background: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    h2 {
        color: #4CAF50;
        text-align: center;
    }

    .user-details {
        margin-bottom: 20px;
    }

    .user-details p {
        font-size: 16px;
        margin: 10px 0;
    }

    .user-details strong {
        color: #555;
    }

    .actions {
        text-align: center;
    }

    .actions a {
        text-decoration: none;
        padding: 10px 15px;
        background-color: #4CAF50;
        color: white;
        border-radius: 5px;
        margin: 5px;
        display: inline-block;
        transition: background-color 0.3s;
    }

    .actions a:hover {
        background-color: #45a049;
    }

    .actions a.delete {
        background-color: #f44336;
    }

    .actions a.delete:hover {
        background-color: #e53935;
    }
</style>
<body>
    <h2>Detalle del Usuario</h2>
    
    <div class="user-details">
        <% 
            Usuario usuario = (Usuario) request.getAttribute("usuario"); 
            if (usuario != null) {
        %>
            <p><strong>ID:</strong> <%= usuario.getId() %></p>
            <p><strong>Nombre:</strong> <%= usuario.getNombre() %></p>
            <p><strong>Email:</strong> <%= usuario.getEmail() %></p>
            <p><strong>Edad:</strong> <%= usuario.getEdad() %></p>
        <% 
            } else {
        %>
            <p>No se encontró información del usuario.</p>
        <% 
            }
        %>
    </div>
    
    <div class="actions">
        <a href="svUsuarios?action=list">Volver a la lista de usuarios</a>
        <a href="svUsuarios?action=edit&id=<%= usuario.getId() %>">Editar Usuario</a>
        <a href="svUsuarios?action=delete&id=<%= usuario.getId() %>" 
           onclick="return confirm('¿Estás seguro de que deseas eliminar este usuario?');">
           Eliminar Usuario
        </a>
    </div>
</body>
</html>
