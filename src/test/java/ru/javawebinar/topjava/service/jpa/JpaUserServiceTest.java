package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceTest;

/**
 * Created by Игорь on 03.07.2016.
 */
@ActiveProfiles(Profiles.JPA)
public class JpaUserServiceTest extends UserServiceTest {
}