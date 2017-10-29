package com.medicalsystem.repository;

import com.medicalsystem.model.User;
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
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private long id;

    @Test
    public void test_autowire() {
        assertNotNull(userRepository);
    }

    @Before
    public void setup() {
        User user = new User();
        user.setUsername("mikolaj");
        user = userRepository.save(user);
        id = user.getId();
    }

    @After
    public void tearDown() {
        userRepository.delete(id);
    }

    @Test
    public void test_existsByUsername() {
        assertTrue(userRepository.existsByUsername("mikolaj"));
    }

    @Test
    public void test_findByUsername() {
        User user = userRepository.findByUsername("mikolaj");
        assertEquals(id, user.getId());
    }
}
