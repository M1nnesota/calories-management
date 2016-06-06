package ru.javawebinar.topjava.controller;

import ru.javawebinar.topjava.dto.UserMealDao;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Игорь on 04.06.2016.
 */
public class UserMealController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/meal.jsp";
    private static String LIST_MEAL = "/mealList.jsp";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd h:mm:ss");
    UserMealDao dao;

    public UserMealController() {
        dao = new UserMealDao();
    }

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String forward = "";
//        String action = request.getParameter("action");
//        switch (action) {
//            case "delete": {
//                int id = Integer.parseInt(request.getParameter("userId"));
//                dao.deleteUserMeal(id);
//                forward = LIST_MEAL;
//                request.setAttribute("getAllUserMeals", dao.getAllUserMeals());
//                break;
//            }
//            case "edit": {
//                forward = INSERT_OR_EDIT;
//                int id = Integer.parseInt(request.getParameter("userId"));
//                UserMeal meal = dao.getUserMeal(id);
//                request.setAttribute("meal", meal);
//                break;
//            }
//            case "listMeal":
//                forward = LIST_MEAL;
//                request.setAttribute("meals", dao.getAllUserMeals());
//                break;
//            default:
//                forward = INSERT_OR_EDIT;
//                break;
//        }
//        RequestDispatcher view = request.getRequestDispatcher(forward);
//        view.forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        UserMeal meal = new UserMeal(
//                LocalDateTime.parse(request.getParameter("dateTime")),
//                request.getParameter("description"),
//                Integer.parseInt(request.getParameter("calories")));
//        String id = request.getParameter("mealId");
//        if (id == null || id.isEmpty()) {
//            dao.addUserMeal(meal);
//        }
//        else {
//            dao.updateUserMeal(Integer.parseInt(id), meal);
//        }
//        RequestDispatcher view = request.getRequestDispatcher(LIST_MEAL);
//        request.setAttribute("meals", dao.getAllUserMeals());
//        view.forward(request, response);
//    }
}
