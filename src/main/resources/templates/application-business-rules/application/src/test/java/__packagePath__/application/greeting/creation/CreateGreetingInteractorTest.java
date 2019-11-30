package @packageName@.application.greeting.creation;

import @packageName@.domain.greeting.*;
import @packageName@.usecase.greeting.creation.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


@DisplayName("Create Greeting Interactor")
@ExtendWith(MockitoExtension.class)
class CreateGreetingInteractorTest implements CreateGreetingResponder {
    @Mock
    private GreetingGateway greetingGateway;

    @Test
    @DisplayName("creates greeting")
    void createsGreeting() {
        var greeting = new Greeting("A second greeting",  new Person("Bobby"));
        when(greetingGateway.save(greeting)).thenReturn(new Greeting(2, "A second greeting",  new Person("Bobby")));

        var useCase = new CreateGreetingInteractor(greetingGateway);
        useCase.execute(new CreateGreetingRequest("A second greeting", "Bobby"), this);
    }

    @Override
    public void accept(CreateGreetingResponse createGreetingResponse) {
        assertThat(createGreetingResponse).isEqualToComparingFieldByField(new CreateGreetingResponse(2L, "A second greeting", "Bobby"));
    }
}
