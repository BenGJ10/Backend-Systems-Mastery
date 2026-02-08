package com.bengregory.volumedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        System.out.println("Hello Ben!");
        SpringApplication.run(App.class, args);
        System.out.println("Your SpringBoot app is up and running...");
    }
}
