package br.estudo.tw.exam.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Objects;

/**
 * Created by torugo on 03/09/15.
 */
public class SpringApplication {

    private static final String SPRING_CONFIG_FILE =
            "/spring.xml";

    private static ApplicationContext applicationContext;

    private SpringApplication() {
    }

    public static ApplicationContext getApplicationContext() {
        if (Objects.isNull(applicationContext)) {
            applicationContext = new ClassPathXmlApplicationContext(SPRING_CONFIG_FILE);
        }
        return applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }
}
