<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Meal list</title>
</head>
<body>
    <h2><a href="index.html">Home</a> </h2>
    <h2>Meal list</h2>
    <p><a href="meals?action=insert">Добавить еду</a></p>
    <table>
        <thead>
            <tr>
                <th>Date</th>
                <th>Description</th>
                <th>Calories</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="meal" items="${meals}">
                <c:choose>
                    <c:when test="${meal.isExceed() == false}">
                        <tr>
                            <td style="color: green"><c:out value="${fn:replace(meal.getDateTime(), 'T', ' ')}"/></td>
                            <td style="color: green"><c:out value="${meal.getDescription()}"/></td>
                            <td style="color: green"><c:out value="${meal.getCalories()}"/></td>
                            <td><a href="meals?action=edit&mealId=<c:out value="${meal.getId()}"/>">Edit</a></td>
                            <td><a href="meals?action=delete&mealId=<c:out value="${meal.getId()}"/>">Delete</a></td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td style="color: red"><c:out value="${fn:replace(meal.getDateTime(), 'T', ' ')}"/></td>
                            <td style="color: red"><c:out value="${meal.getDescription()}"/></td>
                            <td style="color: red"><c:out value="${meal.getCalories()}"/></td>
                            <td><a href="meals?action=edit&mealId=<c:out value="${meal.getId()}"/>">Edit</a></td>
                            <td><a href="meals?action=delete&mealId=<c:out value="${meal.getId()}"/>">Delete</a></td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
