package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.ValidationUtil;

import java.util.List;

@Service
public class MealService {

    private MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(Meal meal, int userId) {
        return repository.save(meal, userId);
    }

    public Meal update(Meal meal, int userId) {
        return ValidationUtil.checkNotFoundWithId(repository.save(meal, userId), meal.getId());
    }

    public boolean delete(int id, int userId) {
        boolean isFound = repository.delete(id, userId);
        ValidationUtil.checkNotFoundWithId(isFound, id);
        return isFound;
    }

    public Meal get(int id, int userId) {
        return ValidationUtil.checkNotFoundWithId(repository.get(id, userId), id);
    }

    public List<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    /*public List<Meal> findByDate(int userId, LocalDate from, LocalDate to) {
        return getFilteredMeals(userId, meal -> DateTimeUtil.isBetweenHalfOpen(meal.getDate(), from, to));
    }

    public List<Meal> findByTime(int userId, LocalTime from, LocalTime to) {
        return getFilteredMeals(userId, meal -> DateTimeUtil.isBetweenHalfOpen(meal.getTime(), from, to));
    }

    public List<Meal> findByDateTime(int userId, LocalDateTime from, LocalDateTime to) {
        return getFilteredMeals(userId, meal -> DateTimeUtil.isBetweenHalfOpen(meal.getDateTime(), from, to));
    }

    private List<Meal> getFilteredMeals(int userId, Predicate<Meal> filterPredicate) {
        return getAll(userId).stream()
                .filter(filterPredicate)
                .collect(Collectors.toList());
    }*/
}