package @packageName@.configuration;

import @packageName@.controller.ProvideGreetingController;
import @packageName@.usecase.greeting.ProvideGreetingUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfiguration {
    @Bean
    ProvideGreetingController provideGreetingController(ProvideGreetingUseCase provideGreetingUseCase) {
        return new ProvideGreetingController(provideGreetingUseCase);
    }
}
