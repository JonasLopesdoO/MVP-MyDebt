package com.ufc.br.mvp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufc.br.mvp.bean.Recebedor;

@Repository
public interface RecebedorRepository extends JpaRepository<Recebedor, Integer>{

}
