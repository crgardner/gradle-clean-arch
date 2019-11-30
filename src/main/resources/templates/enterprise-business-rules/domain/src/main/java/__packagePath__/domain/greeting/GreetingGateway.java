package @packageName@.domain.greeting;

import java.util.Optional;

public interface GreetingGateway {
    Optional<Greeting> findGreetingValueById(Long id);

    Greeting save(Greeting greeting);
}
