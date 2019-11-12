package @packageName@.application.greeting;

import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.*;

import @packageName@.domain.greeting.Greeting;
import @packageName@.usecase.greeting.GreetingResponse;
import @packageName@.usecase.greeting.ProvideGreetingConsumer;
import @packageName@.usecase.greeting.ProvideGreetingUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@DisplayName("Provide Greeting Interactor")
@ExtendWith(MockitoExtension.class)
class ProvideGreetingInteractorTest implements ProvideGreetingConsumer {
    @Mock
    private GreetingGateway greetingGateway;

    @Test
    @DisplayName("Provides greeting")
    void providesGreetingFromGreetingCatalog() {
        when(greetingGateway.findGreetingValueById(1L))
                            .thenReturn(Optional.of(new Greeting(1L, "Hello world.", "Mr. Boston")));
        ProvideGreetingUseCase useCase = new ProvideGreetingInteractor(greetingGateway);

        useCase.handle(this);
    }

    @Override
    public void accept(GreetingResponse greetingResponse) {
        assertThat(greetingResponse.value()).isEqualTo("Hello world.");
    }
}
