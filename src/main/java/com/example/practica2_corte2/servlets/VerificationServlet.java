package com.example.practica2_corte2.servlets;

import com.example.practica2_corte2.model.Producto;
import com.example.practica2_corte2.service.ProductoService;
import com.example.practica2_corte2.service.serviceImpl.ProductoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "VerificationServlet", value = "/verification")
public class VerificationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService productService=new ProductoServiceImpl();
        List<Producto> products=productService.listar();
        Long id = Long.parseLong(req.getParameter("id"));
        List<Producto> productFounder=products.stream().filter(x->x.getId() == (id)).limit(1).collect(Collectors.toList());
        if (productFounder.size()==1) {
            Producto product=productFounder.get(0);
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <head>");
                out.println(" <meta charset=\"UTF-8\">");
                out.println(" <title>Objeto</title>");
                out.println(" </head>");
                out.println(" <body>");
                out.println(" <h1>Objeto existente!</h1>");
                out.println(" <h3> nombre: "+product.getNombre()+" precio: "+product.getPrecio()+" id: "+product.getId()+" tipo: "+product.getTipo()+"</h3>");
                out.println(" </body>");
                out.println("</html>");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No esta en existencia el producto");
        }
    }
}