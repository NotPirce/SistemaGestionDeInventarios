<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>${empty categoria.id or categoria.id == 0 ? 'Nueva categoría' : 'Editar categoría'}</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <style>
            body{
                background-color: #121212;
            }
            
        </style>
    </head>
    <body class="text-white">
        <div class="container mt-5">
            <h2 class="mb-4 text-center">${empty categoria.id or categoria.id == 0 ? 'Nueva categoría' : 'Editar categoría'}</h2>

            <form action="CategoriaServlet.do" method="post" class="card p-4 shadow bg-dark text-white border border-primary border-3 rounded-4">
                <input type="hidden" name="id" value="${categoria.id}"/> 

                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" class="form-control" value="${categoria.nombre}" required/>
                </div>

                <div class="mb-3">
                    <label for="descripcion" class="form-label">Descripción:</label>
                    <input type="text" id="descripcion" name="descripcion" class="form-control" value="${categoria.descripcion}" required/>
                </div>

                <div class="d-flex justify-content-between">
                    <button type="submit" class="btn btn-primary">Guardar</button>
                    <a href="CategoriaServlet.do?accion=listar" class="btn btn-secondary">Volver</a>
                </div>
            </form>
        </div>

        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
