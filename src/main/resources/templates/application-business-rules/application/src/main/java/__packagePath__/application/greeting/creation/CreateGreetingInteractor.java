package @packageName@.application.greeting.creation;

import @packageName@.domain.greeting.Greeting;
import @packageName@.domain.greeting.GreetingGateway;
import @packageName@.usecase.greeting.creation.CreateGreetingRequest;
import @packageName@.usecase.greeting.creation.CreateGreetingResponder;
import @packageName@.usecase.greeting.creation.CreateGreetingResponse;
import @packageName@.usecase.greeting.creation.CreateGreetingUseCase;

public class CreateGreetingInteractor implements CreateGreetingUseCase {
    private final GreetingGateway greetingGateway;
    private int count;

    public CreateGreetingInteractor(GreetingGateway greetingGateway) {
        this.greetingGateway = greetingGateway;
    }

    @Override
    @javax.transaction.Transactional
    public void execute(CreateGreetingRequest request, CreateGreetingResponder responder) {
        Greeting greeting = createGreetingFrom(request);
        Greeting savedGreeting = saveGreeting(greeting);

        responder.accept(createGreetingResponseFrom(savedGreeting));
    }

    private Greeting createGreetingFrom(CreateGreetingRequest createGreetingRequest) {
        return new Greeting(createGreetingRequest.greetingText(), createGreetingRequest.originator());
    }

    private Greeting saveGreeting(Greeting greeting) {
        return greetingGateway.save(greeting);
    }

    private CreateGreetingResponse createGreetingResponseFrom(Greeting savedGreeting) {
        return new CreateGreetingResponse(savedGreeting.id(), savedGreeting.text(), savedGreeting.originator());
    }
}
