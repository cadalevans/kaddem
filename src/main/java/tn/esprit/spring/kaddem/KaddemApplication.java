package tn.esprit.spring.kaddem;

import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class KaddemApplication {

    @Test
    public static void main(String[] args) {
        SpringApplication.run(KaddemApplication.class, args);
    }

}
