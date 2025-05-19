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


@WebServlet(name = "CategoriaServlet", urlPatterns = {"/CategoriaServlet.do"})
public class CategoriaServlet extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
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

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        
        switch(accion == null ? "lista" : accion){
            case "listar":
                List<Categoria> lista = categoriaDAO.listaCategoria();
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
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
