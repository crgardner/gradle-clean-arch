package @packageName@.controller.greeting.creation;

import @packageName@.usecase.greeting.creation.CreateGreetingRequest;
import @packageName@.usecase.greeting.creation.CreateGreetingResponder;
import @packageName@.usecase.greeting.creation.CreateGreetingResponse;
import @packageName@.usecase.greeting.creation.CreateGreetingUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doAnswer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CreateGreetingController.class)
@DisplayName("Create Greeting Controller")
class CreateGreetingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateGreetingUseCase useCase;

    @Test
    @DisplayName("creates new greeting")
    void createsNewGreeting() throws Exception {
        doAnswer(byCallingResponder()).when(useCase).execute(any(), any());

        mockMvc.perform(post("/greetings/")
                        .content("hello from client"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\": 2, \"greetingText\": \"hello from client\"}"));
    }

    private Answer<?> byCallingResponder() {
        return invocation -> {
            var responder = invocation.getArgument(1, CreateGreetingResponder.class);
            var greetingText = invocation.getArgument(0, CreateGreetingRequest.class).greetingText();
            responder.accept(new CreateGreetingResponse(2L, greetingText, "Chris"));
            return null;
        };
    }
}
