package @packageName@.usecase.concept;

public interface UseCaseFactory<REQUEST, RESPONDER> {
    UseCase create(REQUEST request, RESPONDER responder);
}
