package @packageName@.start;

import @packageName@.configuration.ApplicationConfiguration;
import @packageName@.configuration.ControllerConfiguration;
import @packageName@.configuration.PersistenceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration(proxyBeanMethods = false)
@EnableAutoConfiguration
@Import({ ApplicationConfiguration.class, ControllerConfiguration.class, PersistenceConfiguration.class})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
