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
    private int id = 0;
    private static final Logger LOG = LoggerFactory.getLogger(UserServlet.class);
    private static String INSERT_OR_EDIT = "/meal.jsp";
    private static String LIST_MEAL = "/mealList.jsp";
    private UserMealDao dao;

    public MealServlet() {
        dao = new UserMealDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        if ("delete".equalsIgnoreCase(action)) {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            dao.deleteUserMeal(mealId);
            forward = LIST_MEAL;
            request.setAttribute("meals", dao.getAllUserMeals());
        }
        else if ("edit".equalsIgnoreCase(action)) {
            forward = INSERT_OR_EDIT;
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            id = mealId;
            UserMeal meal = dao.getUserMeal(mealId);
            request.setAttribute("meal", meal);
        }
        else if ("mealList".equalsIgnoreCase(action)) {
            forward = LIST_MEAL;
            request.setAttribute("meals", dao.getAllUserMeals());
        }
        else {
            forward = INSERT_OR_EDIT;
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserMeal meal = new UserMeal(id++,
                getLocalDateTime(request.getParameter("dateTime")),
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

    private LocalDateTime getLocalDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(date, formatter);
    }
}
