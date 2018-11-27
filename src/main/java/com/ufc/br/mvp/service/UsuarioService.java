package com.ufc.br.mvp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufc.br.mvp.bean.Usuario;
import com.ufc.br.mvp.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public Usuario find(int id) {
		return repository.getOne(id);
	}
}