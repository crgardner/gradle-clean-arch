package @packageName@.domain.greeting;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.*;

@DisplayName("Person")
class PersonTest {

    @Test
    @DisplayName("forbids creation of Person with name exceeding maximum length")
    void forbidsCreationOfPersonWithNameExceedingMaximumLength() {
        assertThatThrownBy(() -> new Person("Hubert Blaine Wolfeschlegelsteinhausenbergerdorff"))
                                                    .isInstanceOf(IllegalArgumentException.class)
                                                    .withFailMessage("Name is too long");
    }

    @Test
    @DisplayName("allows creation of Person with name less than or equal to maximum length")
    void allowsCreationOfPersonWithNameLessThanOrEqualToMaximumLength() {
        assertThatCode(() -> new Person("Hubert"))
                            .doesNotThrowAnyException();
    }

}
