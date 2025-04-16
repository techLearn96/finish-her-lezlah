package com.finishherlezlah;


import com.finishherlezlah.config.LezlahRateLimitProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@EnableConfigurationProperties(LezlahRateLimitProperties.class)
@SpringBootApplication
public class CamelSpringBootRun {

	public static void main(String[] args) {
		SpringApplication.run(CamelSpringBootRun.class, args);
	}

	    // Direct Roast that does not log or track : Urls: http://localhost:8080/api/v1/roast?category=FOREHEAD&index=1
	    // Direct Roast by Category and ID : http://localhost:8080/api/v1/roast/FOREHEAD/2

}
