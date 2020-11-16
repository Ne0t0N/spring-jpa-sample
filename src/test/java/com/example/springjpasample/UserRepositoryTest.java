package com.example.springjpasample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testUserRepository() {
        // 1. Create a user
        User userBob = new User("Bob");
        userRepository.save(userBob);

        // 2. Check that that user exists
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        assertEquals(users.size(), 1);
        assertEquals(1L, users.get(0).getId());
        assertEquals("Bob", users.get(0).getName());

        // 3. Delete that user
        userRepository.deleteById(1L);

        // 4. Check that that user does not exists anymore
        users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        assertTrue(users.isEmpty());
    }
}
