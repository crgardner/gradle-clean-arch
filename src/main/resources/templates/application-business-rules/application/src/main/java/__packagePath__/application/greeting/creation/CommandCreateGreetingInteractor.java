package @packageName@.application.greeting.creation;

import @packageName@.domain.greeting.Greeting;
import @packageName@.domain.greeting.GreetingGateway;
import @packageName@.domain.greeting.Person;
import @packageName@.usecase.concept.UseCase;
import @packageName@.usecase.greeting.creation.CreateGreetingRequest;
import @packageName@.usecase.greeting.creation.CreateGreetingResponder;
import @packageName@.usecase.greeting.creation.CreateGreetingResponse;

class CommandCreateGreetingInteractor implements UseCase {
    private final GreetingGateway greetingGateway;
    private final CreateGreetingRequest request;
    private final CreateGreetingResponder responder;

    CommandCreateGreetingInteractor(GreetingGateway greetingGateway, CreateGreetingRequest request,
                                    CreateGreetingResponder responder) {
        this.greetingGateway = greetingGateway;
        this.request = request;
        this.responder = responder;
    }

    @Override
    public void execute() {
        Greeting greeting = createGreeting();
        Greeting savedGreeting = saveGreeting(greeting);

        responder.accept(createGreetingResponseFrom(savedGreeting));
    }

    private Greeting createGreeting() {
        return new Greeting(request.greetingText(), new Person(request.originator()));
    }

    private Greeting saveGreeting(Greeting greeting) {
        return greetingGateway.save(greeting);
    }

    private CreateGreetingResponse createGreetingResponseFrom(Greeting savedGreeting) {
        return new CreateGreetingResponse(savedGreeting.id(), savedGreeting.text(), savedGreeting.originator().name());
    }
}
