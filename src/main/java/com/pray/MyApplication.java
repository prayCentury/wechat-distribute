package com.pray;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Package Name: com.application
 * Created by Liu xi on 2018/2/1.
 * Version: V1.0
 * Des:
 */
@SpringBootApplication
public class MyApplication {

    public static void main(String []args){
        SpringApplication.run(MyApplication.class,args);
    }
}
