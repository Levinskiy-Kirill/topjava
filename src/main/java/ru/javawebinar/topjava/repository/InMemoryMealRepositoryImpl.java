package ru.javawebinar.topjava.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);

    private static AtomicInteger counter;

    private static Map<Integer, Meal> store;

    public InMemoryMealRepositoryImpl() {
        counter = new AtomicInteger(1);

        store = new ConcurrentHashMap<>();

        MealsUtil.MEALS.forEach(meal -> {
            int id = counter.getAndIncrement();
            meal.setId(id);
            store.put(id, meal);
        });
    }

    @Override
    public boolean addMeal(Meal meal) {
        if(store.containsKey(meal.getId())) {
            log.debug("Meal with id = {} already exist", meal.getId());
            return false;
        }

        meal.setId(counter.getAndIncrement());
        store.put(meal.getId(), meal);
        return true;
    }

    @Override
    public boolean deleteMeal(int id) {
        return store.remove(id) != null;
    }

    @Override
    public boolean updateMeal(Meal meal) {
        if(!store.containsKey(meal.getId())) {
            log.debug("Not found meal with id = {} for update", meal.getId());
            return false;
        }

        store.put(meal.getId(), meal);
        return true;
    }

    @Override
    public List<Meal> getAllMeals() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Meal getMealById(int id) {
        return store.get(id);
    }
}
