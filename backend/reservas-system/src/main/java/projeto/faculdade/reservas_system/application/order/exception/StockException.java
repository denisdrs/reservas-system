package projeto.faculdade.reservas_system.application.order.exception;

public class StockException extends RuntimeException{
    private static final String message = "Product don't have enough stock";
    public StockException() {
        super(message);
    }
}
