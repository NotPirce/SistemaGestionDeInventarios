/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hemm2025.sistemagestiondeinventarios.servlets;

import com.hemm2025.sistemagestiondeinventarios.DAO.CategoriaDAO;
import com.hemm2025.sistemagestiondeinventarios.models.Categoria;
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
@WebServlet(name = "CategoriaServlet", urlPatterns = {"/CategoriaServlet.do"})
public class CategoriaServlet extends HttpServlet {

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
            out.println("<title>Servlet CategoriaServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoriaServlet at " + request.getContextPath() + "</h1>");
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
        
        String accion = request.getParameter("accion");
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        
        switch(accion == null ? "lista" : accion){
            case "listar":
                List<Categoria> lista = categoriaDAO.listaCategoria();
//                lista = categoriaDAO.listaCategoria();
                // "lista" Esto puede ser cualquier nombre es para guardar un objeto lista dentro del request
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("paginas/categorias.jsp").forward(request, response);
                break;
            case "editar":
                int idEditar = Integer.parseInt(request.getParameter("id"));
                Categoria catEditar = categoriaDAO.getCategoria(idEditar);
                request.setAttribute("categoria", catEditar);
                request.getRequestDispatcher("paginas/formularioCategoria.jsp").forward(request, response);
                break;
            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                categoriaDAO.eliminarCategoria(idEliminar);
                response.sendRedirect("CategoriaServlet.do?accion=listar");
                break;
                
            case "nuevo":
                request.setAttribute("categoria", new Categoria());
                request.getRequestDispatcher("paginas/formularioCategoria.jsp").forward(request, response);
                break;
                
            default:
                response.sendRedirect("CategoriaServlet.do?accion=listar");
                
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
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        
        int id = request.getParameter("id").isEmpty() ? 0 : Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        
        Categoria categoria = new Categoria(id,nombre);
        categoria.setId(id);
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);

        boolean resultado;
        
        if(id == 0){
            categoria.setId(null);
           resultado = categoriaDAO.agregarCategoria(categoria);
        }else{
         resultado = categoriaDAO.ActualizarCategoria(categoria);
        }
        
        if(resultado){
        response.sendRedirect("CategoriaServlet.do?accion=listar");        
        }else{
        response.sendRedirect("paginas/error.jsp");
        }
        
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
