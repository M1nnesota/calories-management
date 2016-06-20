package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDateTime;
import java.time.Month;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData extends UserMeal {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final UserMeal um1 = new UserMeal(1, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static final UserMeal um2 = new UserMeal(2, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
    public static final UserMeal um3 = new UserMeal(3, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
    public static final UserMeal um4 = new UserMeal(4, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000);
    public static final UserMeal um5 = new UserMeal(5, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
    public static final UserMeal um6 = new UserMeal(6, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);
    public static final UserMeal am1 = new UserMeal(7, LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510);
    public static final UserMeal am2 = new UserMeal(8, LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500);

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

    public MealTestData(UserMeal um) {
        this(um.getId(), um.getDateTime(), um.getDescription(), um.getCalories());
    }

    public MealTestData(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, UserMealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public MealTestData(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id, dateTime, description, calories);
    }

    public UserMeal asUserMeal() {
        return new UserMeal(this);
    }
}
