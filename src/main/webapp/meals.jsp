<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="ru">
<head>
    <title>Meals</title>

    <style>
        table {
            border-collapse: collapse;
        }

        td, th {
            border: 1px solid #000;
            text-align: center;
            padding: 5px 10px;
        }

        .green {
            color: #008000;
        }

        .red {
            color: #ff0000;
        }

        .add-link {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>Meals</h2>

    <div class="add-link">
        <a href="meals?action=ADD">Add meal</a>
    </div>

    <table>
        <thead>
            <tr>
                <th>Дата/Время</th>
                <th>Описание</th>
                <th>Калории</th>
                <th>Редактировать</th>
                <th>Удалить</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="meal" items="${meals}">
                <tr class="${meal.excess ? 'red' : 'green'}">
                    <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                    <td><fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}" /></td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                    <td><a href="meals?action=UPDATE&id=${meal.id}">Update</a></td>
                    <td><a href="meals?action=DELETE&id=${meal.id}">Remove</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>