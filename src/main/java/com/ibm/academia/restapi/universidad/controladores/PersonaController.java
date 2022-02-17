package com.ibm.academia.restapi.universidad.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.servicios.PersonaDAO;

public class PersonaController 
{
	@Autowired
	@Qualifier("personaDAOImpl")
	private PersonaDAO personaDao;
	
	@PostMapping("/persona")
	public ResponseEntity<?> crearPersona(@RequestBody Persona persona)
	{
		Persona personaGuardada = personaDao.guardar(persona);
		return new ResponseEntity<Persona>(personaGuardada, HttpStatus.CREATED);	
	}
	
	@GetMapping("/personas/lista")
	public ResponseEntity<?> obtenerTodos()
	{
		List<Persona> personas = (List<Persona>) personaDao.buscarTodos();
		
		if(personas.isEmpty())
			throw new NotFoundException("No existen personas");
		return new ResponseEntity<List<Persona>>(personas,HttpStatus.OK);
	}
	
	@GetMapping("/persona/{personaId}")
	public ResponseEntity<?> obtnerAlumnoPorId(@PathVariable Long personaId)
	{
		Optional<Persona> oPersona = personaDao.buscarPorId(personaId);
		
		if(!oPersona.isPresent())
			throw new NotFoundException(String.format("Persona con id %d no existe", personaId));
		return new ResponseEntity<Persona>(oPersona.get(),HttpStatus.OK);
	}
	
	@DeleteMapping("/persona/eliminar/personaId/{personaId}")
	public ResponseEntity<?>eliminarPersona(@PathVariable Long personaId)
	{
		Optional<Persona> oPersona = personaDao.buscarPorId(personaId);
		
		if(!oPersona.isPresent())
			throw new NotFoundException("La persona con ID %d no existen", personaId);
			personaDao.eliminarPorId(oPersona.get().getId());
		return new ResponseEntity<String>("Persona ID: " + personaId + " se elimino satisfactoriamente", HttpStatus.NO_CONTENT);
	}
	
}
