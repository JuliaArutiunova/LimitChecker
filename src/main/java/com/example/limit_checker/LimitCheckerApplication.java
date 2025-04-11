package com.example.limit_checker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@EnableFeignClients
@EnableScheduling
public class LimitCheckerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LimitCheckerApplication.class, args);
	}

}
