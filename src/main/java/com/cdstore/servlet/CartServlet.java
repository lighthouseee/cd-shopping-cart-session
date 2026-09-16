package com.cdstore.servlet;

import com.cdstore.data.ProductDB;
import com.cdstore.model.Cart;
import com.cdstore.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet({
    "/home",
    "/add",
    "/update",
    "/remove",
    "/checkout"
})
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        switch (path) {

            case "/home":
                request.getRequestDispatcher("/home.jsp")
                       .forward(request, response);
                break;

            case "/update":
                request.getRequestDispatcher("/cart.jsp")
                       .forward(request, response);
                break;

            case "/checkout":
                request.getRequestDispatcher("/checkout.jsp")
                       .forward(request, response);
                break;

            default:
                response.sendRedirect(
                    request.getContextPath() + "/home"
                );
                break;
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String path = request.getServletPath();

        switch (path) {

            case "/add":
                addProduct(request, response);
                break;

            case "/update":
                updateProduct(request, response);
                break;

            case "/remove":
                removeProduct(request, response);
                break;

            default:
                response.sendRedirect(
                    request.getContextPath() + "/home"
                );
                break;
        }
    }

    private Cart getCart(HttpServletRequest request) {

        HttpSession session = request.getSession();

        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        return cart;
    }

    private void addProduct(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        String productCode =
            request.getParameter("productCode");

        Product product =
            ProductDB.getProduct(productCode);

        if (product != null) {
            Cart cart = getCart(request);
            cart.addItem(product);
        }

        response.sendRedirect(
            request.getContextPath() + "/update"
        );
    }

    private void updateProduct(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        String productCode =
            request.getParameter("productCode");

        String quantityText =
            request.getParameter("quantity");

        Cart cart = getCart(request);

        try {
            int quantity =
                Integer.parseInt(quantityText);

            cart.updateItem(
                productCode,
                quantity
            );

        } catch (NumberFormatException e) {
            // Nếu quantity không hợp lệ thì giữ nguyên giỏ hàng.
        }

        response.sendRedirect(
            request.getContextPath() + "/update"
        );
    }

    private void removeProduct(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        String productCode =
            request.getParameter("productCode");

        Cart cart = getCart(request);

        cart.removeItem(productCode);

        response.sendRedirect(
            request.getContextPath() + "/update"
        );
    }
}