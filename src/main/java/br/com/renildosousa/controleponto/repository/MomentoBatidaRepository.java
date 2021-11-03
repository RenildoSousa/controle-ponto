package br.com.renildosousa.controleponto.repository;

import br.com.renildosousa.controleponto.model.MomentoBatida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MomentoBatidaRepository extends JpaRepository<MomentoBatida, Long> {

    Optional<MomentoBatida> findOneByDataHora(LocalDateTime dataHora);

    @Query("SELECT mb FROM MomentoBatida mb WHERE cast(mb.dataHora as date) = cast(:data as date) ORDER BY mb.dataHora ASC")
    List<MomentoBatida> buscarByData(@Param("data") LocalDate dataHora);
}
