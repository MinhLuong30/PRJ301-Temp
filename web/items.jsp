<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ITEM Page</title>
</head>
<body>
    <h1>Welcome ${sessionScope.LOGIN_AD.fullname}</h1>
    <a href="login.jsp">Logout</a>
    <form action="MainController" method="POST">
        <input type="text" name="search" value="${param.search}"/>
        <input type="submit" name="action" value="Search"/>
    </form>

    <table border="1">
        <thead>
            <tr>
                <th>NO</th>
                <th>ID</th>
                <th>NAME</th>
                <th>DESCRIPTION</th>
                <th>PRICE</th>
                <th>SIZE</th>
                <th>ACTION</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${sessionScope.ITEMS}" var="i" varStatus="counter">
                <tr>
                    <td>${counter.count}</td>
                    <td>${i.id}</td>
                    <td>${i.name}</td>
                    <td>${i.description}</td>
                    <td>${i.price}</td>
                    <td>${i.size}</td>
                    <td>
                        <a href="MainController?action=Delete&id=${i.id}">Delete</a>
                        <form action="MainController" method="POST" style="display: inline;">
                            <input type="hidden" name="id" value="${i.id}" />
                            <input type="submit" value="Add" name="action"/>
                        </form>                     
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <c:if test="${sessionScope.CART.size() > 0}">
        <table border="1">
            <thead>
                <tr>
                    <th>NO</th>
                    <th>ID</th>
                    <th>NAME</th>
                    <th>DESCRIPTION</th>
                    <th>PRICE</th>
                    <th>SIZE</th>
                    <th>Quantity</th>
                    <th>Total</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${sessionScope.CART}" var="p" varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td>${p.id}</td>
                        <td>${p.name}</td>
                        <td>${p.description}</td>
                        <td>${p.price}</td>
                        <td>${p.size}</td>
                        <td>${p.quantity}</td>
                        <td>${p.quantity * p.price}</td>
                        <td>
                            <!-- Nút Delete (hoặc Update) -->
                            <form action="MainController" method="POST">
                                <input type="hidden" name="id" value="${p.id}" />
                                <input type="submit" value="Remove" name="action" />
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <h3>Total: ${sessionScope.TOTAL} Triệu</h3>
    </c:if>
</body>
</html>
