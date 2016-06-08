<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Meal list</title>
    <link rel="stylesheet" type="text/css" href="./css/mealList.css">
</head>
<body>
<div class="form">
    <h2><a href="index.html">Home</a> </h2>
    <h2>Список еды</h2>
    <p><a href="meals?action=insert" class="add">Добавить еду</a></p>
    <table>
        <thead>
            <tr>
                <th>Date</th>
                <th>Description</th>
                <th colspan="3">Calories</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="meal" items="${meals}">
                <c:choose>
                    <c:when test="${meal.exceed == false}">
                        <tr>
                            <td class="greenstyle"><c:out value="${fn:replace(meal.dateTime, 'T', ' ')}"/></td>
                            <td class="greenstyle"><c:out value="${meal.description}"/></td>
                            <td class="greenstyle"><c:out value="${meal.calories}"/></td>
                            <td><a class="edit" href="meals?action=edit&mealId=<c:out value="${meal.id}"/>">Edit</a></td>
                            <td><a class="delete" href="meals?action=delete&mealId=<c:out value="${meal.id}"/>">Delete</a></td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td class="redstyle"><c:out value="${fn:replace(meal.dateTime, 'T', ' ')}"/></td>
                            <td class="redstyle"><c:out value="${meal.description}"/></td>
                            <td class="redstyle"><c:out value="${meal.calories}"/></td>
                            <td><a class="edit" href="meals?action=edit&mealId=<c:out value="${meal.id}"/>">Edit</a></td>
                            <td><a class="delete" href="meals?action=delete&mealId=<c:out value="${meal.id}"/>">Delete</a></td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
