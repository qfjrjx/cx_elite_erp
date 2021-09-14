package com.erp.personnel.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

        //addResourceHandler是指定的虚拟路径，addResourceLocations是自己的物理路径，
        registry.addResourceHandler("/file/**").addResourceLocations("file:D:/pictureUpload/upload");

        super.addResourceHandlers(registry);

    }
}
