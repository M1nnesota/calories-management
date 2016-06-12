package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

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
    // for testing
    protected final Logger LOG = LoggerFactory.getLogger(getClass());
    private Map<Integer, UserMeal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.MEAL_LIST.forEach((UserMeal userMeal) -> {
            save(userMeal, LoggedUser.getId());
        });
    }

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        userMeal.setUserId(LoggedUser.getId());
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }
        LOG.info("User id is " + userMeal.getUserId());
        if (userId == userMeal.getUserId()) {
            repository.put(userMeal.getId(), userMeal);
        }
        else throw new NotFoundException("Meal for this user is not found");
        return userMeal;
    }

    @Override
    public boolean delete(int id, int userId) {
        if (repository.get(id).getUserId() != userId) throw new NotFoundException("Meal for this user is not found");
        return repository.remove(id, repository.get(id));
    }

    @Override
    public UserMeal get(int id, int userId) {
        if (repository.get(id).getUserId() != userId) throw new NotFoundException("Meal for this user is not found");
        return repository.get(id);
    }

    @Override
    public Collection<UserMeal> getAll(int userId) {
        List<UserMeal> meals = repository.entrySet().stream().filter(pair -> pair.getValue().getUserId() == userId).map(Map.Entry::getValue).collect(Collectors.toList());
        if (meals.size() == 0) throw new NotFoundException("Meal for this user is not found");
        Collections.sort(meals, ((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime())));
        return meals;
    }
}

