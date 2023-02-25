package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadosHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta {

    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDeAbrirClinica = dataConsulta.getHour() < 7;
        var depoisDeFecharClinica = dataConsulta.getHour() > 18;

        if(domingo || antesDeAbrirClinica || depoisDeFecharClinica){
            throw new ValidacaoException("Consulta fora do horário de funcionamento");
        }

    }

}
