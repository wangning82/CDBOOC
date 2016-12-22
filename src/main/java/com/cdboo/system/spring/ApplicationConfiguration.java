package com.cdboo.system.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "com.cdboo.business")
@EnableJpaRepositories(basePackages = { "com.cdboo.business.repository" })
@EnableWebMvc
public class ApplicationConfiguration {
}
