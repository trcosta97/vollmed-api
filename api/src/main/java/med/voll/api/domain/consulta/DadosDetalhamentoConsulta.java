package med.voll.api.domain.consulta;

import java.util.Date;

public record DadosDetalhamentoConsulta(Long id, Long idMedico, Long idPaciente, Date data) {
}
