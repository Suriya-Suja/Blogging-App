package com.myproject.bloggingapp.users;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {
    @Autowired
    private UsersRepository usersRepository;

    @Test
    @Order(1)
    void can_create_user() {
        var user =  UserEntity.builder()
                .username("suriya")
                .email("suriya@gmail.com")
                .build();

        usersRepository.save(user);
    }

    @Test
    @Order(2)
    void can_find_user() {
        var user =  UserEntity.builder()
                .username("suriya")
                .email("suriya@gmail.com")
                .build();

        usersRepository.save(user);
        var users = usersRepository.findAll();
        Assertions.assertThat(users).hasSize(1);
    }


}
