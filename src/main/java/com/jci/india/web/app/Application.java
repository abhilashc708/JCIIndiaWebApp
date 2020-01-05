package com.jci.india.web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.jci.india.web.app.dto.FileStorageProperties;

@EnableAutoConfiguration
@ServletComponentScan
@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties({
	FileStorageProperties.class
})

/*intitial Base Application Starter*/
/*public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}*/
/*end Base Application Starter*/
/*War generation Base Application Starter*/
public class Application extends SpringBootServletInitializer{
	
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}
	/*War generation end Base Application Starter*/

}
