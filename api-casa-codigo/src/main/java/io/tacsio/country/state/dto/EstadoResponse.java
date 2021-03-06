package io.tacsio.country.state.dto;

import io.tacsio.country.dto.PaisResponse;
import io.tacsio.country.state.Estado;
import lombok.Getter;

@Getter
public class EstadoResponse {

	private Long id;

	private String nome;

	private PaisResponse pais;

	public EstadoResponse(Estado estado) {
		this.id = estado.getId();
		this.nome = estado.getNome();
		this.pais = new PaisResponse(estado.getPais());
	}

	@Override
	public String toString() {
		return "EstadoResponse [nome=" + nome + ", país=" + pais + "]";
	}

}
