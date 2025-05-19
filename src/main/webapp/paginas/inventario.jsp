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
            <div class="mt-4 ">
                <a href="index.jsp" class="btn btn-secondary">Volver al menú principal</a>
            </div>
        </div>
    </body>
</html>
