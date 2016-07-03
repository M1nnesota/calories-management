package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by Игорь on 03.07.2016.
 */
@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserMealServiceTest extends UserMealServiceTest {
}
