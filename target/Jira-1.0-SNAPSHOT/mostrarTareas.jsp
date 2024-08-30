<%@page import="cl.ipchile.jira.entity.Tarea"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Tareas</title>
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
    <h2>Lista de Tareas</h2>
    <a href="crearTarea.jsp">Crear Nueva Tarea</a>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Estado</th>
                <th>Título</th>
                <th>Descripción</th>
                <th>Prioridad</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Tarea> tareas = (List<Tarea>) request.getAttribute("tareas"); 
                if (tareas != null && !tareas.isEmpty()) {
                    for (Tarea tarea : tareas) {
            %>
                <tr>
                    <td><%= tarea.getId() %></td>
                    <td><%= tarea.getTitulo() %></td>
                    <td><%= tarea.getEstado() %></td>
                    <td><%= tarea.getDescripcion() %></td>
                    <td><%= tarea.getPrioridad() %></td>
                    <td class="actions">
                        <a href="svTareas?action=view&id=<%= tarea.getId() %>">Ver</a>
                        <a href="svTareas?action=delete&id=<%= tarea.getId() %>" onclick="return confirm('¿Estás seguro de que deseas eliminar esta tarea?');">Eliminar</a>
                    </td>
                </tr>
            <% 
                    }
                } else {
            %>
                <tr>
                    <td colspan="6" class="no-tasks">No hay tareas disponibles.</td>
                </tr>
            <% 
                }
            %>
        </tbody>
    </table>
</body>
</html>