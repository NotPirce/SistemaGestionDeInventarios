<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%
    // Saca el token JWT de la sesion
    // Busca el pase del usuario
    String token = (String) session.getAttribute("jwt");
    // Lee el rol desde ese token    
    // Pregunta que tipo de usuario es
    String rol = com.hemm2025.sistemagestiondeinventarios.util.JwtUtil.obtenerRol(token);
//    Lo guarda para la pagina jsp actual
// Hace que el rol este disponible en la vista
    request.setAttribute("rol", rol); 
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Lista de categorías</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body{
                background-color: #121212;
            }

        </style>
    </head>
    <body class="text-white">
        <div class="container mt-5">
            <h1 class="mb-4 text-center">Lista de categorías</h1>

            <div class="mb-3 text-end">
                <c:choose>
                    <c:when test="${rol == 'admin'}">
                        <a href="CategoriaServlet.do?accion=nuevo" class="btn btn-success">Agregar nueva categoría</a>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-info btn-sm" disabled>No autorizado</button>
                    </c:otherwise>
                </c:choose>

            </div>

            <table class="table table-dark table-striped table-bordered text-center border-primary ">
                <thead class="table-dark border-warning  ">
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Descripción</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${lista}">
                        <tr>
                            <td>${item.id}</td>
                            <td>${item.nombre}</td>
                            <td>${item.descripcion}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${rol == 'admin'}">
                                        <a href="CategoriaServlet.do?accion=editar&id=${item.id}" class="btn btn-primary btn-sm">Editar</a>
                                        <a href="CategoriaServlet.do?accion=eliminar&id=${item.id}" 
                                           onclick="return confirm('¿Seguro que quieres eliminar esta categoría?')" 
                                           class="btn btn-danger btn-sm">Eliminar</a>
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
