package @packageName@.domain.greeting;

import java.util.Objects;

public class Greeting {
    private Long id;
    private String text;
    private String originator;

    public Greeting(long id, String text, String originator) {
        this(text, originator);
        this.id = id;
    }

    public Greeting(String text, String originator) {
        this.text = text;
        this.originator = originator;
    }

    Greeting() {
        // default
    }

    public Long id() {
        return id;
    }

    public String text() {
        return text;
    }

    public String originator() {
        return originator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Greeting greeting = (Greeting) o;
        return text.equals(greeting.text) &&
                originator.equals(greeting.originator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, originator);
    }
}
