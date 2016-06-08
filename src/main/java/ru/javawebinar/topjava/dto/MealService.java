package ru.javawebinar.topjava.dto;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.util.List;

interface MealService {
    void addUserMeal(UserMeal meal);
    UserMeal getUserMeal(int id);
    void updateUserMeal(int id, UserMeal meal);
    void deleteUserMeal(int id);
    List<UserMealWithExceed> getAllUserMeals();
}
