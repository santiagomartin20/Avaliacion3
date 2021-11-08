package br.com.avaliacao3.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.avaliacao3.modelo.State;

public interface StateRepository extends JpaRepository<State, Long> {

    Page<State> findByRegiao(String nomeCurso, Pageable paginacion);
    
}
