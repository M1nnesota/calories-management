package ru.javawebinar.topjava.web.meal;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.LocalDateTimeConverter;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(UserMealRestController.REST_URL)
public class UserMealRestController extends AbstractUserMealController {
    static final String REST_URL = "/meals";

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserMeal get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMealWithExceed> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody UserMeal meal, @PathVariable("id") int id) {
        super.update(meal, id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserMeal> createWithLocation(@RequestBody UserMeal meal) {
        UserMeal created = super.create(meal);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @RequestMapping(value = "/between", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMealWithExceed> getBetween(@RequestParam(value = "startDate") String startDateTime,
                                               @RequestParam(value = "endDate") String endDateTime) {
        LocalDate startDate = LocalDateTimeConverter.convert(startDateTime).toLocalDate();
        LocalTime startTime = LocalDateTimeConverter.convert(startDateTime).toLocalTime();
        LocalDate endDate = LocalDateTimeConverter.convert(endDateTime).toLocalDate();
        LocalTime endTime = LocalDateTimeConverter.convert(endDateTime).toLocalTime();
        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}