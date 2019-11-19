package @packageName@.usecase.greeting.creation;

public class CreateGreetingRequest {
    private final String greetingText;
    private final String originator;

    public CreateGreetingRequest(String greetingText, String originator) {
        this.greetingText = greetingText;
        this.originator = originator;
    }

    public String greetingText() {
        return greetingText;
    }

    public String originator() {
        return originator;
    }
}
