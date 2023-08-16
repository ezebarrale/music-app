package com.dicsys.tfinal.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dicsys.tfinal.modelos.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{
	
	public Optional<Usuario> findByUsername(String username);
	
	public Optional<Usuario> findByEmail(String email);
	
	public Optional<Usuario> findByUsernameOrEmail(String username, String email);
	
	public Boolean existsByUsername(String username);
	
	public Boolean existsByemail(String email);

}
