package com.ufc.br.mvp.bean;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Conta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String descricao;
	private double valor;
	@OneToOne(mappedBy="conta", cascade = CascadeType.ALL, 
					fetch = FetchType.LAZY)
	private Boleto boleto;
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	@ManyToOne
	@JoinColumn(name = "recebedor_id")
	private Recebedor recebedor;
	private LocalDate vencimento;
	private LocalDate notificacao;
	
	public Conta() {}
	
	public Conta(String descricao, double valor, Boleto boleto, Usuario usuario,
					Recebedor recebedor, LocalDate vencimento, LocalDate notificacao) {
		this.descricao = descricao;
		this.valor = valor;
		this.boleto = boleto;
		this.usuario = usuario;
		this.recebedor = recebedor;
		this.vencimento = vencimento;
		this.notificacao = notificacao;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Boleto getBoleto() {
		return boleto;
	}
	public void setBoleto(Boleto boleto) {
		this.boleto = boleto;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Recebedor getRecebedor() {
		return recebedor;
	}
	public void setRecebedor(Recebedor recebedor) {
		this.recebedor = recebedor;
	}
	public LocalDate getVencimento() {
		return vencimento;
	}
	public void setVencimento(LocalDate vencimento) {
		this.vencimento = vencimento;
	}
	public LocalDate getNotificacao() {
		return notificacao;
	}
	public void setNotificacao(LocalDate notificacao) {
		this.notificacao = notificacao;
	}
	
}
