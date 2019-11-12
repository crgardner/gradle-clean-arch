package @packageName@.configuration;

import @packageName@.application.greeting.ProvideGreetingInteractor;
import @packageName@.persistence.greeting.SpringDataGreetingGateway;
import @packageName@.usecase.greeting.ProvideGreetingUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public ProvideGreetingUseCase provideGreetingUseCase(SpringDataGreetingGateway springDataGreetingGateway) {
        return new ProvideGreetingInteractor(springDataGreetingGateway);
    }
}
