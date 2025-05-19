package com.hemm2025.sistemagestiondeinventarios.filter;

import com.hemm2025.sistemagestiondeinventarios.util.JwtUtil;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// Esta etiqueta es la que indica que rutas vamos a estar filtrando
@WebFilter(urlPatterns = {"*.do", "/admin/*", "/usuario/*"})

// Todas las rutas que tengan .do van a pasar por este filtro
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) sr;
        HttpServletResponse res = (HttpServletResponse) sr1;

        String token = (String) req.getSession().getAttribute("jwt");

//        if (token != null && JwtUtil.validateToken(token)) {
//            String rol = JwtUtil.obtenerRol(token);
//            String path = req.getRequestURI();
//            
//            
//            if(path.contains("/admin") && !"admin".equals(rol)){
//                res.sendRedirect(req.getContextPath() + "/paginas/no-autorizado.jsp");
//                return;
//            }
//            
//            
//            if(path.contains("/usuario/") && !"usuario".equals(rol)){
//                res.sendRedirect(req.getContextPath() + "/paginas/no-autorizado.jsp");
//                return;
//            }
//            
//            fc.doFilter(sr, sr1);
//        } else {
//            res.sendRedirect(req.getContextPath() + "/paginas/login.jsp");
//        }
        if (token != null && JwtUtil.validateToken(token)) {
            fc.doFilter(sr, sr1);
        } else {
            res.sendRedirect(req.getContextPath() + "/paginas/login.jsp");
        }

    }

}
