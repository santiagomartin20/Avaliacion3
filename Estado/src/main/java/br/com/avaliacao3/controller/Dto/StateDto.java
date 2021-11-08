package br.com.avaliacao3.controller.Dto;


import org.springframework.data.domain.Page;

import br.com.avaliacao3.modelo.State;

public class StateDto {

	private Long id;
	private String nome;
	private String regiao;
	private Long populacao;
	private String capital;
	private Double area;

	public StateDto(State states) {
			this.id=states.getId();
			this.nome=states.getNome();
			this.regiao= states.getRegiao();
			this.populacao=states.getPopulacao();
			this.capital=states.getCapital();
			this.area=states.getArea();
		}
		
	
	public Long getId() {
		return id;
	}


	public String getNome() {
		return nome;
	}

	public String getRegiao() {
		return regiao;
	}

	public Long getPopulacao() {
		return populacao;
	}

	public String getCapital() {
		return capital;
	}

	public Double getArea() {
		return area;
	}

	public static Page<StateDto> convertir(Page<State> states) {
		return states.map(StateDto::new);

	}

}
