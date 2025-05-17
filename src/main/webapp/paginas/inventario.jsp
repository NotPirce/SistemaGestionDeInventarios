<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%-- Extrae el rol y lo guarda en un atributo de request para JSTL --%>
<%
    String token = (String) session.getAttribute("jwt");
    String rol = com.hemm2025.sistemagestiondeinventarios.util.JwtUtil.obtenerRol(token);
    request.setAttribute("rol", rol); // <- Esto es clave para que JSTL pueda acceder a "rol"
%>

<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <title>Inventario</title>
        <style>
            body{
                background-color: #121212;
            }
        </style>
    </head>
    <body class="text-white">

        <div class="container mt-4">
            <h2 class="text-center mb-4">Inventario de Productos</h2>

            <c:if test="${not empty mensaje}">
                <div class="alert alert-success">${mensaje}</div>
            </c:if>
            <c:if test="${not empty error}">
                <div class="alert alert-danger bg-secondary">${error}</div>
            </c:if>

            <table class="table table-dark table-striped table-bordered text-center  border-primary">
                <thead class="border-warning">
                    <tr>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th>Stock</th>
                        <th>Categoría</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="producto" items="${listaProductos}">
                        <tr>
                            <td>${producto.nombre}</td>
                            <td>₡ ${producto.precio}</td>
                            <td>${producto.cantidad}</td>
                            <td>${producto.idCategoria}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${rol == 'admin'}">
                                        <a href="paginas/procesarProducto.jsp?id=${producto.id}" class="btn btn-info btn-sm">Procesar</a>
                                    </c:when>
                                    <c:otherwise>
                                        <button class="btn btn-info btn-sm" disabled>No autorizado</button>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>


            <!--        <div class="modal fade" id="modalConfirmar" tabindex="-1" aria-labelledby="modalConfirmarLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content bg-dark text-white">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="modalConfirmarLabel">Confirmar Acción</h5>
                                    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                                </div>
                                <div class="modal-body">
                                    <p id="modalMensaje">¿Estás seguro de realizar esta acción?</p>
            
                                    <form id="formModal" action="InventarioServlet.do" method="post">
                                        <input type="hidden" name="id" id="modalId" />
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
                    </div>-->

            <div class="mt-4 ">
                <a href="index.jsp" class="btn btn-secondary">Volver al menú principal</a>
            </div>
        </div>

        <!--    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        
            <script>
                function mostrarFormulario(boton) {
                    const nombre = boton.getAttribute('data-nombre');
                    const precio = boton.getAttribute('data-precio');
                    const id = boton.getAttribute('data-id');
                    const accion = boton.getAttribute('data-accion');
        
                    const modalMensaje = document.getElementById("modalMensaje");
                    const modalId = document.getElementById("modalId");
                    const modalAccion = document.getElementById("modalAccion");
                    const cantidadInput = document.getElementById("cantidadInput");
        
                    modalMensaje.innerHTML = "¿Estás seguro de " + (accion === 'vender' ? 'vender' : 'comprar') +
                        " <strong>" + nombre + "</strong> con un precio de $" + precio + " por unidad?";
        
                    modalId.value = id;
                    modalAccion.value = accion;
                    cantidadInput.value = 0;
        
                    const myModal = new bootstrap.Modal(document.getElementById('modalConfirmar'));
                    myModal.show();
                }
            </script>-->

    </body>
</html>
