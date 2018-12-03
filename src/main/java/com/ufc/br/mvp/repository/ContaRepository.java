package com.ufc.br.mvp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufc.br.mvp.bean.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer>{

}
