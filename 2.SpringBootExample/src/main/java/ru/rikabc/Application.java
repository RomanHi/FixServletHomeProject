package ru.rikabc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @Author Roman Khayrullin on 18.04.2018
 * @Version 1.0
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "ru.rikabc.repositories")
@EntityScan(value = "ru.rikabc.models")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
