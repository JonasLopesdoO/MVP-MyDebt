package com.ufc.br.mvp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufc.br.mvp.bean.Conta;
import com.ufc.br.mvp.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository repository;
	
	public Conta save(Conta conta) {
		return repository.save(conta);
	}
	
	public void deleteConta(int id) {	
		repository.deleteById(id);
	}
	
	public Conta find(int id) {
		return repository.getOne(id);
	}

	public List<Conta> findAll() {
		return repository.findAll();
	}
}
