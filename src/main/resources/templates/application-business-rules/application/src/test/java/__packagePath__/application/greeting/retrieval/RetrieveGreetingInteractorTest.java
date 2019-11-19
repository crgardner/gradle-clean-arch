package @packageName@.application.greeting.retrieval;

import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.*;

import @packageName@.domain.greeting.Greeting;
import @packageName@.domain.greeting.GreetingGateway;
import @packageName@.usecase.greeting.retrieval.RetrieveGreetingRequest;
import @packageName@.usecase.greeting.retrieval.RetrieveGreetingResponse;
import @packageName@.usecase.greeting.retrieval.RetrieveGreetingResponder;
import @packageName@.usecase.greeting.retrieval.RetrieveGreetingUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@DisplayName("Provide Greeting Interactor")
@ExtendWith(MockitoExtension.class)
class RetrieveGreetingInteractorTest implements RetrieveGreetingResponder {
    @Mock
    private GreetingGateway greetingGateway;

    private RetrieveGreetingUseCase useCase;

    @Test
    @DisplayName("Provides greeting")
    void providesGreetingFromGreetingCatalog() {
        when(greetingGateway.findGreetingValueById(1L))
                            .thenReturn(Optional.of(new Greeting(1L, "Hello world.", "Mr. Boston")));
        useCase = new RetrieveGreetingInteractor(greetingGateway);

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
