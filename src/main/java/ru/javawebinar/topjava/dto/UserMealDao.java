package ru.javawebinar.topjava.dto;

import ru.javawebinar.topjava.business.DataStorage;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.util.List;
import java.util.Map;

/**
 * Created by Игорь on 04.06.2016.
 */
public class UserMealDao implements MealService {
    private DataStorage dataStorage = new DataStorage();

    @Override
    public void addUserMeal(UserMeal meal) {
        dataStorage.userMeal.put(meal.getId(), meal);
    }

    @Override
    public UserMeal getUserMeal(int id) {
        for (Map.Entry<Integer, UserMeal> user : dataStorage.userMeal.entrySet()) {
            if (id == user.getKey()) return user.getValue();
        }
        return null;
    }

    @Override
    public void updateUserMeal(int id, UserMeal meal) {
        dataStorage.userMeal.entrySet().stream().filter(user -> id == user.getKey()).forEach(user -> {
            dataStorage.userMeal.put(id, meal);
        });
    }

    @Override
    public void deleteUserMeal(int id) {
        dataStorage.userMeal.entrySet().stream().filter(user -> id == user.getKey()).forEach(user -> dataStorage.userMeal.remove(user.getKey()));
    }

    @Override
    public List<UserMeal> getAllUserMeals() {
        return (List<UserMeal>) dataStorage.userMeal.values();
    }
}
