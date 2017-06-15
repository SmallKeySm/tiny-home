package com.wzj;

import com.wzj.config.JpaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Import({
        JpaConfig.class
})
@ComponentScan(basePackages = {
        "com.wzj.controller"
})
@SpringBootApplication
public class TinyHomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TinyHomeApplication.class, args);
    }
}
