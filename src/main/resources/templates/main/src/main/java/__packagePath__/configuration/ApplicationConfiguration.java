package @packageName@.configuration;

import @packageName@.application.greeting.creation.CreateGreetingInteractor;
import @packageName@.application.greeting.retrieval.RetrieveGreetingInteractor;
import @packageName@.persistence.greeting.SpringDataGreetingGateway;
import @packageName@.usecase.greeting.creation.CreateGreetingUseCase;
import @packageName@.usecase.greeting.retrieval.RetrieveGreetingUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public RetrieveGreetingUseCase provideGreetingUseCase(SpringDataGreetingGateway springDataGreetingGateway) {
        return new RetrieveGreetingInteractor(springDataGreetingGateway);
    }

    @Bean
    public CreateGreetingUseCase createGreetingUseCase(SpringDataGreetingGateway springDataGreetingGateway) {
        return new CreateGreetingInteractor(springDataGreetingGateway);
    }
}
