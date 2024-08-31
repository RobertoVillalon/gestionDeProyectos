<%@page import="java.util.List"%>
<%@ page import="cl.ipchile.jira.entity.Usuario" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Usuarios</title>
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

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
            background-color: #fff;
        }

        table, th, td {
            border: 1px solid #ccc;
        }

        th, td {
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #0056b3;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        a {
            color: #0056b3;
            text-decoration: none;
            font-weight: bold;
        }

        a:hover {
            text-decoration: underline;
        }

        .actions a {
            margin-right: 10px;
        }

        .actions a:last-child {
            color: red;
        }

        .no-tasks {
            text-align: center;
            padding: 20px;
            background-color: #ffcccc;
            color: #cc0000;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h2>Lista de Usuarios</h2>
    <a href="svUsuarios?action=create">Crear Nuevo Usuario</a>
    <br><br>
    <a href="svTareas?action=list">Ir a lista de Tareas</a>

    <table>
        <thead>
            <tr>
                <th>Nombre</th>
                <th>Email</th>
                <th>Edad</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <% 
                // Obtener el array de usuarios de la solicitud
                List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
                
                // Verificar si el array no es nulo y tiene elementos
                if (usuarios != null && usuarios.size() > 0) {
                    for (Usuario usuario : usuarios) {
            %>
            <tr>
                <td><%= usuario.getNombre() %></td>
                <td><%= usuario.getEmail() %></td>
                <td><%= usuario.getEdad() %></td>
                <td class="actions">
                    <a href="svUsuarios?action=view&id=<%= usuario.getId() %>">Ver</a>
                    <a href="svUsuarios?action=delete&id=<%= usuario.getId() %>" onclick="return confirm('¿Estás seguro de que deseas eliminar este usuario?');">Eliminar</a>
                </td>
            </tr>
            <% 
                    }
                } else {
            %>
            <tr>
                <td colspan="2">No hay usuarios disponibles.</td>
            </tr>
            <% 
                }
            %>
        </tbody>
    </table>
</body>
</html>