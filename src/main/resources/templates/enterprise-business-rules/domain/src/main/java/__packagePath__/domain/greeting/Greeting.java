package @packageName@.domain.greeting;

public class Greeting {
    private long id;
    private String text;
    private String originator;

    public Greeting(long id, String text, String originator) {
        this.id = id;
        this.text = text;
        this.originator = originator;
    }

    Greeting() {
    }

    public long id() {
        return id;
    }

    public String text() {
        return text;
    }

    public String originator() {
        return originator;
    }
}
