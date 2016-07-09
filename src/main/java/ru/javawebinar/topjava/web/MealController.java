package ru.javawebinar.topjava.web;

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
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Collection;

@Controller
public class MealController {
    @Autowired
    UserMealService service;

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String mealList(Model model) {
        Collection<UserMealWithExceed> meals = UserMealsUtil.getWithExceeded(service.getAll(AuthorizedUser.id()),
                AuthorizedUser.getCaloriesPerDay());
        model.addAttribute("mealList", meals);
        return "mealList";
    }

    @RequestMapping(value = "/meals?action=delete&id={id}", params = "id", method = RequestMethod.GET)
    public String deleteMeal(@RequestParam("id") String id) {
        service.delete(Integer.parseInt(id), AuthorizedUser.id());
        // TODO: 08.07.2016 - params aren't working 
        return "mealList";
    }

    @RequestMapping(value = "/meals?action=create", method = RequestMethod.GET)
    public String createMeal(Model model) {
        UserMeal meal = new UserMeal(LocalDateTime.now().withNano(0).withSecond(0), "", 1000);
        model.addAttribute("meal", meal);
        return "mealEdit";
    }

    @RequestMapping(value = "/meals?action=update&id={id}", params = "id", method = RequestMethod.GET)
    public String updateMeal(Model model, @RequestParam("id") String id) {
        UserMeal meal = service.get(Integer.parseInt(id), AuthorizedUser.id());
        model.addAttribute("meal", meal);
        return "mealEdit";
    }

    public String filterMeal() {
        return "mealList";
    }

    private String resetParam(String param, HttpServletRequest request) {
        String value = request.getParameter(param);
        request.setAttribute(param, value);
        return value;
    }
}
