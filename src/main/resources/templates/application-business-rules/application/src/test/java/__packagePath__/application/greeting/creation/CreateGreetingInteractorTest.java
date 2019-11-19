package @packageName@.application.greeting.creation;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import @packageName@.domain.greeting.Greeting;
import @packageName@.domain.greeting.GreetingGateway;
import @packageName@.usecase.greeting.creation.CreateGreetingRequest;
import @packageName@.usecase.greeting.creation.CreateGreetingResponder;
import @packageName@.usecase.greeting.creation.CreateGreetingResponse;
import @packageName@.usecase.greeting.creation.CreateGreetingUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@DisplayName("Create Greeting Interactor")
@ExtendWith(MockitoExtension.class)
class CreateGreetingInteractorTest implements CreateGreetingResponder {
    @Mock
    private GreetingGateway greetingGateway;

    private CreateGreetingUseCase useCase;

    @Test
    @DisplayName("Creates greeting")
    void createsGreeting() {
        var greeting = new Greeting("A second greeting", "Bobby");
        when(greetingGateway.save(greeting)).thenReturn(new Greeting(2, "A second greeting", "Bobby"));

        useCase = new CreateGreetingInteractor(greetingGateway);
        useCase.execute(new CreateGreetingRequest("A second greeting", "Bobby"), this);
    }

    @Override
    public void accept(CreateGreetingResponse createGreetingResponse) {
        assertThat(createGreetingResponse).isEqualToComparingFieldByField(new CreateGreetingResponse(2L, "A second greeting", "Bobby"));
    }
}
