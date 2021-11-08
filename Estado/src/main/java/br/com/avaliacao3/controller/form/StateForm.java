package br.com.avaliacao3.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.avaliacao3.modelo.State;
import br.com.avaliacao3.repository.StateRepository;

public class StateForm {

	@NotNull
	@NotEmpty
	@Length(min = 3)
	private String nome;
	@NotNull
	@NotEmpty
	private String regiao;
	@NotNull
	private Long populacao;
	@NotNull
	@NotEmpty
	@Length(min = 3)
	private String capital;
	@NotNull
	private Double area;

	public StateForm() {
	}

	public StateForm(String nome, String regiao, Long populacao, String capital, Double area) {
		this.nome = nome;
		this.populacao = populacao;
		this.capital = capital;
		this.area = area;
		this.regiao = regiao;

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

	public State convertir() {

		if (regiao.equalsIgnoreCase("NORTE") || regiao.equalsIgnoreCase("NORDESTE") || regiao.equalsIgnoreCase("SUL")
				|| regiao.equalsIgnoreCase("SUDESTE") || regiao.equalsIgnoreCase("CENTRO-OESTE")) {
			this.regiao = regiao.toUpperCase();
		} else {
			this.regiao = null;
		}
		return new State(nome, regiao, populacao, capital, area);
	}

	public State actualizar(Long id, StateRepository stateRepository) {
		State state = stateRepository.getOne(id);
		state.setNome(this.nome);
		state.setCapital(this.capital);
		state.setPopulacao(this.populacao);
		state.setArea(this.area);
		state.setRegiao(this.regiao);
		
		return state;
	}

}
