package @packageName@.domain.greeting;

import org.apache.commons.lang3.Validate;

import java.util.Objects;

public class Person {
    private String name;

    public Person(String name) {
        Validate.isTrue(name != null && name.length() <= 10, "Name is too long");
        this.name = name;
    }

    private Person() {
        // default
    }

    public String name() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
