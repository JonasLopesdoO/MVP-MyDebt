package com.ufc.br.mvp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufc.br.mvp.bean.Role;
import com.ufc.br.mvp.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository repository;
	
	public Role find(String role) {
		return repository.getOne(role);
	}

}
