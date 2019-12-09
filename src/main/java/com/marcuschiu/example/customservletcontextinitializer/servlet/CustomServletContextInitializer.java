package com.marcuschiu.example.customservletcontextinitializer.servlet;

import com.marcuschiu.example.customservletcontextinitializer.config.CustomWebMvcConfigurer;
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
        // create sub WebServlet
        AnnotationConfigWebApplicationContext webServlet = new AnnotationConfigWebApplicationContext();
        webServlet.register(CustomWebMvcConfigurer.class);
        webServlet.setServletContext(rootServletContext);

        // add WebServlet to RootServlet
        ServletRegistration.Dynamic servlet = rootServletContext.addServlet("dispatcherExample", new DispatcherServlet(webServlet));

        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }
}