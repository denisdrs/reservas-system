package projeto.faculdade.reservas_system.application.user.exception;

public class UserNotFoundException extends RuntimeException{

    private static final String message = "User not found";
    public UserNotFoundException() {
        super(message);
    }

}
