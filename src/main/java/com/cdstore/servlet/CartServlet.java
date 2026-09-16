package com.cdstore.servlet;

import com.cdstore.model.Cart;
import com.cdstore.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private Map<String, Product> products;

    @Override
    public void init() {

        products = new HashMap<>();

        products.put(
            "8601",
            new Product(
                "8601",
                "86 (the band) - True Life Songs and Pictures",
                14.95
            )
        );

        products.put(
            "pf01",
            new Product(
                "pf01",
                "Paddlefoot - The first CD",
                12.95
            )
        );

        products.put(
            "pf02",
            new Product(
                "pf02",
                "Paddlefoot - The second CD",
                14.95
            )
        );
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String productCode = request.getParameter("productCode");

        HttpSession session = request.getSession();

        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        if ("add".equals(action)) {

            Product product = products.get(productCode);

            if (product != null) {
                cart.addItem(product);
            }
        }

        else if ("update".equals(action)) {

            try {

                int quantity =
                    Integer.parseInt(request.getParameter("quantity"));

                cart.updateItem(productCode, quantity);

            } catch (NumberFormatException e) {
                // Nếu quantity không hợp lệ thì giữ nguyên giỏ hàng.
            }
        }

        else if ("remove".equals(action)) {

            cart.removeItem(productCode);
        }

        response.sendRedirect(
            request.getContextPath() + "/cart.jsp"
        );
    }
}
