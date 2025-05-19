
package com.hemm2025.sistemagestiondeinventarios.servlets;

import com.hemm2025.sistemagestiondeinventarios.DAO.UsuarioDAO;
import com.hemm2025.sistemagestiondeinventarios.models.Usuario;
import com.hemm2025.sistemagestiondeinventarios.util.JwtUtil;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect("paginas/login.jsp");
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
    String password = request.getParameter("password");

    UsuarioDAO usuarioDAO = new UsuarioDAO();
    Usuario usuario = usuarioDAO.buscarPorUsername(username);

    if (usuario == null) {
        // Usuario no existe
        request.setAttribute("error", "usuario");
        request.getRequestDispatcher("paginas/login.jsp").forward(request, response);
    } else if (!usuario.getPassword().equals(password)) {
        // Contraseña incorrecta
        request.setAttribute("error", "password");
        request.getRequestDispatcher("paginas/login.jsp").forward(request, response);
    } else {
        // Autenticación exitosa
        String rol = usuario.getRole(); 
        String token = JwtUtil.generateToken(username,rol);
        
        response.setHeader("Authorization", "Bearer " + token);
        request.getSession().setAttribute("jwt", token);
        request.getSession().setAttribute("usuario", usuario);
        
        response.sendRedirect("index.jsp");
    }        
            
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private boolean validarUsuario(String username, String password){
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario u = usuarioDAO.getusuario(username, password);
        return u != null;        
    }

}
