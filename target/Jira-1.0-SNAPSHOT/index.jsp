<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Usuarios</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h2>Crear Nuevo Usuario</h2>
    <form action="svUsuarios" method="post">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required><br><br>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>
        
        <label for="edad">Edad: </label>
        <input type="text" id="edad" name="edad" required><br><br>
        
        <button type="submit">Crear Usuario</button>
    </form>
    
    
    
    <h2>Formulario para Mostrar Usuarios</h2>
    <form action="svUsuarios" method="get">
        <button type="submit" name="action" value="list">Mostrar Usuarios</button>
    </form>
    <h2>Formulario para Mostrar Tareas</h2>
    <form action="svTareas" method="get">
        <button type="submit" name="action" value="list">Mostrar Tareas</button>
    </form>
</body>
</html>
