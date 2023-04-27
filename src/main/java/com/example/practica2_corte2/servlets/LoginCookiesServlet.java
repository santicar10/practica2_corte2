package com.example.practica2_corte2.servlets;

import com.example.practica2_corte2.model.Producto;
import com.example.practica2_corte2.service.LoginService;
import com.example.practica2_corte2.service.ProductoService;
import com.example.practica2_corte2.service.serviceImpl.LoginServiceImpl;
import com.example.practica2_corte2.service.serviceImpl.ProductoServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet(name="LoginCookiesServlet", value= "/logincookies")
public class LoginCookiesServlet extends HttpServlet {
    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceImpl();
        ProductoService productService=new ProductoServiceImpl();
        List<Producto> products=productService.listar();
        Optional<String> cookieOptional = auth.getUsername(req);
        if (cookieOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <head>");
                out.println(" <meta charset=\"UTF-8\">");
                out.println(" <title>Hola " + cookieOptional.get() +
                        "</title>");
                out.println(" </head>");
                out.println(" <body>");
                out.println(" <h1>Hola " + cookieOptional.get() + " has iniciado sesión con éxito!</h1>");
                out.println("       <table>");
                out.println("           <tr>");
                out.println("               <th>id</th>");
                out.println("               <th>nombre</th>");
                out.println("               <th>tipo</th>");
                out.println("               <th>precio</th>");
                out.println("           </tr>");
                products.forEach(p->{
                    out.println("           <tr>");
                    out.println("               <th>"+p.getId()+"</th>");
                    out.println("               <th>"+p.getNombre()+"</th>");
                    out.println("               <th>"+p.getTipo()+"</th>");
                    out.println("               <th>"+p.getPrecio()+"</th>");
                    out.println("           </tr>");
                });
                out.println("       </table>");
                out.println("<p><a href='" + req.getContextPath() +
                        "'>volver</a></p>");
                out.println("<p><a href='" + req.getContextPath() +
                        "/logout'>cerrar sesión</a></p>");
                out.println(" </body>");
                out.println("</html>");
            }
        } else {
            getServletContext().getRequestDispatcher("/logincookies.jsp").forward(req,
                    resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            Cookie usernameCookie = new Cookie("username", username);
            resp.addCookie(usernameCookie);
            resp.sendRedirect(req.getContextPath() + "/logincookies");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no esta autorizado para ingresar a esta página!");
        }

    }
}