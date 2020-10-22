package com.marcuschiu.example.customservletcontextinitializer;

import com.marcuschiu.example.customservletcontextinitializer.servlet.CustomServletContextInitializer;
import org.springframework.boot.SpringApplication;

public class Application {
    public static void main(String[] args) {
        SpringApplication.run(CustomServletContextInitializer.class, args);
//        SpringApplication.run(new Class[] { CustomServletContextInitializer.class }, args);
    }
}
