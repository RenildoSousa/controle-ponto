package br.com.renildosousa.controleponto.dto;

import br.com.renildosousa.controleponto.model.MomentoBatida;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public class MomentoBatidaDto {
    private Long id;
    private LocalDateTime dataHora;

    public MomentoBatidaDto(MomentoBatida momentoBatida) {
        this.id = momentoBatida.getId();
        this.dataHora = momentoBatida.getDataHora();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public static Page<MomentoBatidaDto> conversor(Page<MomentoBatida> pontos){
        return pontos.map(MomentoBatidaDto::new);
    }
}
