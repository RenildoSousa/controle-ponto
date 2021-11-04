package br.com.renildosousa.controleponto;

import br.com.renildosousa.controleponto.model.MomentoBatida;
import br.com.renildosousa.controleponto.repository.MomentoBatidaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
class MomentoBatidaRepositoryTest {

    @Autowired
    private MomentoBatidaRepository batidaRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    void buscarBatidaByDateTime() {
        em.persist(new MomentoBatida(LocalDateTime.parse("2021-11-25T21:29:41.401224")));
        Optional<MomentoBatida> momentoBatida = batidaRepository.findOneByDataHora(LocalDateTime.parse("2021-11-25T21:29:41.401224"));
        Assertions.assertNotNull(momentoBatida);
        Assertions.assertEquals(LocalDateTime.parse("2021-11-25T21:29:41.401224"), momentoBatida.get().getDataHora());
    }

    @Test
    void buscarBatidasByDate() {
        em.persist(new MomentoBatida(LocalDateTime.parse("2021-11-25T21:29:41.401224")));
        em.persist(new MomentoBatida(LocalDateTime.parse("2021-11-25T22:29:41.401224")));
        List<MomentoBatida> momentoBatidas = batidaRepository.buscarByData(LocalDateTime.parse("2021-11-25T21:29:41.401224").toLocalDate());
        Assertions.assertNotNull(momentoBatidas);
        Assertions.assertNotEquals(0, momentoBatidas.size());
    }
}
