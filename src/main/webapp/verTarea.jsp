<%@page import="cl.ipchile.jira.entity.Tarea"%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Detalles de la Tarea</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            margin: 0;
            padding: 20px;
        }

        h2 {
            color: #0056b3;
        }

        .task-details {
            background-color: #fff;
            padding: 20px;
            border: 1px solid #ccc;
            max-width: 600px;
            margin: 0 auto;
        }

        .task-details label {
            font-weight: bold;
            margin-top: 10px;
            display: block;
        }

        .task-details p {
            margin: 5px 0;
            padding: 5px;
            background-color: #f9f9f9;
            border: 1px solid #ddd;
        }

        .actions {
            margin-top: 20px;
        }

        .actions a {
            color: #0056b3;
            text-decoration: none;
            font-weight: bold;
            margin-right: 10px;
        }

        .actions a:hover {
            text-decoration: underline;
        }

        .actions .delete {
            color: red;
        }

        .back {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 15px;
            background-color: #0056b3;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .back:hover {
            background-color: #003d7a;
        }
    </style>
</head>
<body>
    <h2>Detalles de la Tarea</h2>
    
    <div class="task-details">
        <label>ID:</label>
        <p><%= ((Tarea) request.getAttribute("tarea")).getId() %></p>
        
        <label>Estado:</label>
        <p><%= ((Tarea) request.getAttribute("tarea")).getEstado() %></p>
        
        <label>Título:</label>
        <p><%= ((Tarea) request.getAttribute("tarea")).getTitulo() %></p>
        
        <label>Descripción:</label>
        <p><%= ((Tarea) request.getAttribute("tarea")).getDescripcion() %></p>
        
        <label>Prioridad:</label>
        <p><%= ((Tarea) request.getAttribute("tarea")).getPrioridad() %></p>
        
        <div class="actions">
            <a href="svTareas?action=edit&id=<%= ((Tarea) request.getAttribute("tarea")).getId() %>">Editar</a>
            <a href="svTareas?action=delete&id=<%= ((Tarea) request.getAttribute("tarea")).getId() %>" 
               class="delete" 
               onclick="return confirm('¿Estás seguro de que deseas eliminar esta tarea?');">
               Eliminar
            </a>
        </div>
    </div>

    <a href="svTareas?action=list" class="back">Volver a la lista</a>
</body>
</html>
