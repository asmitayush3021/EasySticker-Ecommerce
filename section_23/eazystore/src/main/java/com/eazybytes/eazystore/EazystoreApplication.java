package com.eazybytes.eazystore;

import com.eazybytes.eazystore.dto.ContactInfoDto;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableCaching
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
@EnableConfigurationProperties(value = {ContactInfoDto.class})
public class EazystoreApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        // Iterate over all entries
        dotenv.entries().forEach(entry -> {
            String key = entry.getKey();
            String value = entry.getValue();

            if (value != null) {
                System.setProperty(key, value);
            }
        });
        SpringApplication.run(EazystoreApplication.class, args);
    }

}
