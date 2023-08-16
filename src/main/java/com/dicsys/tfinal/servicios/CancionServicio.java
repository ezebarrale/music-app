package com.dicsys.tfinal.servicios;

import java.util.List;

import com.dicsys.tfinal.dto.CancionDto;

public interface CancionServicio {

	public CancionDto guardarCancion(Integer id);
	
	public List<CancionDto> listaCanciones();
	
	public CancionDto CancionPorId(Integer id);
	
	public List<CancionDto> listaFavoritos();
	
	public CancionDto obtenerCancion(Integer id);
	
	public CancionDto actualizarCancion(CancionDto cancionDto, Integer id);
	
	public void eliminarCancion(Integer id);
}
