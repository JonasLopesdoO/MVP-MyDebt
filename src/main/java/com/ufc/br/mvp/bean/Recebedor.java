package com.ufc.br.mvp.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Recebedor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="recebedor_id")
	private int id;
	
	private String nome;
	private String descricao;
	private String endereco;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recebedor", fetch = FetchType.EAGER)
	private List<Conta> contas;
	
	public Recebedor() {}
	
	public Recebedor(String nome, String descricao, String endereco) {
		this.nome = nome;
		this.descricao = descricao;
		this.endereco = endereco;
		this.contas = new ArrayList<>();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}
}

