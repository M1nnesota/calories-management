package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Objects;

@Controller
public class MealController extends UserMealRestController {

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String mealList(Model model) {
        model.addAttribute("mealList", getAll());
        return "mealList";
    }

    @RequestMapping(value = "/meals", params = {"action=delete", "id"}, method = RequestMethod.GET)
    public String deleteMeal(Model model, @RequestParam int id) {
        delete(id);
        return mealList(model);
    }

    @RequestMapping(value = "/meals", params = "action=create", method = RequestMethod.GET)
    public String createMeal(Model model, HttpServletRequest request) {
        UserMeal meal = new UserMeal(LocalDateTime.now().withNano(0).withSecond(0), "", 1000);
        model.addAttribute("meal", meal);
        return "mealEdit";
    }

    @RequestMapping(value = "/meals", params = {"action=update", "id"}, method = RequestMethod.GET)
    public String updateMeal(Model model, @RequestParam int id) {
        UserMeal meal = get(id);
        model.addAttribute("meal", meal);
        return "mealEdit";
    }

    @RequestMapping(value = "/meals", method = RequestMethod.POST)
    public String postMeal(Model model, HttpServletRequest request) {
        final UserMeal userMeal = new UserMeal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));
        if (request.getParameter("id").isEmpty()) {
            create(userMeal);
        } else {
            String id = Objects.requireNonNull(request.getParameter("id"));
            update(userMeal, Integer.parseInt(id));
        }
        return mealList(model);
    }

    @RequestMapping(value = "/meals", params = "action=filter", method = RequestMethod.POST)
    public String filterMeal(HttpServletRequest request) {
        LocalDate startDate = TimeUtil.parseLocalDate(resetParam("startDate", request));
        LocalDate endDate = TimeUtil.parseLocalDate(resetParam("endDate", request));
        LocalTime startTime = TimeUtil.parseLocalTime(resetParam("startTime", request));
        LocalTime endTime = TimeUtil.parseLocalTime(resetParam("endTime", request));
        request.setAttribute("mealList", getBetween(startDate, startTime, endDate, endTime));
        return "mealList";
    }

    private String resetParam(String param, HttpServletRequest request) {
        String value = request.getParameter(param);
        request.setAttribute(param, value);
        return value;
    }
}
