package @packageName@.usecase.greeting.retrieval;

public class RetrieveGreetingResponse {
    private String greetingText;

    public RetrieveGreetingResponse(String greetingText) {
        this.greetingText = greetingText;
    }

    public String greetingText() {
        return greetingText;
    }

}
