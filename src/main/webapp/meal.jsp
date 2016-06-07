<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Meal</title>
</head>
<body>
<table>
    <form method="post" action="meals" name="frmAddMeal">
        <tr>
            <td>Date</td>
            <td><input type="text" name="dateTime" value="${fn:replace(meal.getDateTime(), 'T', ' ')}"/></td>

        </tr>
        <tr>
            <td>Description</td>
            <td><input type="text" name="description" value="<c:out value="${meal.getDescription()}"/>"/></td>
        </tr>
        <tr>
            <td>Calories</td>
            <td><input type="number" name="calories" value="<c:out value="${meal.getCalories()}"/>"/></td>
        </tr>
        <tr colspan="2">
            <td><input type="submit" value="Save"/></td>
        </tr>
    </form>
</table>
</body>
</html>