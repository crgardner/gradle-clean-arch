package @packageName@.configuration;

import @packageName@.application.greeting.creation.CreateGreetingUseCaseFactory;
import @packageName@.controller.exception.GlobalExceptionHandler;
import @packageName@.controller.greeting.creation.CommandCreateGreetingController;
import @packageName@.controller.greeting.creation.CreateGreetingController;
import @packageName@.controller.greeting.retrieval.RetrieveGreetingController;
import @packageName@.usecase.greeting.creation.CreateGreetingUseCase;
import @packageName@.usecase.greeting.retrieval.RetrieveGreetingUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfiguration {
    @Bean
    public RetrieveGreetingController provideGreetingController(RetrieveGreetingUseCase provideGreetingUseCase) {
        return new RetrieveGreetingController(provideGreetingUseCase);
    }

    @Bean
    public CreateGreetingController createGreetingController(CreateGreetingUseCase createGreetingUseCase) {
        return new CreateGreetingController(createGreetingUseCase);
    }

    @Bean
    public CommandCreateGreetingController commandCreateGreetingController(
            CreateGreetingUseCaseFactory createGreetingUseCaseFactory) {

        return new CommandCreateGreetingController(createGreetingUseCaseFactory);
    }

    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }
}
