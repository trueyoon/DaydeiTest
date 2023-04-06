package com.sparta.daydeibackrepo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing
@EnableScheduling
//@OpenAPIDefinition(servers = {@Server(url = "https://sparta-daln.shop", description = "Default Server URL") ,@Server(url = "http://localhost:8080", description = "Local Server URL")})
@EnableAsync
@SpringBootApplication
public class DaydeiBackRepoApplication {

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.properties";
//            + "/app/config/springboot-webservice/real-application.properties";

    public static void main(String[] args) {
//        SpringApplication.run(DaydeiBackRepoApplication.class, args);
        new SpringApplicationBuilder(DaydeiBackRepoApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }

}

