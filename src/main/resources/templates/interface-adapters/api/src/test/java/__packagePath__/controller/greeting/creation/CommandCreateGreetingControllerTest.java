package @packageName@.controller.greeting.creation;

import @packageName@.controller.greeting.shared.GreetingResource;
import @packageName@.usecase.concept.*;
import @packageName@.usecase.greeting.creation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.function.BiConsumer;

import static @packageName@.controller.test.support.UseCaseFactoryMocking.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CommandCreateGreetingController.class)
@DisplayName("Create Greeting Controller")
class CommandCreateGreetingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UseCaseFactory<CreateGreetingRequest, CreateGreetingResponder> useCaseFactory;

    @Mock
    private UseCase useCase;

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
        when(useCaseFactory.create(any(), any())).thenAnswer(byPreparing(useCase, toRunMainFlow()));

        mockMvc.perform(post("/commands/greetings/")
               .contentType("application/json")
               .content(greetingResourceJson))
               .andExpect(status().isOk())
               .andExpect(content().json("{\"id\": 1, \"greetingText\": \"hello from client\", \"originator\": \"Mr. Boston\"}"));
    }

    private BiConsumer<CreateGreetingRequest, CreateGreetingResponder> toRunMainFlow() {
        return (request, responder) -> {
            doAnswer(invocation -> {
                responder.accept(new CreateGreetingResponse(1L, request.greetingText(), request.originator()));
                return null;
            }).when(useCase).execute();
        };
    }

    @Test
    @DisplayName("reports server side error")
    void reportsServerSideError() throws Exception {
        when(useCaseFactory.create(any(), any())).thenAnswer(byPreparing(useCase, toThrowUnexpectedException()));

        mockMvc.perform(post("/commands/greetings/")
               .contentType("application/json")
               .content(greetingResourceJson))
               .andExpect(status().is5xxServerError())
               .andExpect(content().string("An unknown error has occurred"));
    }

    private BiConsumer<CreateGreetingRequest, CreateGreetingResponder> toThrowUnexpectedException() {
        return (request, response) -> doThrow(new RuntimeException()).when(useCase).execute();
    }
}
