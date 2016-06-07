package ru.javawebinar.topjava.dto;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by Игорь on 04.06.2016.
 */
public class UserMealDao implements MealService {
    private Map<Integer, UserMeal> userMeal = new ConcurrentHashMap<>();

    @Override
    public void addUserMeal(UserMeal meal) {
        userMeal.put(meal.getId(), meal);
    }

    @Override
    public UserMeal getUserMeal(int id) {
        for (Map.Entry<Integer, UserMeal> user : userMeal.entrySet()) {
            if (id == user.getKey()) return user.getValue();
        }
        return null;
    }

    @Override
    public void updateUserMeal(int id, UserMeal meal) {
        userMeal.entrySet().stream().filter(user -> id == user.getKey()).forEach(user -> {
            userMeal.put(id, meal);
        });
    }

    @Override
    public void deleteUserMeal(int id) {
        userMeal.remove(id);
    }

    @Override
    public List<UserMealWithExceed> getAllUserMeals() {
        List <UserMeal> meals = userMeal.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
        return UserMealsUtil.getFilteredMealsWithExceeded(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
    }
}
