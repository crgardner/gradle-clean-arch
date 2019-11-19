package @packageName@.application.greeting.retrieval;

import @packageName@.domain.greeting.Greeting;
import @packageName@.domain.greeting.GreetingGateway;
import @packageName@.usecase.greeting.retrieval.RetrieveGreetingRequest;
import @packageName@.usecase.greeting.retrieval.RetrieveGreetingResponder;
import @packageName@.usecase.greeting.retrieval.RetrieveGreetingResponse;
import @packageName@.usecase.greeting.retrieval.RetrieveGreetingUseCase;

import java.util.Optional;

public class RetrieveGreetingInteractor implements RetrieveGreetingUseCase {
    private final GreetingGateway greetingGateway;

    public RetrieveGreetingInteractor(GreetingGateway greetingGateway) {
        this.greetingGateway = greetingGateway;
    }

    @Override
    public void execute(RetrieveGreetingRequest request, RetrieveGreetingResponder responder) {
        var greetingResponse = createResponseFrom(greetingMatching(request));

        greetingResponse.ifPresentOrElse(responder::accept, responder::onNotFound);
    }

    private Optional<Greeting> greetingMatching(RetrieveGreetingRequest request) {
        return greetingGateway.findGreetingValueById(request.id());
    }

    private Optional<RetrieveGreetingResponse> createResponseFrom(Optional<Greeting> possibleGreeting) {
        return possibleGreeting.map(Greeting::text)
                               .map(RetrieveGreetingResponse::new);
    }

}
