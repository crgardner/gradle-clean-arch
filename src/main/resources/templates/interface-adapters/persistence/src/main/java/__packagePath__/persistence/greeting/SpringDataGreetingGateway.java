package @packageName@.persistence.greeting;

import @packageName@.application.greeting.GreetingGateway;
import @packageName@.domain.greeting.Greeting;

import java.util.Optional;

public class SpringDataGreetingGateway implements GreetingGateway {
    private final GreetingRepository greetingRepository;

    public SpringDataGreetingGateway(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @Override
    public Optional<Greeting> findGreetingValueById(Long id) {
        return greetingRepository.findById(id);
    }
}
