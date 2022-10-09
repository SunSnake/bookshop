package com.gorgeous.bookshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author haohaoxiansheng
 * @date 2022/10/08 15:33
 */
@EnableSwagger2
@SpringBootApplication
@MapperScan("com.gorgeous.bookshop.mapper")
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
