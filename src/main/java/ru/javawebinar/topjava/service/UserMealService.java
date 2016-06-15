package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

/**
 * GKislin
 * 15.06.2015.
 */
public interface UserMealService {
    UserMeal save(UserMeal userMeal, int userId);

    void delete(int id, int userId);

    UserMeal get(int id, int userId);

    Collection<UserMealWithExceed> getAll(int userId);

    Collection<UserMealWithExceed> getAllFiltered(int userId, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime);
}
