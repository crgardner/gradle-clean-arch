package @packageName@.controller.greeting.retrieval;

import @packageName@.usecase.greeting.retrieval.RetrieveGreetingRequest;
import @packageName@.usecase.greeting.retrieval.RetrieveGreetingResponse;
import @packageName@.usecase.greeting.retrieval.RetrieveGreetingResponder;
import @packageName@.usecase.greeting.retrieval.RetrieveGreetingUseCase;
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

@WebMvcTest(controllers = RetrieveGreetingController.class)
class RetrieveGreetingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RetrieveGreetingUseCase useCase;

    @Test
    @DisplayName("provides greeting for identified greeting")
    void providesGreeting() throws Exception {
        doAnswer(byCallingPresenter()).when(useCase).execute(any(), any());

        mockMvc.perform(get("/greetings/1"))
               .andExpect(status().isOk())
               .andExpect(content().string("Hello, World!!"));
    }

    private Answer<?> byCallingPresenter() {
        return invocation -> {
            var responder = invocation.getArgument(1, RetrieveGreetingResponder.class);
            responder.accept(new RetrieveGreetingResponse("Hello, World!!"));
            return null;
        };
    }
}
