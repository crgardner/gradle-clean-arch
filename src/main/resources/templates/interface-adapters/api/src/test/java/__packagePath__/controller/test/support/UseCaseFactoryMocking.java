package @packageName@.controller.test.support;

import @packageName@.usecase.concept.UseCase;
import org.mockito.stubbing.Answer;

import java.util.function.BiConsumer;

@SuppressWarnings("unchecked")
public final class UseCaseFactoryMocking {
    public static <T, U> Answer<?> byPreparing(UseCase useCase, BiConsumer<T, U> toPerformAction) {
        return invocation -> {
            var request  = (T) invocation.getArgument(0);
            var responder = (U) invocation.getArgument(1);

            toPerformAction.accept(request, responder);
            return useCase;
        };
    }
}
