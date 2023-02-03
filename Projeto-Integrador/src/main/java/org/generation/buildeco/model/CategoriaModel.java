package org.generation.buildeco.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tb_categoria")
public class CategoriaModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo nome de Categoria é Obrigátorio!")
	@Size(min = 1, max = 100, message ="O atributo nome de Categoria deve conter no mínimo 100 e no máximo 100 caracteres")
	private String nome;
	
	@NotBlank(message = "O atributo Generico ou Original é Obrigátorio!")
	@Size(min = 1, max = 100, message ="O atributo Generico ou Original deve conter no mínimo 100 e no máximo 100 caracteres")
	private String descricao;
	
	@NotBlank (message = "O campo preco é obrigatório!")
	@Size (min = 1, max = 100, message = "O campo nome preco conter no minimo 05 caracteres" )
	public Double preco;
	
	@OneToMany (mappedBy = "nome", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("nome")
	private List<ProdutoModel> produto;
	

	public List<ProdutoModel> getProduto() {
		return produto;
	}

	public void setProduto(List<ProdutoModel> produto) {
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	} 
	
}
