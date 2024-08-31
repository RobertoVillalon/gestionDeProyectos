<%@page import="cl.ipchile.jira.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="cl.ipchile.jira.entity.Tarea"%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Tarea</title>
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

        .form-container {
            background-color: #fff;
            padding: 20px;
            border: 1px solid #ccc;
            max-width: 600px;
            margin: 0 auto;
        }

        label {
            font-weight: bold;
            margin-top: 10px;
            display: block;
        }

        input[type="text"], textarea, select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }

        .actions {
            margin-top: 20px;
            text-align: right;
        }

        .actions input[type="submit"] {
            background-color: #0056b3;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .actions input[type="submit"]:hover {
            background-color: #003d7a;
        }

        .back {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 15px;
            background-color: #ccc;
            color: black;
            text-decoration: none;
            border-radius: 5px;
        }

        .back:hover {
            background-color: #aaa;
        }
    </style>
</head>
<body>
    <h2>Editar Tarea</h2>
    
    <div class="form-container">
        <form action="svTareas" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="<%= ((Tarea) request.getAttribute("tarea")).getId() %>">
            
            <label for="estado">Estado:</label>
            <select name="estado" id="estado">
                <option value="Pendiente" <%= ((Tarea) request.getAttribute("tarea")).getEstado().equals("Pendiente") ? "selected" : "" %>>Pendiente</option>
                <option value="En Progreso" <%= ((Tarea) request.getAttribute("tarea")).getEstado().equals("En Progreso") ? "selected" : "" %>>En Progreso</option>
                <option value="Completada" <%= ((Tarea) request.getAttribute("tarea")).getEstado().equals("Completada") ? "selected" : "" %>>Completada</option>
            </select>

            <label for="titulo">Título:</label>
            <input type="text" id="titulo" name="titulo" value="<%= ((Tarea) request.getAttribute("tarea")).getTitulo() %>">

            <label for="descripcion">Descripción:</label>
            <textarea id="descripcion" name="descripcion" rows="4"><%= ((Tarea) request.getAttribute("tarea")).getDescripcion() %></textarea>

            <label for="prioridad">Prioridad:</label>
            <select name="prioridad" id="prioridad">
                <option value="Baja" <%= ((Tarea) request.getAttribute("tarea")).getPrioridad().equals("Baja") ? "selected" : "" %>>Baja</option>
                <option value="Media" <%= ((Tarea) request.getAttribute("tarea")).getPrioridad().equals("Media") ? "selected" : "" %>>Media</option>
                <option value="Alta" <%= ((Tarea) request.getAttribute("tarea")).getPrioridad().equals("Alta") ? "selected" : "" %>>Alta</option>
            </select>
            
            <label for="usuario">Asignar a Usuario:</label>
            <select id="usuario" name="usuario" required>
                <% 
                    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
                    if (usuarios != null) {
                        for (Usuario usuario : usuarios) {
                %>
                            <option value="<%= usuario.getId() %>"><%= usuario.getNombre() %></option>
                <% 
                        }
                    } else {
                %>
                        <option value="">No hay usuarios disponibles</option>
                <% 
                    }
                %>
            </select>

            <div class="actions">
                <input type="submit" value="Guardar Cambios">
            </div>
        </form>
        <a href="svTareas?action=list" class="back">Volver a la lista</a>
    </div>
</body>
</html>
