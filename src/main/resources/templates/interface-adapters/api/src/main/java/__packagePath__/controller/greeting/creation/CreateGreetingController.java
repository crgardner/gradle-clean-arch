package @packageName@.controller.greeting.creation;


import @packageName@.usecase.greeting.creation.CreateGreetingRequest;
import @packageName@.usecase.greeting.creation.CreateGreetingUseCase;
import @packageName@.webmvc.response.ResponseEntityResponseWriter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateGreetingController {
    private final CreateGreetingUseCase useCase;

    public CreateGreetingController(CreateGreetingUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping(value = "/greetings", produces = "application/json")
    public ResponseEntity<?> createGreeting(@RequestBody String greetingText) {
        var responseWriter = new ResponseEntityResponseWriter();
        var presenter = new CreateGreetingPresenter(responseWriter);

        useCase.execute(new CreateGreetingRequest(greetingText, "Chris"), presenter);

        return responseWriter.getResponseEntity();
    }
}
