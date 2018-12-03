package com.ufc.br.mvp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufc.br.mvp.bean.Recebedor;
import com.ufc.br.mvp.repository.RecebedorRepository;

@Service
public class RecebedorService {
	
	@Autowired
	private RecebedorRepository repository;
	
	public Recebedor save(Recebedor recebedor) {
		return repository.save(recebedor);
	}
	
	public void delete(int id) {
		repository.deleteById(id);
	}
	
	public Recebedor find(int id) {
		return repository.getOne(id);
	}
	
	public List<Recebedor> findAll(){
		return repository.findAll();
	}
	
}
