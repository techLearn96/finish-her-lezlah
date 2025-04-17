package com.finishherlezlah.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // automatically registered when the Spring Boot app starts up
public class WebConfig {

    // CORs configuration: configuration in Spring Boot determines how a server handles requests from different origins
    // (domains, protocols, or ports)
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000", "https://lezlah.netlify.app/")
                        .allowedMethods("GET") // Only allow GET requests
                        .allowedHeaders("*");
            }
        };
    } // This will make your backend only accept GET requests from your frontend, which matches the current
    // use case of Lezlah. You’re tightening up security while keeping things clean and efficient.
}
