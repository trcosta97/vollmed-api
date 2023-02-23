package med.voll.api.domain.consulta;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamento(
        @NotNull
        Long idConsulta,

        @NotNull
        Motivo motivo
    ) {
}
