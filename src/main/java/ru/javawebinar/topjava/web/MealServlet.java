package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.dto.UserMealDao;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IGOR on 03.06.2016.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UserServlet.class);
    private static String INSERT_OR_EDIT = "/meal.jsp";
    private static String LIST_MEAL = "/mealList.jsp";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd h:mm:ss");
    UserMealDao dao;

    public MealServlet() {
        dao = new UserMealDao();
    }

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        LOG.debug("redirect to mealList");
//        List<UserMeal> mealList = Arrays.asList(
//                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
//                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
//                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
//                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
//                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
//                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
//        );
//        List<UserMealWithExceed> filteredMealsWithExceeded = UserMealsUtil.getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        request.setAttribute("filteredMeals", filteredMealsWithExceeded);
//        request.getRequestDispatcher("/mealList.jsp").forward(request, response);
//    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        switch (action) {
            case "delete": {
                int id = Integer.parseInt(request.getParameter("userId"));
                dao.deleteUserMeal(id);
                forward = LIST_MEAL;
                request.setAttribute("getAllUserMeals", dao.getAllUserMeals());
                break;
            }
            case "edit": {
                forward = INSERT_OR_EDIT;
                int id = Integer.parseInt(request.getParameter("userId"));
                UserMeal meal = dao.getUserMeal(id);
                request.setAttribute("meal", meal);
                break;
            }
            case "listMeal":
                forward = LIST_MEAL;
                request.setAttribute("meals", dao.getAllUserMeals());
                break;
            default:
                forward = INSERT_OR_EDIT;
                break;
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserMeal meal = new UserMeal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
        String id = request.getParameter("mealId");
        if (id == null || id.isEmpty()) {
            dao.addUserMeal(meal);
        }
        else {
            dao.updateUserMeal(Integer.parseInt(id), meal);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_MEAL);
        request.setAttribute("meals", dao.getAllUserMeals());
        view.forward(request, response);
    }
}
