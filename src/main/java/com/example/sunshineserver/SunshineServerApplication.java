package com.example.sunshineserver;

import com.example.sunshineserver.auth.domain.GoogleProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties({GoogleProperties.class})
public class SunshineServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SunshineServerApplication.class, args);
    }

}
