package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceTest {
    @Autowired
    protected UserMealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        UserMeal userMeal = service.get(um1.getId(), USER_ID);
        MATCHER.assertEquals(um1, userMeal);
    }

    @Test(expected = NotFoundException.class)
    public void testGetWrongUser() throws Exception {
        UserMeal userMeal = service.get(am1.getId(), USER_ID);
        MATCHER.assertEquals(am1, userMeal);
    }
    
    @Test
    public void testDelete() throws Exception {
        service.delete(um1.getId(), USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(um2, um3, um4, um5, um6), service.getAll(LoggedUser.id()));
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteWrongUser() throws Exception {
        service.delete(am1.getId(), USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(um2, um3, um4, um5, um6), service.getAll(USER_ID));
    }

    @Test
    public void testGetBetweenDates() throws Exception {
        Collection<UserMeal> dates = service.getBetweenDates(LocalDate.of(2015, 5, 30), LocalDate.of(2015, 5, 30), USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(um1, um2, um3), dates);
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        Collection<UserMeal> dateTimes = service.getBetweenDateTimes(LocalDateTime.of(2015, 5, 30, 10, 0), LocalDateTime.of(2015, 5, 30, 13, 0), USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(um1, um2), dateTimes);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<UserMeal> all = service.getAll(USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(um1, um2, um3, um4, um5, um6), all);
    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal updated = new UserMeal(um1);
        updated.setDescription("Updated description");
        updated.setCalories(1100);
        service.update(updated, USER_ID);
        MATCHER.assertEquals(updated, service.get(um1.getId(), USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateWrongMeal() throws Exception {
        UserMeal updated = new UserMeal(um1);
        updated.setDescription("Updated description");
        updated.setCalories(1200);
        service.update(updated, ADMIN_ID);
        MATCHER.assertEquals(updated, service.get(um1.getId(), ADMIN_ID));
    }

    @Test
    public void testSave() throws Exception {
        MealTestData tum = new MealTestData(null, LocalDateTime.now(), "description", 200);
        UserMeal created = service.save(tum.asUserMeal(), USER_ID);
        tum.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(um1, um2, um3, um4, um5, um6, tum), service.getAll(USER_ID));
    }
}