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
    <title>Your Cart</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/style.css">
</head>

<body>

<h1>Your Cart</h1>

<div class="buttons">

    <form action="${pageContext.request.contextPath}/"
          method="get">

        <input type="submit"
               value="Continue Shopping">

    </form>

    <form action="${pageContext.request.contextPath}/checkout.jsp"
          method="get">

        <input type="submit"
               value="Checkout">

    </form>

</div>

<%
    if (cart == null || cart.getItems().isEmpty()) {
%>

<p>Your cart is empty.</p>

<%
    } else {
%>

<table>

    <tr>
        <th>Quantity</th>
        <th>Description</th>
        <th>Price</th>
        <th>Amount</th>
        <th></th>
    </tr>

    <%
        for (CartItem item : cart.getItems()) {
    %>

    <tr>

        <td>

            <form class="update-form"
                  action="${pageContext.request.contextPath}/cart"
                  method="post">

                <input type="hidden"
                       name="action"
                       value="update">

                <input type="hidden"
                       name="productCode"
                       value="<%= item.getProduct().getCode() %>">

                <input class="quantity"
                       type="number"
                       name="quantity"
                       min="0"
                       value="<%= item.getQuantity() %>">

                <input type="submit"
                       value="Update">

            </form>

        </td>

        <td>
            <%= item.getProduct().getDescription() %>
        </td>

        <td>
            <%= String.format("%.2f", item.getProduct().getPrice()) %>
        </td>

        <td>
            <%= String.format("%.2f", item.getAmount()) %>
        </td>

        <td>

            <form action="${pageContext.request.contextPath}/cart"
                  method="post">

                <input type="hidden"
                       name="action"
                       value="remove">

                <input type="hidden"
                       name="productCode"
                       value="<%= item.getProduct().getCode() %>">

                <input type="submit"
                       value="Remove">

            </form>

        </td>

    </tr>

    <%
        }
    %>

</table>

<%
    }
%>

</body>

</html>
