package @packageName@.controller.greeting.creation;

import @packageName@.usecase.concept.UseCase;
import @packageName@.usecase.concept.UseCaseFactory;
import @packageName@.usecase.greeting.creation.CreateGreetingRequest;
import @packageName@.usecase.greeting.creation.CreateGreetingResponder;
import @packageName@.webmvc.response.ResponseEntityResponseWriter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandCreateGreetingController {
    private final UseCaseFactory<CreateGreetingRequest, CreateGreetingResponder> useCaseFactory;

    public CommandCreateGreetingController(UseCaseFactory<CreateGreetingRequest, CreateGreetingResponder> useCaseFactory) {
        this.useCaseFactory = useCaseFactory;
    }

    @PostMapping(value = "/commands/greetings", produces = "application/json")
    public ResponseEntity<?> createGreeting(@RequestBody String greetingText) {
        var responseWriter = new ResponseEntityResponseWriter();
        var useCase = createUseCase(greetingText, responseWriter);

        useCase.execute();

        return responseWriter.getResponseEntity();
    }

    private UseCase createUseCase(String greetingText, ResponseEntityResponseWriter responseWriter) {
        return useCaseFactory.create(requestFrom(greetingText), presenterFor(responseWriter));
    }

    private CreateGreetingPresenter presenterFor(ResponseEntityResponseWriter responseWriter) {
        return new CreateGreetingPresenter(responseWriter);
    }

    private CreateGreetingRequest requestFrom(String greetingText) {
        return new CreateGreetingRequest(greetingText, "Chris");
    }
}
