package projeto.faculdade.reservas_system.application.user.exception;

public class EmailAlreadyUsedException extends RuntimeException{

    private static final String message = "Email already registered";
    public EmailAlreadyUsedException() {
        super(message);
    }
}
