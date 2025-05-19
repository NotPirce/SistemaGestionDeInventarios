<!DOCTYPE html>
<html>
<head>
    <title>Inicio</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
   
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

  
  

    <style>
        body {
            background-color: #121212;
            margin: 0;
            overflow-x: hidden;
        }

        .hero-section {
            width: 100%;
            height: 100vh;
            background-color: #1e1e1e;
            position: relative;
            overflow: hidden;
        }

        canvas {
            width: 100%;
            height: 100%;
            display: block;
            position: absolute;
            top: 0;
            left: 0;
            z-index: 0;
        }

        .floating-navbar {
            position: absolute;
            top: 20px;
            left: 50%;
            transform: translateX(-50%);
            z-index: 10;
            width: 90%;
            max-width: 800px;
            background-color: rgba(255, 255, 255, 0.9);
            border-radius: 1rem;
            padding: 0.5rem 1rem;
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
<body>   
    <section class="hero-section">
       <spline-viewer url="https://prod.spline.design/K00P7pQhFV-E-QQz/scene.splinecode"></spline-viewer>

        <nav class="navbar navbar-expand-lg floating-navbar shadow">
            <div class="container-fluid justify-content-center">
                <div class="navbar-nav d-flex flex-wrap gap-4 justify-content-center">
                    <a class="nav-link" href="CategoriaServlet.do?accion=listar">Lista de Categorías</a>
                    <a class="nav-link" href="ProductosServlet.do?accion=listar">Lista de Productos</a>
                    <a class="nav-link" href="InventarioServlet.do">Inventario</a>
                    <a class="nav-link" href="LoginServlet">Cerrar sesión</a>
                </div>
            </div>
        </nav>
    </section> 
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://unpkg.com/@splinetool/runtime"></script>
<script type="module" src="https://unpkg.com/@splinetool/viewer@1.9.96/build/spline-viewer.js"></script>
<script>
  const canvas = new Spline.Runtime({
    container: document.getElementById('spline-container'),
    sceneUrl: 'https://prod.spline.design/tu-scene-hash/scene.splinecode'
})
</script>
</body>
</html>