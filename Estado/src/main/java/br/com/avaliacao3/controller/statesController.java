package br.com.avaliacao3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.avaliacao3.controller.Dto.StateDto;
import br.com.avaliacao3.controller.form.StateForm;
import br.com.avaliacao3.modelo.State;
import br.com.avaliacao3.repository.StateRepository;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/states")
public class statesController {

	@Autowired
	private StateRepository stateRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<StateDto> cadastrarState(@RequestBody @Valid StateForm form,
			UriComponentsBuilder uriBuilder) {
		State state = form.convertir();
		if (state.getRegiao()==null) {
			return ResponseEntity.notFound().build();
		} else {
			stateRepository.save(state);
			URI uri = uriBuilder.path("/api/states/{id}").buildAndExpand(state.getId()).toUri();
			return ResponseEntity.created(uri).body(new StateDto(state));
		}
	}

	@GetMapping
	public Page<StateDto> lista(@RequestParam(required = false) String region,
			@RequestParam(required = false) String ordenacion_area_ou_populacao) {

		Pageable paginacion = PageRequest.of(0, 15);

		if (ordenacion_area_ou_populacao != null) {
			paginacion = PageRequest.of(0, 15, Direction.DESC, ordenacion_area_ou_populacao);
		}

		if (region == null) {
			Page<State> states = stateRepository.findAll(paginacion);
			return StateDto.convertir(states);
		} else {
			Page<State> states = stateRepository.findByRegiao(region.toUpperCase(), paginacion);
			return StateDto.convertir(states);

		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<StateDto> buscaId(@PathVariable Long id) {
		Optional<State> state = stateRepository.findById(id);
		if (state.isPresent()) {
			return ResponseEntity.ok(new StateDto(state.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<StateDto> actualizarState(@PathVariable Long id, @RequestBody @Valid StateForm form,
			UriComponentsBuilder uriBuilder) {

		Optional<State> verificador = stateRepository.findById(id);
		if (verificador.isPresent()) {
			try {
				State state = form.actualizar(id, stateRepository);
				return ResponseEntity.ok(new StateDto(state));
			}
			catch (Exception e) {
				return ResponseEntity.notFound().build();
			}
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> DeletarState(@PathVariable Long id) {

		Optional<State> verificador = stateRepository.findById(id);
		if (verificador.isPresent()) {
			stateRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();

	}

}

/*
 * Filtrar os estados porregião Filtrar os estados com maiorpopulação Filtrar
 * os estados com a maiorárea
 */