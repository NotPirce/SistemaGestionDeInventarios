/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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

/**
 *
 * @author notpi
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        request.getSession().invalidate();
        response.sendRedirect("paginas/login.jsp");
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
        
        
        
        
        
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        
//        if(validarUsuario(username, password)){
//            String token = JwtUtil.generateToken(username);
//            response.setHeader("Authorization", "Bearer" + token);
//            request.getSession().setAttribute("jwt", token);
//            response.sendRedirect("index.jsp");
//            
//        }
                
        
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
    
    private boolean validarUsuario(String username, String password){
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario u = usuarioDAO.getusuario(username, password);
        return u != null;        
    }

}
