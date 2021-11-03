package br.com.renildosousa.controleponto.controller;

import br.com.renildosousa.controleponto.dto.MomentoBatidaDto;
import br.com.renildosousa.controleponto.form.MomentoBatidaForm;
import br.com.renildosousa.controleponto.model.MomentoBatida;
import br.com.renildosousa.controleponto.repository.MomentoBatidaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Api(tags = "Bater ponto")
@RestController
@RequestMapping("/batidas")
public class MomentoBatidaController {

    @Autowired
    private MomentoBatidaRepository momentoBatidaRepository;

    @GetMapping
    public Page<MomentoBatidaDto> lista(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable paginacao) {
        Page<MomentoBatida> pontos = momentoBatidaRepository.findAll(paginacao);
        return MomentoBatidaDto.conversor(pontos);
    }

    @ApiOperation(value = "Registrar um horário da jornada diária de trabalho")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<MomentoBatidaDto> baterPonto(@RequestBody @Valid MomentoBatidaForm momentoBatidaForm, UriComponentsBuilder uriComponentsBuilder) {
        MomentoBatida momentoBatida = momentoBatidaForm.converter(momentoBatidaRepository);
        momentoBatidaRepository.save(momentoBatida);

        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(momentoBatida.getId()).toUri();
        return ResponseEntity.created(uri).body(new MomentoBatidaDto(momentoBatida));
    }
}
