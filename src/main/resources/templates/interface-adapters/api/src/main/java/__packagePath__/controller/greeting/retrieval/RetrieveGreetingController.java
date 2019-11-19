package @packageName@.controller.greeting.retrieval;

import @packageName@.usecase.greeting.retrieval.RetrieveGreetingRequest;
import @packageName@.usecase.greeting.retrieval.RetrieveGreetingUseCase;
import @packageName@.webmvc.response.ResponseEntityResponseWriter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RetrieveGreetingController {
    private final RetrieveGreetingUseCase useCase;

    public RetrieveGreetingController(RetrieveGreetingUseCase useCase)  {
        this.useCase = useCase;
    }

    @GetMapping("/greetings/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        var responseWriter = new ResponseEntityResponseWriter();
        var presenter = new RetrieveGreetingPresenter(responseWriter);

        useCase.execute(new RetrieveGreetingRequest(id), presenter);

        return responseWriter.getResponseEntity();
    }

}
