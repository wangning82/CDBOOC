package com.cdboo.system.spring;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "com.cdboo.business")
@EnableJpaRepositories(basePackages = {"com.cdboo.business.repository"})
@EntityScan("com.cdboo.business.entity")
@EnableWebMvc
public class ApplicationConfiguration {

}
