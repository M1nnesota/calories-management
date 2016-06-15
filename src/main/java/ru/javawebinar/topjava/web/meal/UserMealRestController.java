package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMealService service;

    public Collection<UserMealWithExceed> getAll() {
        LOG.info("getAll");
        return service.getAll(LoggedUser.getId());
    }

    public Collection<UserMealWithExceed> getAllFiltered(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        LOG.info("getAllFiltered");
        if (startTime == null) startTime = LocalTime.MIN;
        if (endTime == null) endTime = LocalTime.MAX;
        if (startDate == null) startDate = LocalDate.MIN;
        if (endDate == null) endDate = LocalDate.MAX;
        return service.getAllFiltered(LoggedUser.getId(), startDate, startTime, endDate, endTime);
    }

    public UserMeal get(int id) {
        if (service.get(id, LoggedUser.getId()) == null) throw new NotFoundException("Meal is not found");
        LOG.info("get " + id);
        return service.get(id, LoggedUser.getId());
    }

    public void delete(int id) {
        if (service.get(id, LoggedUser.getId()) == null) throw new NotFoundException("Meal is not found");
        LOG.info("delete " + id);
        service.delete(id, LoggedUser.getId());
    }

    public UserMeal save(UserMeal userMeal) {
        LOG.info("create " + userMeal);
        return service.save(userMeal, LoggedUser.getId());
    }
}
