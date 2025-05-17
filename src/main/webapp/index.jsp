<!DOCTYPE html>
<html>
<head>
    <title>Inicio</title>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #121212;
        }

        .main-container {
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: start;
        }

        .nav-box {
            max-width: 700px;
            margin: 0 auto;
        }

        .header-content {
            border: 1px solid #ffffff;
            border-radius: 1rem;
            margin: 1px 10px;
        }

        .nav-link {
            color: #343a40 !important;
            font-weight: 500;
        }

        .nav-link:hover {
            color: #0d6efd !important;
        }
    </style>
</head>
<body >
    <div class="main-container py-4">

       
        <header class="bg-dark text-white shadow-sm py-4 mb-5 header-content ">
            <div class="container text-center">
                <h1 class="mb-3">SISTEMA DE GESTION DE INVENTARIOS</h1>
                <p class="text-white-50 fs-5">
                    Bienvenido a nuestra plataforma de gestión.<br>
                    Accede fácilmente a las funcionalidades del sistema.<br>
                    Sistema de administración de inventario y productos.
                </p>
            </div>
        </header>

        <!-- Navigation -->
        <div class="container nav-box">
            <nav class="navbar navbar-expand-lg bg-white shadow rounded mb-4">
                <div class="container-fluid justify-content-center">
                    <div class="navbar-nav d-flex flex-wrap gap-4 justify-content-center">
                        <a class="nav-link" href="CategoriaServlet.do?accion=listar">Lista de Categorías</a>
                        <a class="nav-link" href="ProductosServlet.do?accion=listar">Lista de Productos</a>
                        <a class="nav-link" href="InventarioServlet.do">Inventario</a>
                    </div>
                </div>
            </nav>

            <!-- Logout -->
            <div class="text-center">
                <a href="LoginServlet" class="btn btn-outline-danger px-4">Cerrar sesión</a>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
