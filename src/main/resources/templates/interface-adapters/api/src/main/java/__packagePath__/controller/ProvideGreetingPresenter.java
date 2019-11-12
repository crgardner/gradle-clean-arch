package @packageName@.controller;

import @packageName@.usecase.greeting.GreetingResponse;
import @packageName@.usecase.greeting.ProvideGreetingConsumer;

public class ProvideGreetingPresenter implements ProvideGreetingConsumer {
    private String responseValue;

    @Override
    public void accept(GreetingResponse greetingResponse) {
        responseValue = greetingResponse.value();
    }

    public String getResponseValue() {
        return responseValue;
    }
}
