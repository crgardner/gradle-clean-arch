package @packageName@.controller.greeting.creation;

import @packageName@.controller.greeting.shared.GreetingResource;
import @packageName@.usecase.concept.*;
import @packageName@.usecase.greeting.creation.*;
import @packageName@.webmvc.response.ResponseEntityResponseWriter;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommandCreateGreetingController {
    private final UseCaseFactory<CreateGreetingRequest, CreateGreetingResponder> useCaseFactory;

    public CommandCreateGreetingController(UseCaseFactory<CreateGreetingRequest, CreateGreetingResponder> useCaseFactory) {
        this.useCaseFactory = useCaseFactory;
    }

    @PostMapping(value = "/commands/greetings", consumes = "application/json", produces = "application/json")
    public Object createGreeting(@RequestBody GreetingResource greetingResource) {
        var responseWriter = new ResponseEntityResponseWriter();
        var useCase = createUseCase(greetingResource, responseWriter);

        useCase.execute();

        return responseWriter.getResponseEntity().getBody();
    }

    private UseCase createUseCase(GreetingResource greetingResource, ResponseEntityResponseWriter responseWriter) {
        return useCaseFactory.create(requestFrom(greetingResource), presenterFor(responseWriter));
    }

    private CreateGreetingPresenter presenterFor(ResponseEntityResponseWriter responseWriter) {
        return new CreateGreetingPresenter(responseWriter);
    }

    private CreateGreetingRequest requestFrom(GreetingResource greetingResource) {
        return new CreateGreetingRequest(greetingResource.getGreetingText(), greetingResource.getOriginator());
    }
}
