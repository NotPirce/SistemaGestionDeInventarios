/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hemm2025.sistemagestiondeinventarios.servlets;

import com.hemm2025.sistemagestiondeinventarios.DAO.ProductoDAO;
import com.hemm2025.sistemagestiondeinventarios.models.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author notpi
 */
@WebServlet(name = "InventarioServlet", urlPatterns = {"/InventarioServlet.do"})
public class InventarioServlet extends HttpServlet {

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
            out.println("<title>Servlet InventarioServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InventarioServlet at " + request.getContextPath() + "</h1>");
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
        ProductoDAO productoDAO = new ProductoDAO();

        List<Producto> productos = productoDAO.obtenerInventarioCompleto();
        request.setAttribute("listaProductos", productos);
        request.getRequestDispatcher("paginas/inventario.jsp").forward(request, response);
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
        int id = Integer.parseInt(request.getParameter("id"));
        String accion = request.getParameter("accion");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        ProductoDAO dao = new ProductoDAO();
        Producto producto = dao.obtenerPorId(id);

        if (producto != null && accion != null) {
            if (accion.equals("vender")) {
                if (producto.getCantidad() < cantidad) {
                  
                    request.setAttribute("error", "No hay suficiente stock para vender.");
                } else {
                    
                    producto.setCantidad(producto.getCantidad() - cantidad);
                    boolean exito = dao.actualizar(producto);
                    if (exito) {
                        request.setAttribute("mensaje", "Producto vendido exitosamente.");
                    } else {
                        request.setAttribute("error", "Ocurrió un error al actualizar el inventario.");
                    }
                }
            } else if (accion.equals("comprar")) {
                
                producto.setCantidad(producto.getCantidad() + cantidad);
                boolean exito = dao.actualizar(producto);
                if (exito) {
                    request.setAttribute("mensaje", "Producto comprado exitosamente.");
                } else {
                    request.setAttribute("error", "Ocurrió un error al actualizar el inventario.");
                }
            } else {
                request.setAttribute("error", "Acción no válida.");
            }
        } else {
            request.setAttribute("error", "Producto no encontrado.");
        }

        List<Producto> listaProductos = dao.obtenerInventarioCompleto();
        request.setAttribute("listaProductos", listaProductos);
        
        request.getRequestDispatcher("paginas/inventario.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para gestionar el inventario de productos";
    }

}
