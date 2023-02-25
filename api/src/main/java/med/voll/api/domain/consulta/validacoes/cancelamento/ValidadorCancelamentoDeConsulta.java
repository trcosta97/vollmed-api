package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosCancelamento;

public interface ValidadorCancelamentoDeConsulta {

    void validar(DadosCancelamento dados);
}
