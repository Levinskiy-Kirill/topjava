<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>${meal != null ? "Edit" : "Add"} meal</title>

    <style>
        .form-row {
            margin-bottom: 10px;
        }

        .form-row__title {
            display: inline-block;
            width: 100px;
        }

        .form-row__input {
            width: 200px;
        }
    </style>
</head>
<body>
    <form action="meals" method="post">
        <div class="form-row">
            <div class="form-row__title">Date / Time:</div>
            <input class="form-row__input" type="datetime-local" name="dateTime" value="${meal.dateTime}" />
        </div>

        <div class="form-row">
            <div class="form-row__title">Description:</div>
            <input class="form-row__input" type="text" name="description" value="${meal.description}" />
        </div>

        <div class="form-row">
            <div class="form-row__title">Calories:</div>
            <input class="form-row__input" type="number" name="calories" value="${meal.calories}"/>
        </div>

        <input type="hidden" name="id" value="${meal.id}">
        <input type="hidden" name="action" value="${meal != null ? "UPDATE" : "ADD"}">

        <div>
            <a href="meals">Cancel</a>
            <input type="submit" value="Save">
        </div>
    </form>
</body>
</html>