package org.notification.module;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"org.notification.module"})
public class AlertMain {

    public static void main(String[] args) {
        SpringApplication.run(AlertMain.class, args);
    }

}
