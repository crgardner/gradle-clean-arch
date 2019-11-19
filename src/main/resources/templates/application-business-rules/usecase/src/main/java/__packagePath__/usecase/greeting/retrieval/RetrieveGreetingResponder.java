package @packageName@.usecase.greeting.retrieval;

public interface RetrieveGreetingResponder {
    void accept(RetrieveGreetingResponse retrieveGreetingResponse);

    void onNotFound();
}
