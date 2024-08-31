<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ page import="cl.ipchile.jira.entity.Usuario" %>
<!DOCTYPE html>
<html>
<head>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioActual");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
    <meta charset="UTF-8">
    <title>Gestor de Tareas</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }
        .header {
            background-color: #4CAF50;
            color: white;
            padding: 15px;
            text-align: center;
        }
        .nav {
            overflow: hidden;
            background-color: #333;
        }
        .nav a {
            float: left;
            display: block;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }
        .nav a:hover {
            background-color: #ddd;
            color: black;
        }
        .container {
            padding: 20px;
        }
        .card {
            background-color: white;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }
        h2 {
            text-align: center;
            margin-top: 0;
        }
        p {
            text-align: center;
        }
    </style>
</head>
<body>

    <div class="header">
    <h1>Bienvenido, <%= usuario.getNombre() %></h1>
    </div>

    <div class="nav">
        <form action="svUsuario" method="get">
            <a href="svUsuarios?action=list">Listar Usuarios</a>
        </form>
        <form action="svTareas" method="get">
            <a href="svTareas?action=list">Listar Tareas</a>
        </form>
        <form action="svUsuario" method="get">
            <a href="svUsuarios?action=create">Crear Usuario</a>
        </form>
        <form action="svTareas" method="get">
            <a href="svTareas?action=create">Crear Tarea</a>
        </form>
    </div>
    <div class="container">
        <div class="card">
            <h2>¿Qué es el Gestor de Tareas?</h2>
            <p>El Gestor de Tareas es una aplicación que te permite gestionar tus tareas de manera eficiente. Puedes crear, editar, eliminar y asignar tareas a usuarios.</p>
        </div>
        <div class="card">
            <h2>Funciones Principales</h2>
            <ul>
                <li>Crear nuevas tareas y asignarlas a usuarios</li>
                <li>Visualizar y editar tareas existentes</li>
                <li>Gestionar usuarios y sus respectivas tareas</li>
                <li>Filtrar y buscar tareas según su estado o prioridad</li>
            </ul>
        </div>
    </div>

</body>
</html>
