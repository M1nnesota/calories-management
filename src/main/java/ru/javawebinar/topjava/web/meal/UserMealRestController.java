package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;

import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMealService service;

    public UserMeal create(UserMeal userMeal, int userId) {
//        userMeal.setId(null);
        LOG.info("create " + userMeal);
        return service.save(userMeal, userId);
    }

    public void delete(int id, int userId) {
        LOG.info("delete " + id);
        service.delete(id, userId);
    }

    public UserMeal get(int id, int userId) {
        LOG.info("get " + id);
        return service.get(id, userId);
    }

    public Collection<UserMeal> getAll() {
        LOG.info("getAll");
        return service.getAll();
    }

    public void update(UserMeal userMeal, int id, int userId) {
        userMeal.setId(id);
        LOG.info("update " + userMeal);
        service.update(userMeal, userId);
    }
}
