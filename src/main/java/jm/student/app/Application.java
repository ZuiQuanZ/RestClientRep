package jm.student.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "jm.student")
@EntityScan(basePackages = "jm.student.models")

public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
