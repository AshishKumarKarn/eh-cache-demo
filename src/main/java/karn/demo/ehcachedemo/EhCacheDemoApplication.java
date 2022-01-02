package karn.demo.ehcachedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableCaching// --we don't need this for current configuration
public class EhCacheDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EhCacheDemoApplication.class, args);
    }


}
