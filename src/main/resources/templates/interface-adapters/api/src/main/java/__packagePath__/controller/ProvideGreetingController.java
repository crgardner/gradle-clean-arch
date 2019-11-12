package @packageName@.controller;

import @packageName@.usecase.greeting.ProvideGreetingUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProvideGreetingController {
    private final ProvideGreetingUseCase useCase;

    public ProvideGreetingController(ProvideGreetingUseCase useCase)  {
        this.useCase = useCase;
    }

    @GetMapping("/greetings/default")
    public String get() {
        ProvideGreetingPresenter presenter = new ProvideGreetingPresenter();
        useCase.handle(presenter);

        return presenter.getResponseValue();
    }

}
