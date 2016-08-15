package ru.javawebinar.topjava.service;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import ru.javawebinar.topjava.repository.JpaUtil;
import ru.javawebinar.topjava.web.user.AbstractUserController;

abstract public class AbstractJpaUserServiceTest extends AbstractUserServiceTest {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private JpaUtil jpaUtil;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @BeforeClass
    public static void beforeClass() {
        ReflectionTestUtils.setField(AbstractUserController.class, "isTest", true);
    }

    @AfterClass
    public static void afterClass() {
        ReflectionTestUtils.setField(AbstractUserController.class, "isTest", false);
    }
}
