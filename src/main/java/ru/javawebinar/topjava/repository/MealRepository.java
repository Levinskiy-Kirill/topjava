package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealRepository {
    boolean addMeal(Meal meal);

    boolean deleteMeal(int id);

    boolean updateMeal(Meal meal);

    List<Meal> getAllMeals();

    Meal getMealById(int id);
}
