package @packageName@.configuration;

import @packageName@.application.greeting.creation.CreateGreetingInteractor;
import @packageName@.application.greeting.creation.CreateGreetingUseCaseFactory;
import @packageName@.application.greeting.retrieval.RetrieveGreetingInteractor;
import @packageName@.persistence.greeting.SpringDataGreetingGateway;
import @packageName@.usecase.concept.UseCase;
import @packageName@.usecase.concept.UseCaseFactory;
import @packageName@.usecase.greeting.creation.CreateGreetingRequest;
import @packageName@.usecase.greeting.creation.CreateGreetingResponder;
import @packageName@.usecase.greeting.creation.CreateGreetingUseCase;
import @packageName@.usecase.greeting.retrieval.RetrieveGreetingUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.function.*;

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

    @Bean
    public UseCaseFactory<CreateGreetingRequest, CreateGreetingResponder> createGreetingUseCaseFactory(
            SpringDataGreetingGateway springDataGreetingGateway, UnaryOperator<UseCase> transactionalDecorator) {
        return new CreateGreetingUseCaseFactory(springDataGreetingGateway, transactionalDecorator);
    }

    @Bean
    public Function<UseCase, UseCase> transactionalDecorator() {
        return this::transactionalUseCase;
    }

    @Bean
    @Scope("prototype")
    public UseCase transactionalUseCase(UseCase useCase) {
        return new TransactionalUseCase(useCase);
    }

}
