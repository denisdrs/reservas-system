package projeto.faculdade.reservas_system.application.user.usecase.contract;

public record RegisterUserInput(String name, String email, String password, AddressInput address) {

    public record AddressInput(String street, Long number, String city, String state) {

    }
}
