package ru.javawebinar.topjava.repository.jdbc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.ADMIN_ID;
import static ru.javawebinar.topjava.MealTestData.MEAL_1;
import static ru.javawebinar.topjava.MealTestData.MEAL_2;
import static ru.javawebinar.topjava.MealTestData.MEAL_3;
import static ru.javawebinar.topjava.MealTestData.MEAL_4;
import static ru.javawebinar.topjava.MealTestData.MEAL_5;
import static ru.javawebinar.topjava.MealTestData.MEAL_6;
import static ru.javawebinar.topjava.MealTestData.MEAL_7;
import static ru.javawebinar.topjava.MealTestData.MEAL_ID;
import static ru.javawebinar.topjava.MealTestData.NOT_FOUND_ID;
import static ru.javawebinar.topjava.MealTestData.USER_ID;
import static ru.javawebinar.topjava.MealTestData.getNew;
import static ru.javawebinar.topjava.MealTestData.getUpdated;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class JdbcMealRepositoryTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    MealRepository repository;

    @Test
    public void get() {
        Meal meal = repository.get(MEAL_ID, USER_ID);
        Assert.assertEquals(MEAL_1, meal);
    }

    @Test
    public void getForeign() {
        Assert.assertNull(repository.get(MEAL_ID, ADMIN_ID));
    }

    @Test
    public void getNotFound() {
        Assert.assertNull(repository.get(NOT_FOUND_ID, USER_ID));
    }

    @Test
    public void delete() {
        repository.delete(MEAL_ID, USER_ID);
        List<Meal> expected = Arrays.asList(MEAL_7, MEAL_6, MEAL_5, MEAL_4, MEAL_3, MEAL_2);
        Assert.assertEquals(repository.getAll(USER_ID), expected);
    }

    @Test
    public void deleteForeign() {
        Assert.assertFalse(repository.delete(MEAL_ID, ADMIN_ID));
    }

    @Test
    public void deleteNotFound() {
        Assert.assertFalse(repository.delete(NOT_FOUND_ID, USER_ID));
    }

    @Test
    public void getBetweenHalfOpen() {
        LocalDateTime start = DateTimeUtil.atStartOfDayOrMin(LocalDate.of(2021, Month.FEBRUARY, 27));
        LocalDateTime end = DateTimeUtil.atStartOfNextDayOrMax(LocalDate.of(2021, Month.FEBRUARY, 27));
        List<Meal> filteredMeals = repository.getBetweenHalfOpen(start, end, USER_ID);
        List<Meal> expected = Arrays.asList(MEAL_7, MEAL_6, MEAL_5, MEAL_4);

        Assert.assertEquals(filteredMeals, expected);
    }

    @Test
    public void getAll() {
        Assert.assertEquals(repository.getAll(USER_ID), Arrays.asList(MEAL_7, MEAL_6, MEAL_5, MEAL_4, MEAL_3, MEAL_2, MEAL_1));
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        repository.save(updated, USER_ID);
        Assert.assertEquals(repository.get(updated.getId(), USER_ID), updated);
    }

    @Test
    public void updateForeign() {
        Assert.assertNull(repository.save(getUpdated(), ADMIN_ID));
    }

    @Test
    public void updateNotFound() {
        Meal updated = getUpdated();
        updated.setId(NOT_FOUND_ID);
        Assert.assertNull(repository.save(updated, USER_ID));
    }

    @Test
    public void create() {
        Meal created = repository.save(getNew(), USER_ID);
        Integer newId = created.getId();
        Meal newMeal = getNew();
        newMeal.setId(newId);

        Assert.assertEquals(created, newMeal);
        Assert.assertEquals(repository.get(newId, USER_ID), newMeal);
    }

    @Test
    public void createWithDuplicateDateTime() {
        Meal newMeal = getNew();
        newMeal.setDateTime(MEAL_1.getDateTime());
        Assert.assertThrows(DataAccessException.class, () -> repository.save(newMeal, USER_ID));
    }
}