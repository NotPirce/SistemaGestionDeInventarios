<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import="com.hemm2025.sistemagestiondeinventarios.DAO.ProductoDAO" %>
<%@ page import="com.hemm2025.sistemagestiondeinventarios.models.Producto" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    ProductoDAO dao = new ProductoDAO();
    Producto producto = dao.obtenerPorId(id);
    request.setAttribute("producto", producto);
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Procesar Producto</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body{
                background-color: #121212;
            }
        </style>
    </head>
    <body class="text-white">
        <div class="container mt-5">
            <h2 class="text-center mb-4">Detalle del Producto</h2>

            <table class="table table-dark table-bordered border-primary">
                <thead class="border-warning">
                    <tr>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th>Cantidad</th>
                        <th>Categoría</th>
                        <th>Acciones</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td>${producto.nombre}</td>
                        <td>₡ ${producto.precio}</td>
                        <td>${producto.cantidad}</td>
                        <td>${producto.idCategoria}</td>
                        <td> 
                            <div class="d-flex gap-2">
                                <button class="btn btn-warning" onclick="mostrarFormulario('vender')">Vender</button>
                                <button class="btn btn-success" onclick="mostrarFormulario('comprar')">Comprar</button>
                            </div>
                        </td>
                    </tr>
                </tbody>

            </table>




            <div class="mt-4">
                <a href="${pageContext.request.contextPath}/InventarioServlet.do?accion=listar" class="btn btn-secondary">Volver al inventario</a>

            </div>
        </div>


        <div class="modal fade" id="modalProcesar" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content bg-dark text-white">
                    <div class="modal-header">
                        <h5 class="modal-title" id="modalLabel">Confirmar Acción</h5>
                        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                    </div>
                    <div class="modal-body">
                        <p id="modalMensaje"></p>

                        <form id="formModal" action="${pageContext.request.contextPath}/InventarioServlet.do" method="post">
                            <input type="hidden" name="id" value="${producto.id}" />
                            <input type="hidden" name="accion" id="modalAccion" />

                            <div class="mb-3">
                                <label for="cantidadInput" class="form-label">Cantidad</label>
                                <input type="number" class="form-control" id="cantidadInput" name="cantidad" min="1" required>
                            </div>

                            <button type="submit" class="btn btn-primary">Confirmar</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <script>
                    function mostrarFormulario(accion) {
                        const modalMensaje = document.getElementById("modalMensaje");
                        const modalAccion = document.getElementById("modalAccion");

                        const nombre = "${producto.nombre}";
                        const precio = "${producto.precio}";

                        modalMensaje.innerHTML = "¿Estás seguro de " + (accion === 'vender' ? 'vender' : 'comprar') +
                                " <strong>" + nombre + "</strong> con un precio de $" + precio + " por unidad?";
                        modalAccion.value = accion;
                        document.getElementById("cantidadInput").value = "";

                        const modal = new bootstrap.Modal(document.getElementById("modalProcesar"));
                        modal.show();
                    }
        </script>

    </body>
</html>
