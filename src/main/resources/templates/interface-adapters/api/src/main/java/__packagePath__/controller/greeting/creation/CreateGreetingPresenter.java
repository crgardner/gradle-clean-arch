package @packageName@.controller.greeting.creation;

import @packageName@.controller.greeting.shared.GreetingResource;
import @packageName@.controller.response.ResponseWriter;
import @packageName@.usecase.greeting.creation.CreateGreetingResponder;
import @packageName@.usecase.greeting.creation.CreateGreetingResponse;

class CreateGreetingPresenter implements CreateGreetingResponder {
    private final ResponseWriter responseWriter;

    CreateGreetingPresenter(ResponseWriter responseWriter) {
        this.responseWriter = responseWriter;
    }

    @Override
    public void accept(CreateGreetingResponse createGreetingResponse) {
        var greetingResource = new GreetingResource(createGreetingResponse.id(), createGreetingResponse.greetingText(),
                                                    createGreetingResponse.originator());
        responseWriter.ok(greetingResource);
    }
}
