package br.com.gamesranking.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "tb_jogador")
public class Jogador {

	private Long id;
	private String nome;
	private Integer quantidadeVitoria = 0;
	private Integer quantidadePartida = 0;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotNull
	@Size(min = 3, max = 40)
	@Column(nullable = false)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getQuantidadeVitoria() {
		return quantidadeVitoria;
	}
	public void setQuantidadeVitoria(Integer quantidadeVitoria) {
		this.quantidadeVitoria = quantidadeVitoria;
	}
	public Integer getQuantidadePartida() {
		return quantidadePartida;
	}
	public void setQuantidadePartida(Integer quantidadePartida) {
		this.quantidadePartida = quantidadePartida;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jogador other = (Jogador) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
