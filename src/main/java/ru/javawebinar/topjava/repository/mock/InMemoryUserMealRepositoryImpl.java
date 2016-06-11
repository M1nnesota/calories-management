package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;

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
        UserMealsUtil.MEAL_LIST.forEach((UserMeal userMeal) -> {
            save(userMeal, LoggedUser.id());
        });
    }

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }
        repository.put(userMeal.getId(), userMeal);
        return userMeal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return repository.remove(id, repository.get(id));
    }

    @Override
    public UserMeal get(int id, int userId) {
        return repository.get(id);
    }

    @Override
    public Collection<UserMeal> getAll() {
        List<UserMeal> meals = repository.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
        Collections.sort(meals, ((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime())));
        return meals;
    }
}

