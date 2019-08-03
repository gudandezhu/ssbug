package com.ps.ssbug_h5_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class H5_api {

    public static void main(String[] args) {
        SpringApplication.run(H5_api.class, args);
    }

}
