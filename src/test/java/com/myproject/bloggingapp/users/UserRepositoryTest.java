package com.myproject.bloggingapp.users;

import com.myproject.bloggingapp.JpaTestConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JpaTestConfig.class)
public class UserRepositoryTest {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private DataSource dataSource;

    @Test
    @Order(1)
    void can_create_user() {

        System.out.println("--------------------------------------------------");
        System.out.println("CURRENT DATASOURCE: " + dataSource.getClass().getName());
        System.out.println("--------------------------------------------------");

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
