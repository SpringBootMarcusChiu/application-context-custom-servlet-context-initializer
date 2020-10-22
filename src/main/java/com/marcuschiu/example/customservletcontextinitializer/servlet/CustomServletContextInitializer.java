package com.marcuschiu.example.customservletcontextinitializer.servlet;

import com.marcuschiu.example.customservletcontextinitializer.configurer.CustomWebMvcConfigurer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * @EnableAutoConfiguration
 * - because ERROR - Unable to start ServletWebServerApplicationContext due to
 *   missing ServletWebServerFactory bean
 * - Enable auto-configuration of the Spring Application Context, attempting to guess
 *   and configure beans that you are likely to need
 */
@EnableAutoConfiguration
public class CustomServletContextInitializer implements ServletContextInitializer {

    public void onStartup(ServletContext rootServletContext) {
        System.out.println("Root ServletContext - server info: " + rootServletContext.getServerInfo());
        System.out.println("Root ServletContext - name: " + rootServletContext.getServletContextName());

        // create a type of WebApplicationContext
        AnnotationConfigWebApplicationContext webAppContext = new AnnotationConfigWebApplicationContext();
        webAppContext.register(CustomWebMvcConfigurer.class); // register component classes
        webAppContext.setServletContext(rootServletContext);  // set

        // add a DispatcherServlet to ServletContextInitializer
        ServletRegistration.Dynamic servlet = rootServletContext.addServlet("Dispatcher Name Here", new DispatcherServlet(webAppContext));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }
}