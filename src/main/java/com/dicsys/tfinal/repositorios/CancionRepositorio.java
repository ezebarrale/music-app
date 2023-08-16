package com.dicsys.tfinal.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dicsys.tfinal.modelos.Cancion;

public interface CancionRepositorio extends JpaRepository<Cancion, Integer>{

}
