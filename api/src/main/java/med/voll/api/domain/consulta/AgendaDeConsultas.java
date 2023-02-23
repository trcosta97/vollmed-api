package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaReppository consultaReppository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    public void agendar(DadosAgendamentoConsulta dados) {
        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Paciente nao existe");
        }

        if(dados.idMedico()!= null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Médico nao existe");
        }

        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var medico = escolherMedico(dados);
        var consulta = new Consulta(null, medico, paciente, dados.data(), null);
        consultaReppository.save(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade obrigatória caso médico não seja especificado");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public void cancelar(DadosCancelamento dados) {
        if(!consultaReppository.existsById(dados.idConsulta())){
            throw new ValidacaoException("Consulta não existe");
        }

        var consulta = consultaReppository.findById(dados.idConsulta()).get();
        consulta.cancelar(dados.motivo());

    }
}


