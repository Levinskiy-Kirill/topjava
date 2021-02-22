package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.SecurityUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management (ARM)
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));

            /*AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));*/

            MealRestController mealController = appCtx.getBean(MealRestController.class);

            System.out.println("All meals user with id = " + SecurityUtil.authUserId());
            mealController.getAll().forEach(System.out::println);

            System.out.println(" \nGet meal with id = 5");
            System.out.println(mealController.get(5));

            System.out.println(" \nSave new meal");
            mealController.save(new Meal(LocalDateTime.now(), "New meal", 456));

            System.out.println("Update meal with id = 6");
            mealController.save(new Meal(6, LocalDateTime.now(), "updated meal", 1456));

            System.out.println("Delete meal with id = 4");
            mealController.delete(4);

            mealController.getAll().forEach(System.out::println);

            System.out.println("===========MealService test===============");
            MealService mealService = appCtx.getBean(MealService.class);
//            mealService.update(new Meal(6, LocalDateTime.now(), "updated meal", 1456), 2);
//            mealService.get(6, 2);
//            mealService.delete(4, 2);
//            mealService.getAll(2);
        }
    }
}
