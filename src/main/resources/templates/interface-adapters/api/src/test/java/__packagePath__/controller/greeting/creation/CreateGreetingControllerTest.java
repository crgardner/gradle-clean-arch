package @packageName@.controller.greeting.creation;

import @packageName@.controller.greeting.shared.GreetingResource;
import @packageName@.usecase.greeting.creation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CreateGreetingController.class)
@DisplayName("Create Greeting Controller")
class CreateGreetingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateGreetingUseCase useCase;

    private String greetingResourceJson;

    @BeforeEach
    void init() throws Exception {
        var objectMapper = new ObjectMapper();
        var greetingResource = new GreetingResource(0L, "hello from client", "Mr. Boston");
        greetingResourceJson = objectMapper.writeValueAsString(greetingResource);
    }

    @Test
    @DisplayName("creates new greeting")
    void createsNewGreeting() throws Exception {
        doAnswer(byCallingResponder()).when(useCase).execute(any(), any());

        mockMvc.perform(post("/greetings/")
                        .contentType("application/json")
                        .content(greetingResourceJson))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\": 2, \"greetingText\": \"hello from client\"}"));
    }

    private Answer<?> byCallingResponder() {
        return invocation -> {
            var responder = invocation.getArgument(1, CreateGreetingResponder.class);
            var greetingText = invocation.getArgument(0, CreateGreetingRequest.class).greetingText();
            responder.accept(new CreateGreetingResponse(2L, greetingText, "Mr. Boston"));
            return null;
        };
    }
}
