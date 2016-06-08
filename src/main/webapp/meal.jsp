<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Редактировать еду</title>
    <link rel="stylesheet" type="text/css" href="css/edit.css">
</head>
<body>
<form method="post" action="meals" name="frmAddMeal">
    <div class="form">
        <table>
            <thead>
                <tr>
                    <td class="head" colspan="2">Редактировать еду</td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td class="info">Date</td>
                    <td><input type="text" name="dateTime" value="${fn:replace(meal.dateTime, 'T', ' ')}"/></td>
                </tr>
                <tr>
                    <td class="info">Description</td>
                    <td><input type="text" name="description" value="<c:out value="${meal.description}"/>"/></td>
               </tr>
                <tr>
                    <td class="info">Calories</td>
                    <td><input type="number" name="calories" value="<c:out value="${meal.calories}"/>"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input class="button" type="submit" value="Save"/></td>
                </tr>
            </tbody>
        </table>
    </div>
</form>
</body>
</html>