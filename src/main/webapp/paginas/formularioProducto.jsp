<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>${empty producto.id or producto.id == 0 ? 'Nuevo producto' : 'Editar producto'}</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body{
                background-color: #121212;
            }
        </style>
    </head>
    <body class="text-white">
        <div class="container mt-5">
            <h2 class="mb-4 text-center">${empty producto.id or producto.id == 0 ? 'Nuevo producto' : 'Editar producto'}</h2>

            <form action="ProductosServlet.do" method="post" class="card p-4 shadow-sm bg-dark text-white border border-3 border-warning">
                <input type="hidden" name="id" value="${producto.id != null ? producto.id : 0}" />

                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" class="form-control" value="${producto.nombre}" required />
                </div>

                <div class="mb-3">
                    <label for="descripcion" class="form-label">Descripción:</label>
                    <textarea id="descripcion" name="descripcion" rows="4" class="form-control">${producto.descripcion}</textarea>
                </div>

                <div class="mb-3">
                    <label for="precio" class="form-label">Precio:</label>
                    <input type="number" id="precio" name="precio" step="0.01" class="form-control" value="${producto.precio}" required />
                </div>

                <div class="mb-3">
                    <label for="cantidad" class="form-label">Cantidad:</label>
                    <input type="number" id="cantidad" name="cantidad" class="form-control" value="${producto.cantidad}" required />
                </div>  

                <div class="mb-4">
                    <label for="categoria_id" class="form-label">Categoría (ID):</label>
                    <select id="categoria_id" name="categoria_id" class="form-select" required>
                        <option value="">Seleccione una categoría</option>
                        <c:forEach var="cat" items="${categorias}">
                            <option value="${cat.id}" ${producto.categoriaId != null && cat.id == producto.categoriaId.id ? 'selected="selected"' : ''}>
                                ${cat.id}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="d-flex justify-content-between">
                    <button type="submit" class="btn btn-primary">Guardar</button>
                    <a href="ProductosServlet.do?accion=listar" class="btn btn-secondary">Cancelar</a>
                </div>
            </form>
        </div>

     
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
