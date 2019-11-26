package @packageName@.configuration;

import @packageName@.usecase.concept.UseCase;

class TransactionalUseCase implements UseCase {
    private final UseCase useCase;

    TransactionalUseCase(UseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    @javax.transaction.Transactional
    public void execute() {
        useCase.execute();
    }
}
