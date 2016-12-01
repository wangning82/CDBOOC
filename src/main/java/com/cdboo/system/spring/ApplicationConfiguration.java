package com.cdboo.system.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.cdboo.business")
@EnableJpaRepositories(basePackages = "com.cdboo.business.repository")
public class ApplicationConfiguration {
}
