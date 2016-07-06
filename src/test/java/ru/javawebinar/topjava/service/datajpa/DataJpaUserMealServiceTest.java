package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.UserTestData.*;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealServiceTest;

import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by Игорь on 03.07.2016.
 */
@ActiveProfiles({Profiles.DATAJPA, Profiles.ACTIVE_DB})
public class DataJpaUserMealServiceTest extends UserMealServiceTest {
    @Test
    public void testGetWithUser() {
        UserMeal um = service.getWithUser(MEAL1_ID, UserTestData.USER_ID);
        MATCHER.assertEquals(um, MEAL1);
        UserTestData.MATCHER.assertEquals(UserTestData.USER, um.getUser());
    }
}
