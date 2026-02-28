package projeto.faculdade.reservas_system.infra.exception;

import java.time.LocalDateTime;

record DefaultExceptionResponse(LocalDateTime timestamp,
                                Integer status,
                                String error){}