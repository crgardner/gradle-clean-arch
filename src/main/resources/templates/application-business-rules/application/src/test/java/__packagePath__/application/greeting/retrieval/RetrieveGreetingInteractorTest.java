package @packageName@.application.greeting.retrieval;

import @packageName@.domain.greeting.*;
import @packageName@.usecase.greeting.retrieval.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Provide Greeting Interactor")
@ExtendWith(MockitoExtension.class)
class RetrieveGreetingInteractorTest implements RetrieveGreetingResponder {
    @Mock
    private GreetingGateway greetingGateway;

    @Test
    @DisplayName("provides greeting")
    void providesGreetingFromGreetingCatalog() {
        when(greetingGateway.findGreetingValueById(1L))
                            .thenReturn(Optional.of(new Greeting(1L, "Hello world.", new Person("Mr. Boston"))));
        var useCase = new RetrieveGreetingInteractor(greetingGateway);
        useCase.execute(new RetrieveGreetingRequest(1L), this);
    }

    @Override
    public void accept(RetrieveGreetingResponse retrieveGreetingResponse) {
        assertThat(retrieveGreetingResponse.greetingText()).isEqualTo("Hello world.");
    }

    @Override
    public void onNotFound() {
    }
}
