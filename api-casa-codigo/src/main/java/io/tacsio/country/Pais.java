package io.tacsio.country;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.tacsio.country.state.Estado;
import lombok.Getter;

@Entity
@Getter
public class Pais extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String nome;

	@OneToMany(mappedBy = "pais")
	private List<Estado> estados = new ArrayList<>();

	protected Pais() {
	}

	public Pais(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "País [nome=" + nome + "]";
	}

}