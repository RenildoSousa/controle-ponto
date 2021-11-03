package br.com.renildosousa.controleponto.execption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class LimiteBatidasException extends RuntimeException {
    private final LocalDateTime dataHora;
    private final String mensagem;

    public LimiteBatidasException(LocalDateTime dataHora) {
        this.dataHora = dataHora;
        this.mensagem = "Apenas 4 horários podem ser registrados por dia";
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getMensagem() {
        return mensagem;
    }
}
