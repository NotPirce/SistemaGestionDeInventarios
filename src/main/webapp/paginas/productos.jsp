<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String token = (String) session.getAttribute("jwt");
    String rol = com.hemm2025.sistemagestiondeinventarios.util.JwtUtil.obtenerRol(token);
    request.setAttribute("rol", rol); // <- Esto es clave para que JSTL pueda acceder a "rol"
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Lista de productos</title>        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body{
                background-color: #121212;
            }
        </style>
    </head>
    <body class="text-white">
        <div class="container mt-5">
            <h1 class="mb-4 text-center">Lista de productos</h1>

            <div class="mb-3 text-end">
                <c:choose>
                    <c:when test="${rol == 'admin'}">
                        <a href="ProductosServlet.do?accion=nuevo" class="btn btn-success">Nuevo producto</a>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-info btn-sm" disabled>No autorizado</button>
                    </c:otherwise>
                </c:choose>


            </div>

            <table class="table table-dark table-bordered table-striped text-center border-primary">
                <thead class="table-white border-warning">
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Descripción</th>
                        <th>Precio</th>
                        <th>Cantidad</th>
                        <th>Categoría ID</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${lista}">
                        <tr>
                            <td>${item.id}</td>
                            <td>${item.nombre}</td>
                            <td>${item.descripcion}</td>
                            <td>₡ ${item.precio}</td>
                            <td>${item.cantidad}</td>
                            <td>${item.idCategoria}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${rol == 'admin'}">
                                        <a href="ProductosServlet.do?accion=editar&id=${item.id}" class="btn btn-primary btn-sm">Editar</a>
                                        <a href="ProductosServlet.do?accion=eliminar&id=${item.id}" class="btn btn-danger btn-sm">Eliminar</a>
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

            <div class="mt-4">
                <a href="index.jsp" class="btn btn-secondary">Volver al menu principal</a>
            </div>
        </div>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
