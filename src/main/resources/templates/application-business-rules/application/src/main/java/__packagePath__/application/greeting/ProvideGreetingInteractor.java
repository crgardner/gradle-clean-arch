package @packageName@.application.greeting;

import @packageName@.domain.greeting.Greeting;
import @packageName@.usecase.greeting.GreetingResponse;
import @packageName@.usecase.greeting.ProvideGreetingConsumer;
import @packageName@.usecase.greeting.ProvideGreetingUseCase;

import java.util.Optional;

public class ProvideGreetingInteractor implements ProvideGreetingUseCase {
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
