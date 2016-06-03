<%--
  Created by IntelliJ IDEA.
  User: IGOR
  Date: 03.06.2016
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meal list</title>
</head>
<body>
    <h2><a href="index.html">Home</a> </h2>
    <h2>Meal list</h2>
    <table>
        <tr>
            <td>Date</td>
            <td>Description</td>
            <td>Calories</td>
        </tr>
        <c:forEach var="meal" items="${filteredMeals}">
            <c:choose>
                <c:when test="${meal.isExceed() == false}">
                    <tr style="color: green">
                        <td>${meal.getDateTime().toLocalDate()}</td>
                        <td>${meal.getDescription()}</td>
                        <td>${meal.getCalories()}</td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr style="color: red">
                        <td>${meal.getDateTime().toLocalDate()}</td>
                        <td>${meal.getDescription()}</td>
                        <td>${meal.getCalories()}</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </table>
</body>
</html>
