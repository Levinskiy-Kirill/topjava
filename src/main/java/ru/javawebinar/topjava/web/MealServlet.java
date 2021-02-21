package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    private static MealRepository repository;

    @Override
    public void init() throws ServletException {
        repository = new InMemoryMealRepositoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if(action == null) {
            List<MealTo> meals = MealsUtil.filteredByStreams(repository.getAllMeals(), LocalTime.MIN, LocalTime.MAX, MealsUtil.CALORIES_PER_DAY);

            request.setAttribute("meals", meals);
            request.getRequestDispatcher("/meals.jsp").forward(request, response);

            return;
        }

        Integer id;

        switch (action) {
            case "ADD":
                request.getRequestDispatcher("/editMeal.jsp").forward(request, response);
                break;

            case "UPDATE":
                id = toInteger(request.getParameter("id"));
                Meal meal = repository.getMealById(id);

                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/editMeal.jsp").forward(request, response);

                break;

            case "DELETE":
                id = toInteger(request.getParameter("id"));
                repository.deleteMeal(id);
                response.sendRedirect("meals");
                break;

            default:
                log.warn("Unsupported operation type = {}", action);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Integer id = !request.getParameter("id").equals("")
                ? toInteger(request.getParameter("id"))
                : null;

        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
        String description = request.getParameter("description");
        int calories = toInteger(request.getParameter("calories"));

        Meal meal = id != null ? repository.getMealById(id) : new Meal();

        meal.setDateTime(dateTime);
        meal.setDescription(description);
        meal.setCalories(calories);

        if(id != null) {
            repository.updateMeal(meal);
        } else {
            repository.addMeal(meal);
        }

        response.sendRedirect("meals");
    }

    private Integer toInteger(String id) {
        try {
            return Integer.valueOf(id);
        } catch (NumberFormatException e) {
            log.error("Error parse meal id ={}", id, e);
            throw new IllegalArgumentException();
        }
    }
}
