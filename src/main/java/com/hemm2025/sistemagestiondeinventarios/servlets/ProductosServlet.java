/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hemm2025.sistemagestiondeinventarios.servlets;

import com.hemm2025.sistemagestiondeinventarios.DAO.ProductoDAO;
import com.hemm2025.sistemagestiondeinventarios.DAO.CategoriaDAO;
import com.hemm2025.sistemagestiondeinventarios.models.Producto;
import com.hemm2025.sistemagestiondeinventarios.models.Categoria;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

import java.util.List;

/**
 *
 * @author notpi
 */
@WebServlet(name = "ProductosServlet", urlPatterns = {"/ProductosServlet.do"})
public class ProductosServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductosServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductosServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Toma el valor del parametro accion en la URL
        String accion = request.getParameter("accion");
        ProductoDAO productoDAO = new ProductoDAO();
        switch (accion == null ? "lista" : accion) {
            case "listar":
                List<Producto> lista = productoDAO.listaProductos();
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("paginas/productos.jsp").forward(request, response);
                break;
            case "editar":
                int id = Integer.parseInt(request.getParameter("id"));
                Producto producto = productoDAO.getProducto(id);

                CategoriaDAO categoriaDAO = new CategoriaDAO();
                List<Categoria> categorias = categoriaDAO.obtenerTodas();

                request.setAttribute("producto", producto);
                request.setAttribute("categorias", categorias);
                request.getRequestDispatcher("paginas/formularioProducto.jsp").forward(request, response);
                break;
            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                productoDAO.eliminarProducto(idEliminar);
                response.sendRedirect("ProductosServlet.do?accion=listar");
                break;
            case "nuevo":
                Producto nuevoProducto = new Producto();
                CategoriaDAO categoriaDAONuevo = new CategoriaDAO();
                List<Categoria> categoriasNuevo = categoriaDAONuevo.obtenerTodas();
                request.setAttribute("producto", nuevoProducto);
                request.setAttribute("categorias", categoriasNuevo);
                request.getRequestDispatcher("paginas/formularioProducto.jsp").forward(request, response);
                break;
            case "inventario":
                List<Producto> inventario = productoDAO.obtenerInventarioCompleto();
                request.setAttribute("inventario", inventario);
                request.getRequestDispatcher("paginas/inventario.jsp").forward(request, response);
                break;
            default:
                response.sendRedirect("ProductosServlet.do?accion=listar");

        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = request.getParameter("id").isEmpty() ? 0 : Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            BigDecimal precio = new BigDecimal(request.getParameter("precio"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            int categoria_id = Integer.parseInt(request.getParameter("categoria_id"));

            System.out.println("ID categoría recibido: " + categoria_id);

            CategoriaDAO categoriaDAO = new CategoriaDAO();
            Categoria categoria = categoriaDAO.getCategoria(categoria_id);

          
            ProductoDAO productoDAO = new ProductoDAO();
            boolean resultado;
            
            System.out.println("Intentando guardar producto...");
            if (id == 0) {
                  Producto producto = new Producto(nombre, descripcion, precio, cantidad, categoria.getId());
                if (productoDAO.agregarProducto(producto)) {
                    response.sendRedirect("ProductosServlet.do?accion=listar");
                } else {
                    request.setAttribute("error", "Error al guardar el producto en la base de datos.");
                    request.getRequestDispatcher("paginas/formularioProducto.jsp").forward(request, response);
                }
            } else {
                  Producto producto = new Producto(id,nombre, descripcion, precio, cantidad, categoria.getId());
                if (productoDAO.actualizarProducto(producto)) {
                    response.sendRedirect("ProductosServlet.do?accion=listar");
                } else {
                    request.setAttribute("error", "Error al actualizar el producto en la base de datos.");
                    request.getRequestDispatcher("paginas/formularioProducto.jsp").forward(request, response);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al procesar el formulario: " + e.getMessage());
            request.getRequestDispatcher("paginas/formularioProducto.jsp").forward(request, response);
        }

//    

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
