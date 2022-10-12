package com.gorgeous.bookshop;

import com.gorgeous.bookshop.task.AddBookTask;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author gorgeous
 * @date 2022/10/08 15:33
 */
@EnableSwagger2
@SpringBootApplication
@MapperScan("com.gorgeous.bookshop.mapper")
public class MainApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MainApplication.class, args);
        new AddBookTask(context).start();
    }

}
