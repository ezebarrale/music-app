package com.dicsys.tfinal.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dicsys.tfinal.modelos.Rol;

public interface RolRepositorio extends JpaRepository<Rol, Long>{

	public Optional<Rol> findByName(String name);
	
}
