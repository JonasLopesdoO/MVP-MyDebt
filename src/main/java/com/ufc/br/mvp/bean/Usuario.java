package com.ufc.br.mvp.bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
@Entity
public class Usuario implements UserDetails{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="usuario_id")
	private Long id;
	@NotNull @NotEmpty
	private String nome;
	private LocalDate nascimento;
	@NotNull @NotEmpty
	private String email;
	@NotNull @NotEmpty
	private String senha;
	
	@ManyToMany
	@JoinTable(name="usuarios_roles", joinColumns =  
			@JoinColumn( name = "usuario_id", referencedColumnName = "usuario_id"),
			inverseJoinColumns = @JoinColumn (name = "role_id", referencedColumnName = "role"))
	private List<Role> roles;
	
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
	private List<Conta> contas;
	
	public Usuario() {
		this.contas = new ArrayList<>();
		this.roles = new ArrayList<>();
	}
	
	public Usuario(String nome, LocalDate nascimento, 
					String email, String senha) {
		this.nome = nome;
		this.nascimento = nascimento;
		this.email = email;
		this.senha = senha;
		this.contas = new ArrayList<>();
		this.roles = new ArrayList<>();
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
	
	public LocalDate getNascimento() {
		return nascimento;
	}
	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
