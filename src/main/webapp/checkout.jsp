<%@ page import="com.cdstore.model.Cart" %>
<%@ page import="com.cdstore.model.CartItem" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    Cart cart = (Cart) session.getAttribute("cart");
%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Checkout</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/style.css">
</head>

<body>

<h1>CheckOut</h1>

<%
    if (cart == null || cart.getItems().isEmpty()) {
%>

<p>Your cart is empty.</p>

<%
    } else {
%>

<table>

    <tr>
        <th>Description</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Amount</th>
    </tr>

    <%
        for (CartItem item : cart.getItems()) {
    %>

    <tr>

        <td>
            <%= item.getProduct().getDescription() %>
        </td>

        <td>
            <%= String.format("%.2f", item.getProduct().getPrice()) %>
        </td>

        <td>
            <%= item.getQuantity() %>
        </td>

        <td>
            <%= String.format("%.2f", item.getAmount()) %>
        </td>

    </tr>

    <%
        }
    %>

</table>

<h3>
    Total:
    <%= String.format("%.2f", cart.getTotal()) %>
</h3>

<%
    }
%>

</body>

</html>
