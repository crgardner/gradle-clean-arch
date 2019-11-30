package @packageName@.controller.greeting.shared;

@SuppressWarnings("unused")
public class GreetingResource {
    private Long id;
    private String greetingText;
    private String originator;

    public GreetingResource(Long id, String greetingText, String originator) {
        this.id = id;
        this.greetingText = greetingText;
        this.originator = originator;
    }

    public Long getId() {
        return id;
    }

    public GreetingResource() {
        // default
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGreetingText() {
        return greetingText;
    }

    public void setGreetingText(String greetingText) {
        this.greetingText = greetingText;
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }
}
