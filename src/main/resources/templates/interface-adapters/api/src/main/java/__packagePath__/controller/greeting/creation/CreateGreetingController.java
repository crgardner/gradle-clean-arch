package @packageName@.controller.greeting.creation;


import @packageName@.controller.greeting.shared.GreetingResource;
import @packageName@.usecase.greeting.creation.*;
import @packageName@.webmvc.response.ResponseEntityResponseWriter;
import org.springframework.web.bind.annotation.*;

@RestController
public class CreateGreetingController {
    private final CreateGreetingUseCase useCase;

    public CreateGreetingController(CreateGreetingUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping(value = "/greetings", consumes = "application/json", produces = "application/json")
    public Object createGreeting(@RequestBody GreetingResource greetingResource) {
        var responseWriter = new ResponseEntityResponseWriter();
        var presenter = new CreateGreetingPresenter(responseWriter);

        useCase.execute(new CreateGreetingRequest(greetingResource.getGreetingText(), greetingResource.getOriginator()), presenter);

        return responseWriter.getResponseEntity().getBody();
    }
}
