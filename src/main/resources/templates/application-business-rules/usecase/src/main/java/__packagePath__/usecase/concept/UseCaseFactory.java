package @packageName@.usecase.concept;

public interface UseCaseFactory<I, O> {
    UseCase create(I request, O responder);
}
