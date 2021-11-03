package br.com.renildosousa.controleponto.execption;

import java.time.LocalDateTime;

public class HorarioAlmocoException extends RuntimeException {
    private final LocalDateTime dataHora;
    private final String mensagem;

    public HorarioAlmocoException(LocalDateTime dataHora) {
        this.dataHora = dataHora;
        this.mensagem = "Deve haver no mínimo 1 hora de almoço";
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getMensagem() {
        return mensagem;
    }
}
