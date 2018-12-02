package com.ufc.br.mvp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufc.br.mvp.bean.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>{

}
