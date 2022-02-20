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
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/restapi")
public class PabellonController 
{
	@Autowired
	private PabellonDAO pabellonDao;
	
	/**
	 * Endpoint para consultar todos los pabellones
	 * @return Retorna una lista de pabellones.
	 * @author AMR - 14-02-2022
	 */
	@ApiOperation(value = "Consultar todos los pabellones")
	@ApiResponse({
		@ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
		@ApiResponse(code = 404, message = "No hay elementos en la base de datos")
	})
	@GetMapping("/pabellones/lista")
	public List<Pabellon> listarTodas()
	{
		List<Pabellon> pabellones = (List<Pabellon>) pabellonDao.buscarTodos();
		return pabellones;
	}
	
	/**
	 * Endpoint para consultar un pabellon por id
	 * @param pabellonId Parámetro de búsqueda del pabellon
	 * @return Retorna un objeto de tipo pabellon
	 * @NotFoundException En caso de que falle buscando el pabellon
	 * @author AMR - 14-02-2022
	 */
	@GetMapping("/pabellon/pabellonId/{pabellonId}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long pabellonId)
	{
		Optional<Pabellon> oPabellon = pabellonDao.buscarPorId(pabellonId);
		
		if(!oPabellon.isPresent())
			throw new NotFoundException(String.format("El pabellon con id: %d no existe", pabellonId));
		
		return new ResponseEntity<Pabellon>(oPabellon.get(), HttpStatus.OK);	
	}
	
	@PostMapping("/pabellon")
	public ResponseEntity<?> guardar(@Valid @RequestBody Pabellon pabellon, BindingResult result)
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
		
		Pabellon pabellonGuardado = pabellonDao.guardar(pabellon);
		return new ResponseEntity<Pabellon>(pabellonGuardado, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/pabellon/eliminar/pabellonId/{pabellonId}")
	public ResponseEntity<?> eliminar(@PathVariable Long pabellonId)
	{
		Optional<Pabellon> oPabellon = pabellonDao.buscarPorId(pabellonId);
		
		if(!oPabellon.isPresent())
			throw new NotFoundException(String.format("El pabellon con id: %d no existe", pabellonId));
		
		pabellonDao.eliminarPorId(pabellonId);
		return new ResponseEntity<>("El pabellon con id: " + pabellonId + " fue eliminado", HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/pabellon/actualizar/pabellonId/{pabellonId}")
	public ResponseEntity<?> actualizar(@PathVariable Long pabellonId, @Valid @RequestBody Pabellon pabellon, BindingResult result)
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
		
		Pabellon pabellonActualizado = null;
		
		try
		{
			pabellonActualizado = pabellonDao.actualizar(pabellonId, pabellon);
		}
		catch (Exception e) 
		{
			logger.info(e.getMessage());
			throw e;
		}
		
		return new ResponseEntity<Pabellon>(pabellonActualizado, HttpStatus.OK);
	}
	
	@GetMapping("/pabellon/codigoPostal/{codigoPostal}")
	public ResponseEntity<?> buscarPorCodigoPostal(@PathVariable String codigoPostal)
	{
		Optional<Pabellon> oPabellon = pabellonDao.buscarPorCodigoPostal(codigoPostal);
		
		if(!oPabellon.isPresent())
			throw new NotFoundException(String.format("El pabellon con codigo postal: %s no existe", codigoPostal));
		
		return new ResponseEntity<Pabellon>(oPabellon.get(), HttpStatus.OK);	
	}
	
	@GetMapping("/pabellones/contains/{nombre}")
	public List<Pabellon> findPabellonesByNombreContains(String nombre)
	{
		List<Pabellon> pabellones = (List<Pabellon>) pabellonDao.findPabellonesByNombreContains(nombre);
		if(!pabellones.contains(nombre))
			throw new NotFoundException(String.format("El pabellon  no existe"));
		return pabellones;
	}

}
