package br.com.renildosousa.controleponto.execption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.CONFLICT)
public class HorarioRegistradoException extends RuntimeException {
    private final LocalDateTime dataHora;
    private final String mensagem;

    public HorarioRegistradoException(LocalDateTime dataHora) {
        this.dataHora = dataHora;
        this.mensagem = "Horário já registrado";
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getMensagem() {
        return mensagem;
    }
}
