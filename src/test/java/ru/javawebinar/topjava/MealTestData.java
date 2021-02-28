package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.AbstractBaseEntity;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int MEAL_ID = AbstractBaseEntity.START_SEQ + 2;
    public static final int NOT_FOUND_ID = 100;

    public static final Meal MEAL_1 = new Meal(AbstractBaseEntity.START_SEQ + 2, LocalDateTime.of(2021, Month.FEBRUARY, 26, 10, 0), "Завтрак", 500);
    public static final Meal MEAL_2 = new Meal(AbstractBaseEntity.START_SEQ + 3, LocalDateTime.of(2021, Month.FEBRUARY, 26, 13, 0), "Обед", 1000);
    public static final Meal MEAL_3 = new Meal(AbstractBaseEntity.START_SEQ + 4, LocalDateTime.of(2021, Month.FEBRUARY, 26, 20, 0), "Ужин", 500);
    public static final Meal MEAL_4 = new Meal(AbstractBaseEntity.START_SEQ + 5, LocalDateTime.of(2021, Month.FEBRUARY, 27, 9, 0), "Еда на граничное значение", 100);
    public static final Meal MEAL_5 = new Meal(AbstractBaseEntity.START_SEQ + 6, LocalDateTime.of(2021, Month.FEBRUARY, 27, 10, 0), "Завтрак", 500);
    public static final Meal MEAL_6 = new Meal(AbstractBaseEntity.START_SEQ + 7, LocalDateTime.of(2021, Month.FEBRUARY, 27, 13, 0), "Обед", 1000);
    public static final Meal MEAL_7 = new Meal(AbstractBaseEntity.START_SEQ + 8, LocalDateTime.of(2021, Month.FEBRUARY, 27, 20, 0), "Ужин", 410);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2021, Month.FEBRUARY, 28, 10, 0, 0), "New meal", 1500);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(MEAL_1);
        updated.setDescription("Updated description");
        updated.setDateTime(LocalDateTime.of(2021, Month.FEBRUARY, 28, 10, 0, 0));
        updated.setCalories(300);
        return updated;
    }
}
