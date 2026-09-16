<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CD List</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/style.css">
</head>

<body>

<h1>CD List</h1>

<table>
    <tr>
        <th>Description</th>
        <th>Price</th>
        <th></th>
    </tr>

    <tr>
        <td>
            86 (the band) - True Life Songs and Pictures
        </td>

        <td>
            14.95
        </td>

        <td>
            <form action="${pageContext.request.contextPath}/add"
                  method="post">

                <input type="hidden"
                       name="productCode"
                       value="8601">

                <input type="submit"
                       value="Add To Cart">
            </form>
        </td>
    </tr>

    <tr>
        <td>
            Paddlefoot - The first CD
        </td>

        <td>
            12.95
        </td>

        <td>
            <form action="${pageContext.request.contextPath}/add"
                  method="post">

                <input type="hidden"
                       name="productCode"
                       value="pf01">

                <input type="submit"
                       value="Add To Cart">
            </form>
        </td>
    </tr>

    <tr>
        <td>
            Paddlefoot - The second CD
        </td>

        <td>
            14.95
        </td>

        <td>
            <form action="${pageContext.request.contextPath}/add"
                  method="post">

                <input type="hidden"
                       name="productCode"
                       value="pf02">

                <input type="submit"
                       value="Add To Cart">
            </form>
        </td>
    </tr>
</table>

</body>
</html>