<%@page import="cl.ipchile.jira.entity.Usuario"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Usuario</title>
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
            color: #2196F3;
            text-align: center;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            margin-bottom: 5px;
            color: #555;
        }

        input[type="text"], input[type="email"], input[type="number"] {
            padding: 10px;
            margin-bottom: 15px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 16px;
            width: 100%;
        }

        input[type="submit"] {
            padding: 10px;
            background-color: #2196F3;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #1976D2;
        }

        .back-link {
            text-align: center;
            margin-top: 20px;
        }

        .back-link a {
            text-decoration: none;
            color: #2196F3;
            transition: color 0.3s;
        }

        .back-link a:hover {
            color: #1976D2;
        }
    </style>
</head>
<body>
    <div class="container">
        <form action="svUsuarios" method="post">
            <h2>Editar Usuario</h2>
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="<%= ((Usuario) request.getAttribute("usuario")).getId() %>">
            
            <label for="nombre">Nombre: </label>
            <input type="text" id="nombre" name="nombre" value="<%= ((Usuario) request.getAttribute("usuario")).getNombre()%>">
            
            <label for="nombre">Email: </label>
            <input type="email" id="email" name="email" value="<%= ((Usuario) request.getAttribute("usuario")).getEmail()%>">
            
            <label for="nombre">Edad:  </label>
            <input type="text" id="edad" name="edad" value="<%= ((Usuario) request.getAttribute("usuario")).getEdad()%>">

            <div class="actions">
                <input type="submit" value="Guardar Cambios">
            </div>
        </form>
        <a href="svUsuarios?action=list" class="back">Volver a la lista</a>
    </div>
</body>
</html>
