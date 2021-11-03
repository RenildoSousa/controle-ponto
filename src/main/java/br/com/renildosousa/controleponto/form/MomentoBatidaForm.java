package br.com.renildosousa.controleponto.form;

import br.com.renildosousa.controleponto.execption.FimSemanaException;
import br.com.renildosousa.controleponto.execption.HorarioAlmocoException;
import br.com.renildosousa.controleponto.execption.HorarioRegistradoException;
import br.com.renildosousa.controleponto.execption.LimiteBatidasException;
import br.com.renildosousa.controleponto.model.MomentoBatida;
import br.com.renildosousa.controleponto.repository.MomentoBatidaRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class MomentoBatidaForm {

    @NotNull(message = "Campo obrigatório não informado")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dataHora;

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public MomentoBatida converter(MomentoBatidaRepository repository) {
        validaFimSemana(dataHora);
        validaHorarioRegistrado(dataHora, repository);
        return new MomentoBatida(dataHora);
    }

    private void validaFimSemana(LocalDateTime dataHora) {
        if (dataHora.getDayOfWeek().equals(DayOfWeek.SATURDAY) || dataHora.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            throw new FimSemanaException(dataHora);
        }
    }

    private void validaHorarioRegistrado(LocalDateTime dataHora, MomentoBatidaRepository repository) {
        if (repository.findOneByDataHora(dataHora).isPresent())
            throw new HorarioRegistradoException(dataHora);

        List<MomentoBatida> momentoBatidas = repository.buscarByData(dataHora.toLocalDate());

        if (momentoBatidas.size() == 4)
            throw new LimiteBatidasException(dataHora);

        if (momentoBatidas.size() == 2 && Duration.between(momentoBatidas.get(1).getDataHora(), dataHora).toHours() < 1)
            throw new HorarioAlmocoException(dataHora);
    }
}
