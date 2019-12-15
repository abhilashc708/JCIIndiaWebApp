package com.jci.india.web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.jci.india.web.app.dto.FileStorageProperties;


@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties({
    FileStorageProperties.class
})

public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
