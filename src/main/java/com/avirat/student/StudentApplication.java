package com.avirat.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.avirat.student.studentRepo")
@EntityScan("com.avirat.student.studentEntity")
public class StudentApplication {
	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}
}
