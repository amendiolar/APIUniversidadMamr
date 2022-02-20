package com.ibm.academia.restapi.universidad.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;
import com.ibm.academia.restapi.universidad.servicios.AulaDAO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/restapi")
public class AulaController 
{
	@Autowired
	private AulaDAO aulaDao;
	
	/**
	 * Endpoint para consultar todas las aulas
	 * @return Retorna una lista de aulas.
	 * @author AMR - 19-02-2022
	 */
	@ApiOperation(value = "Consultar todas las aulas")
	@ApiResponse({
		@ApiResponse(code =  200, message = "Endpoint ejecutado satisfactoriamente"),
		@ApiResponse(code = 404, message = "No hay elementos en la base de datos")
	})
	@GetMapping("/aulas/lista")
	public List<Aula> listarTodas()
	{
		List<Aula> aulas = (List<Aula>) aulaDao.buscarTodos();
		return aulas;
	}
	
	/**
	 * Endpoint para consultar una aula por id
	 * @param aulaId Parámetro de búsqueda de la aula
	 * @return Retorna un objeto de tipo aula
	 * @NotFoundException En caso de que falle buscando la aula
	 * @author AMR - 19-02-2022
	 */
	@GetMapping("/aula/aulaId/{aulaId}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long aulaId)
	{
		Optional<Aula> oAula = aulaDao.buscarPorId(aulaId);
		
		if(!oAula.isPresent())
			throw new NotFoundException(String.format("El aula con id: %d no existe", aulaId));
		
		return new ResponseEntity<Aula>(oAula.get(), HttpStatus.OK);	
	}
	
	@PostMapping("/aula")
	public ResponseEntity<?> guardar(@Valid @RequestBody Aula aula, BindingResult result)
	{
		Map<String, Object> validaciones = new HashMap<String, Object>();
		if(result.hasErrors())
		{
			List<String> listaErrores = result.getFieldErrors()
					.stream()
					.map(errores -> "Campo: '" + errores.getField() + "' " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista Errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
		}
		
		Aula aulaGuardada = aulaDao.guardar(aula);
		return new ResponseEntity<Aula>(aulaGuardada, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/aula/eliminar/aulaId/{aulaId}")
	public ResponseEntity<?> eliminar(@PathVariable Long aulaId)
	{
		Optional<Aula> oAula = aulaDao.buscarPorId(aulaId);
		
		if(!oAula.isPresent())
			throw new NotFoundException(String.format("El aula con id: %d no existe", aulaId));
		
		aulaDao.eliminarPorId(aulaId);
		return new ResponseEntity<>("El aula con id: " + aulaId + " fue eliminada", HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/aula/actualizar/aulaId/{aulaId}")
	public ResponseEntity<?> actualizar(@PathVariable Long aulaId, @Valid @RequestBody Aula aula, BindingResult result)
	{
		Map<String, Object> validaciones = new HashMap<String, Object>();
		if(result.hasErrors())
		{
			List<String> listaErrores = result.getFieldErrors()
					.stream()
					.map(errores -> "Campo: '" + errores.getField() + "' " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista Errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
		}
		
		Aula aulaActualizada = null;
		
		try
		{
			aulaActualizada = aulaDao.actualizar(aulaId, aula);
		}
		catch (Exception e) 
		{
			logger.info(e.getMessage());
			throw e;
		}
		
		return new ResponseEntity<Aula>(aulaActualizada, HttpStatus.OK);
	}
	
	@PutMapping("/aula/asociar-pizarron")
	public ResponseEntity<?> asignarPizarronAula(@RequestParam Long pizarronId, @RequestParam(name = "aula_id") Long aulaId)
	{
		Aula = ((AulaDAO)aulaDao).asociarPizarronAula(aulaId, pizarronId); 
		return new ResponseEntity<Aula>(aula, HttpStatus.OK);
	}

	@GetMapping("/aula/aula- pizarron/{tipoPizarron}")
	public ResponseEntity<?> buscarPorTipoPizarron(@PathVariable String tipoPizarron)
	{
		Optional<Aula> oAula = aulaDao.buscarPorTipoPizarron(tipoPizarron);
		
		if(!oAula.isPresent())
			throw new NotFoundException(String.format("El aula con el tipo pizarron: %s no existe", tipoPizarron));
		
		return new ResponseEntity<Aula>(oAula.get(), HttpStatus.OK);	
	}
	
	@PutMapping("/aula/asociar-pabellon")
	public ResponseEntity<?> asignarPabellonAula(@RequestParam Long pabellonId, @RequestParam(name = "aula_id") Long aulaId)
	{
		Aula = ((AulaDAO)aulaDao).asociarPabellonAula(aulaId, pabellonId); 
		return new ResponseEntity<Aula>(aula, HttpStatus.OK);
	}

	@GetMapping("/aula/aula- pabellon/{nombrePabellon}")
	public ResponseEntity<?> buscarPorNombrePabellon(@PathVariable String nombrePabellon)
	{
		Optional<Aula> oAula = aulaDao.buscarPorNombrePabellon(nombrePabellon);
		
		if(!oAula.isPresent())
			throw new NotFoundException(String.format("El aula en el pabellon: %s no existe", nombrePabellon));
		
		return new ResponseEntity<Aula>(oAula.get(), HttpStatus.OK);	
	}
	
	@GetMapping("/aula/numeroAula/{numeroAula}")
	public ResponseEntity<?> buscarPorNumeroAula(@PathVariable Integer numeroAula)
	{
		Optional<Aula> oAula = aulaDao.buscarPorNumeroAula(aulaId);
		
		if(!oAula.isPresent())
			throw new NotFoundException(String.format("El aula con id: %d no existe", aulaId));
		
		return new ResponseEntity<Aula>(oAula.get(), HttpStatus.OK);	
	}
}
