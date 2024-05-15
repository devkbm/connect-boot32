package com.like.core.jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.like.core.audit.SpringSecurityAuditorAwareTest;
import com.like.core.jpa.domain.AuditorDetails;

@Configuration
@EnableJpaAuditing
@Profile("localtest")
public class JpaRepositoryAuditConfigTest {

	@Bean
	public AuditorAware<AuditorDetails> auditorProvider() {
	    return new SpringSecurityAuditorAwareTest(); // AuditorAware 의 구현체 객체 생성	    
	}
}
