package br.com.renildosousa.controleponto;

import br.com.renildosousa.controleponto.controller.MomentoBatidaController;
import br.com.renildosousa.controleponto.execption.FimSemanaException;
import br.com.renildosousa.controleponto.form.MomentoBatidaForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;

@SpringBootTest
@ActiveProfiles("test")
class MomentoBatidaControllerTest {

    @Autowired
    private MomentoBatidaController batidaController;

    @Test
    void registrarPontoSucesso() {
        MomentoBatidaForm momentoBatidaForm = new MomentoBatidaForm();
        momentoBatidaForm.setDataHora(LocalDateTime.parse("2021-11-26T21:29:41.401224"));
        batidaController.baterPonto(momentoBatidaForm, UriComponentsBuilder.newInstance());
        Assertions.assertThrows(RuntimeException.class, () -> batidaController.baterPonto(momentoBatidaForm, UriComponentsBuilder.newInstance()));
    }

    @Test
    void registrarPontoFalhaFimSemana() {
        MomentoBatidaForm momentoBatidaForm = new MomentoBatidaForm();
        momentoBatidaForm.setDataHora(LocalDateTime.parse("2021-11-27T21:29:41.401224"));
        Assertions.assertThrows(FimSemanaException.class, () -> batidaController.baterPonto(momentoBatidaForm, UriComponentsBuilder.newInstance()));
    }
}
