package com.like;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@SpringBootApplication
public class ConnectBoot32Application {

	public static void main(String[] args) {
		SpringApplication.run(ConnectBoot32Application.class, args);
	}

	@PersistenceContext
	EntityManager em;

	@Bean
	JPAQueryFactory queryFactory() {
		return new JPAQueryFactory(em);
		
	}
	
	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}
}
