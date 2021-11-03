package br.com.renildosousa.controleponto.execption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class FimSemanaException extends RuntimeException {
    private final LocalDateTime dataHora;
    private final String mensagem;

    public FimSemanaException(LocalDateTime dataHora) {
        this.dataHora = dataHora;
        this.mensagem = "Sábado e domingo não são permitidos como dia de trabalho";
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getMensagem() {
        return mensagem;
    }
}
