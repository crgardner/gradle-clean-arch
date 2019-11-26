package @packageName@.application.greeting.creation;

import @packageName@.domain.greeting.GreetingGateway;
import @packageName@.usecase.concept.UseCase;
import @packageName@.usecase.concept.UseCaseFactory;
import @packageName@.usecase.greeting.creation.CreateGreetingRequest;
import @packageName@.usecase.greeting.creation.CreateGreetingResponder;

import java.util.function.Function;

public class CreateGreetingUseCaseFactory implements UseCaseFactory<CreateGreetingRequest, CreateGreetingResponder> {

    private final GreetingGateway greetingGateway;
    private final Function<UseCase, UseCase> transactionDecorator;

    public CreateGreetingUseCaseFactory(GreetingGateway greetingGateway, Function<UseCase, UseCase> transactionDecorator) {
        this.greetingGateway = greetingGateway;
        this.transactionDecorator = transactionDecorator;
    }

    @Override
    public UseCase create(CreateGreetingRequest request, CreateGreetingResponder responder) {
        return transactionDecorator.apply(new CommandCreateGreetingInteractor(greetingGateway, request, responder));
    }
}
