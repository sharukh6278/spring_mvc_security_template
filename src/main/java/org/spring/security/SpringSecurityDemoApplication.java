package org.spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("org.spring.security")
@EnableJpaRepositories(basePackages = "org.spring.security.repository")
@EnableAutoConfiguration
@EntityScan(basePackages = {"org.spring.security"})
public class SpringSecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityDemoApplication.class, args);
	}
}
