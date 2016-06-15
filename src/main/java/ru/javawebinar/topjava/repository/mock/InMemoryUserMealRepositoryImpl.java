package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {
    private Map<Integer, UserMeal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.MEAL_LIST.forEach((UserMeal userMeal) -> save(userMeal, LoggedUser.getId()));
    }

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        userMeal.setUserId(userId);
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }
        repository.put(userMeal.getId(), userMeal);
        return userMeal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return !(!repository.containsKey(id) || repository.get(id).getUserId() != userId) && repository.remove(id, repository.get(id));
    }

    @Override
    public UserMeal get(int id, int userId) {
        if (!repository.containsKey(id) || repository.get(id).getUserId() != userId) return null;
        return repository.get(id);
    }

    @Override
    public Collection<UserMealWithExceed> getAll(int userId) {
        List<UserMeal> meals = repository.entrySet().stream()
                .filter(pair -> pair.getValue().getUserId() == userId).map(Map.Entry::getValue)
                .sorted(((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime())))
                .collect(Collectors.toList());
        return UserMealsUtil.getWithExceeded(meals, UserMealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    @Override
    public Collection<UserMealWithExceed> getAllFiltered(int userId, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        List<UserMeal> meals = repository.entrySet().stream()
                .filter(pair -> pair.getValue().getUserId() == userId)
                .filter(pair -> TimeUtil.isBetween(pair.getValue().getDateTime().toLocalDate(), startDate, endDate))
                .filter(pair -> TimeUtil.isBetween(pair.getValue().getDateTime().toLocalTime(), startTime, endTime))
                .sorted(((o1, o2) -> o2.getValue().getDateTime().compareTo(o1.getValue().getDateTime())))
                .map(Map.Entry::getValue).collect(Collectors.toList());
        return UserMealsUtil.getWithExceeded(meals, UserMealsUtil.DEFAULT_CALORIES_PER_DAY);
    }
}

