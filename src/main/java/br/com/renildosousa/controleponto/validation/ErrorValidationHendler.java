package br.com.renildosousa.controleponto.validation;

import br.com.renildosousa.controleponto.dto.ErrorDto;
import br.com.renildosousa.controleponto.execption.FimSemanaException;
import br.com.renildosousa.controleponto.execption.HorarioAlmocoException;
import br.com.renildosousa.controleponto.execption.HorarioRegistradoException;
import br.com.renildosousa.controleponto.execption.LimiteBatidasException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@RestController
public class ErrorValidationHendler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorDto> hendle(MethodArgumentNotValidException exception) {
        List<ErrorDto> errorDtos = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErrorDto errorDto = new ErrorDto(e.getField(), mensagem);
            errorDtos.add(errorDto);
        });
        return errorDtos;
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(DateTimeParseException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(ex.getMessage(), "Data e hora em formato inválido"));
    }

    @ExceptionHandler(LimiteBatidasException.class)
    public ResponseEntity<?> handleLimeBatidasException(LimiteBatidasException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorDto(ex.getDataHora().toString(), ex.getMensagem()));
    }

    @ExceptionHandler(HorarioRegistradoException.class)
    public ResponseEntity<?> handleHorarioRegistradoException(HorarioRegistradoException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDto(ex.getDataHora().toString(), ex.getMensagem()));
    }

    @ExceptionHandler(HorarioAlmocoException.class)
    public ResponseEntity<?> handleHorarioAlmocoException(HorarioAlmocoException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorDto(ex.getDataHora().toString(), ex.getMensagem()));
    }

    @ExceptionHandler(FimSemanaException.class)
    public ResponseEntity<?> handleFimSemanaException(FimSemanaException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorDto(ex.getDataHora().toString(), ex.getMensagem()));
    }
}
