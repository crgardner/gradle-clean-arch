package @packageName@.controller.greeting.retrieval;

import @packageName@.controller.exception.UnknownResourceException;
import @packageName@.controller.response.ResponseWriter;
import @packageName@.usecase.greeting.retrieval.RetrieveGreetingResponse;
import @packageName@.usecase.greeting.retrieval.RetrieveGreetingResponder;
import @packageName@.webmvc.response.ResponseEntityResponseWriter;

class RetrieveGreetingPresenter implements RetrieveGreetingResponder {
    private final ResponseWriter responseWriter;

    RetrieveGreetingPresenter(ResponseWriter responseWriter) {
        this.responseWriter = responseWriter;
    }

    @Override
    public void accept(RetrieveGreetingResponse retrieveGreetingResponse) {
        responseWriter.ok(retrieveGreetingResponse.greetingText());
    }

    @Override
    public void onNotFound() {
        throw new UnknownResourceException("Could not find greeting");
    }

}
