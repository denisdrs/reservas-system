package projeto.faculdade.reservas_system.application.order.exception;

public class OrderNotFoundException extends RuntimeException{

    private static final String message = "Order not found";

    public OrderNotFoundException() {
        super(message);
    }
}
