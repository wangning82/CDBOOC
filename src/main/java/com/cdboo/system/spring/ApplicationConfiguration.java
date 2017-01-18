package com.cdboo.system.spring;

import com.cdboo.business.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(basePackages = "com.cdboo.business")
@EnableJpaRepositories(basePackages = {"com.cdboo.business.repository"})
@EntityScan("com.cdboo.business.entity")
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    private PropsConfig propsConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(Constants.URL_MUSIC + "**").addResourceLocations("file:" + propsConfig.getMusic());
        registry.addResourceHandler(Constants.URL_IMAGES + "**").addResourceLocations("file:" + propsConfig.getImages());
        super.addResourceHandlers(registry);
    }
}
