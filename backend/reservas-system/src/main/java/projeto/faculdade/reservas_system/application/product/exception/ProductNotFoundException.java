package projeto.faculdade.reservas_system.application.product.exception;

public class ProductNotFoundException extends RuntimeException{
    private static final String message = "Product with id not found";
    public ProductNotFoundException() {
        super(message);
    }
}
