package com.medicalsystem.repository;

import com.medicalsystem.model.ApplicationUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationUserRepositoryTest {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    private long id;

    @Test
    public void test_autowire() {
        assertNotNull(applicationUserRepository);
    }

    @Before
    public void setup() {
        ApplicationUser user = new ApplicationUser();
        user.setUsername("mikolaj");
        user = applicationUserRepository.save(user);
        id = user.getId();
    }

    @After
    public void tearDown() {
        applicationUserRepository.delete(id);
    }

    @Test
    public void test_existsByUsername() {
        assertTrue(applicationUserRepository.existsByUsername("mikolaj"));
    }

    @Test
    public void test_findByUsername() {
        ApplicationUser user = applicationUserRepository.findByUsername("mikolaj");
        assertEquals(id, user.getId());
    }
}
