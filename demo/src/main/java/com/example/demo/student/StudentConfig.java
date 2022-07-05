package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repo){
        return args -> {
            Student a = new Student(
                    "Asd",
                    LocalDate.of(2000, Month.JANUARY, 4),
                    "asd.sfd"
            );
            Student v = new Student(
                    "v",
                    LocalDate.of(2000, Month.JANUARY, 4),
                    "iu.sfd"
            );

            repo.saveAll(List.of(a, v));
        };
    }
}
