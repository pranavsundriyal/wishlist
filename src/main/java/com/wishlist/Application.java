package com.wishlist;

import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class Application {

    @Value("${setting.cacheEnabled}")
    private Boolean cacheEnabled;
    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        //System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
        //    System.out.println(beanName);
        }
    }

    @Bean
    public CacheManager cacheManager(){
        if (cacheEnabled) {
            CacheManager cacheManager = CacheManager.create();
            return cacheManager;
        } else {
            return null;
        }
    }

    @Bean
    public ExecutorService rulesExecutorService(){
        return Executors.newFixedThreadPool(10);
    }

    @Bean
    public ExecutorService flexExecutorService(){
        return Executors.newFixedThreadPool(10);
    }
}
