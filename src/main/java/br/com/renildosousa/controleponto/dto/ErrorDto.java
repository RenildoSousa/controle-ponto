package br.com.renildosousa.controleponto.dto;

import org.springframework.http.HttpStatus;

public class ErrorDto {
    private String value;
    private String mensagem;
    HttpStatus httpStatus;

    public ErrorDto(String value, String mensagem) {
        this.value = value;
        this.mensagem = mensagem;
    }

    public String getValue() {
        return value;
    }

    public String getMensagem() {
        return mensagem;
    }
}
