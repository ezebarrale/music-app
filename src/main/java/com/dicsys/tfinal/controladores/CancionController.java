package com.dicsys.tfinal.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dicsys.tfinal.dto.CancionDto;
import com.dicsys.tfinal.servicios.CancionServicio;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/apiV1/canciones")
public class CancionController {
	
	@Autowired
	private CancionServicio cancionServicio;
	
	@GetMapping
	public ResponseEntity<List<CancionDto>> listaCanciones(){
		
		return new ResponseEntity<>(cancionServicio.listaCanciones(), HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/{id}" )
	public ResponseEntity<CancionDto> cancionPorId(@PathVariable(name = "id") Integer id){
		
		return new ResponseEntity<>(cancionServicio.CancionPorId(id), HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/favoritos")
	public ResponseEntity<List<CancionDto>> listaFavoritos(){
		
		return new ResponseEntity<>(cancionServicio.listaFavoritos(), HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<CancionDto> guardarCancion(@PathParam("id") Integer id){
		
		return new ResponseEntity<>(cancionServicio.guardarCancion(id), HttpStatus.CREATED);
		
	}
	
	@PutMapping(value = "/{id}" )
	public ResponseEntity<CancionDto> actualizarCancion(@RequestBody CancionDto cancionDto, @PathVariable(name = "id") Integer id){
		
		return new ResponseEntity<>(cancionServicio.actualizarCancion(cancionDto, id), HttpStatus.OK);
		
	}
	
	@DeleteMapping(value = "/{id}" )
	public ResponseEntity<String> eliminarCancion(@PathVariable(name = "id") Integer id){
		
		cancionServicio.eliminarCancion(id);
		
		return new ResponseEntity<>("Cancion favorita eliminada correctamente", HttpStatus.OK);
		
	}

}
