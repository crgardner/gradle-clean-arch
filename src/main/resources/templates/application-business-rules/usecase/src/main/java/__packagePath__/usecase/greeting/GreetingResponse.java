package @packageName@.usecase.greeting;

public class GreetingResponse {
    private String value;

    public GreetingResponse(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}
