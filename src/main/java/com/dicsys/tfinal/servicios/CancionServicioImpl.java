package com.dicsys.tfinal.servicios;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dicsys.tfinal.dto.CancionDto;
import com.dicsys.tfinal.excepciones.ResourceNotFoundException;
import com.dicsys.tfinal.modelos.Cancion;
import com.dicsys.tfinal.repositorios.CancionRepositorio;

@Service
public class CancionServicioImpl implements CancionServicio{
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CancionRepositorio cancionRepositorio;
	
	private final String URL_BASE = "https://60d67598307c300017a5f3e4.mockapi.io/api/";
	
	private Cancion mapearDto(CancionDto cancionDto) {
		
		Cancion cancion = new Cancion();
		
		cancion.setTitulo(cancionDto.getTitulo());
		cancion.setArtista(cancionDto.getArtista());
		cancion.setAlbum(cancionDto.getAlbum());
		cancion.setGenero(cancionDto.getGenero());
		
		return cancion;
	}
	
	private CancionDto mapearEntidad(Cancion cancion) {
		
		CancionDto cancionRespuesta = new CancionDto();
		
		cancionRespuesta.setId(cancion.getId());
		cancionRespuesta.setTitulo(cancion.getTitulo());
		cancionRespuesta.setArtista(cancion.getArtista());
		cancionRespuesta.setAlbum(cancion.getAlbum());
		cancionRespuesta.setGenero(cancion.getGenero());
		
		return cancionRespuesta;
	}
	
	private CancionDto buscarCancion(Integer id) {
		
		CancionDto cancion = restTemplate.getForObject(URL_BASE+"cancion/"+id, CancionDto.class );
		
		return cancion;
	}
	
	@Override
	public CancionDto guardarCancion(Integer id) {
		
		CancionDto cancionRespuesta = buscarCancion(id);
		
		Cancion cancion = mapearDto(cancionRespuesta);
		
		Cancion nuevaCancion = cancionRepositorio.save(cancion);
		
		CancionDto cancionDto = mapearEntidad(nuevaCancion);
		
		return cancionDto;
	}

	@Override
	public List<CancionDto> listaCanciones() {
		
		
		CancionDto[] canciones = restTemplate.getForObject(URL_BASE+"cancion", CancionDto[].class );
		
		return Arrays.asList(canciones);
	}

	@Override
	public CancionDto CancionPorId(Integer id) {
		
		return buscarCancion(id);
	}

	@Override
	public List<CancionDto> listaFavoritos() {
		
		List<Cancion> cancionesRespuesta = cancionRepositorio.findAll();
		
		//cancionesRespuesta.stream().forEach(c -> cancionesFavoritas.add(mapearEntidad(c)));
		
		return cancionesRespuesta.stream().map(c -> mapearEntidad(c)).toList();
		
	}

	@Override
	public CancionDto obtenerCancion(Integer id) {
		
		Cancion cancion = cancionRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("cancion", "id", id));
		return mapearEntidad(cancion);
	}
	
	@Override
	public CancionDto actualizarCancion(CancionDto cancionDto, Integer id) {
		
		Cancion cancion = cancionRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("cancion", "id", id));
		
		cancion.setTitulo(cancionDto.getTitulo());
		cancion.setAlbum(cancionDto.getAlbum());
		cancion.setArtista(cancionDto.getArtista());
		cancion.setGenero(cancionDto.getGenero());
		
		Cancion nuevaCancion = cancionRepositorio.save(cancion);
		
		return mapearEntidad(nuevaCancion);
	}

	@Override
	public void eliminarCancion(Integer id) {
		
		Cancion cancion = cancionRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("cancion", "id", id));
		
		cancionRepositorio.delete(cancion);
		
	}

}
