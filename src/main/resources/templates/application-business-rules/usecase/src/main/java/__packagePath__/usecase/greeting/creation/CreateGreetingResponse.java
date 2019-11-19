package @packageName@.usecase.greeting.creation;

public class CreateGreetingResponse {
    private final Long id;
    private final String greetingText;
    private final String originator;

    public CreateGreetingResponse(Long id, String greetingText, String originator) {
        this.id = id;
        this.greetingText = greetingText;
        this.originator = originator;
    }

    public Long id() {
        return id;
    }

    public String greetingText() {
        return greetingText;
    }

    public String originator() {
        return originator;
    }
}
