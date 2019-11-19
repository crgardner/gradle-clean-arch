package @packageName@.application.greeting.creation;

import @packageName@.domain.greeting.Greeting;
import @packageName@.usecase.greeting.creation.CreateGreetingResponse;
import @packageName@.usecase.greeting.creation.CreateGreetingResponder;
import @packageName@.usecase.greeting.creation.CreateGreetingUseCase;

import java.util.Optional;

public class CreateGreetingInteractor implements CreateGreetingUseCase {
    private final GreetingGateway greetingGateway;

    public ProvideGreetingInteractor(GreetingGateway greetingGateway) {
        this.greetingGateway = greetingGateway;
    }

    @Override
    public void handle(ProvideGreetingConsumer provideGreetingConsumer) {
        var greetingResponse = createResponseFrom(defaultGreeting());

        provideGreetingConsumer.accept(greetingResponse);
    }

    private Optional<Greeting> defaultGreeting() {
        return greetingGateway.findGreetingValueById(1L);
    }

    private GreetingResponse createResponseFrom(Optional<Greeting> possibleGreeting) {
        return possibleGreeting.map(Greeting::text)
                               .map(GreetingResponse::new)
                               .orElseGet(() -> new GreetingResponse("No soup for you"));
    }

}
