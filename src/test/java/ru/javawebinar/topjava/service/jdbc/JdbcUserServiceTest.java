package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceTest;

/**
 * Created by Игорь on 03.07.2016.
 */
@ActiveProfiles(Profiles.JDBC)
public class JdbcUserServiceTest extends UserServiceTest {
}
