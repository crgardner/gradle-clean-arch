package @packageName@.controller;

import @packageName@.usecase.greeting.GreetingResponse;
import @packageName@.usecase.greeting.ProvideGreetingConsumer;
import @packageName@.usecase.greeting.ProvideGreetingUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doAnswer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProvideGreetingController.class)
class ProvideGreetingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProvideGreetingUseCase useCase;

    @Test
    @DisplayName("provides greeting")
    void providesGreeting() throws Exception {
        doAnswer(byCallingPresenter()).when(useCase).handle(any());

        mockMvc.perform(get("/greetings/default/"))
               .andExpect(status().isOk())
               .andExpect(content().string("Hello, World!!"));
    }

    private Answer<?> byCallingPresenter() {
        return invocation -> {
            var responder = invocation.getArgument(0, ProvideGreetingConsumer.class);
            responder.accept(new GreetingResponse("Hello, World!!"));
            return null;
        };
    }
}
