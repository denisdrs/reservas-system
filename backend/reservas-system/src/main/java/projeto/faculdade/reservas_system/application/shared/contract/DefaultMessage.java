package projeto.faculdade.reservas_system.application.shared.contract;

import lombok.Builder;

@Builder
public record DefaultMessage<T>(String message, T data) {
}
