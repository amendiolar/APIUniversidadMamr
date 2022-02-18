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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.servicios.PersonaDAO;

@RestController
@RequestMapping("/restapi")
public class EmpleadoController 
{
	@Autowired
	@Qualifier("empleadoDAOImpl")
	private PersonaDAO empleadoDao;
	
	@PostMapping("/empleado")
	public ResponseEntity<?> crearEmpleado(@RequestBody Persona empleado)
	{	
		Persona empleadoGuardado = empleadoDao.guardar(empleado);
		return new ResponseEntity<Persona>(empleadoGuardado, HttpStatus.CREATED);
	}
	
	@GetMapping("/empleados/lista")
	public ResponseEntity<?> obtenerTodos()
	{
		List<Persona> empleados = (List<Persona>) empleadoDao.buscarTodos();
		
		if(empleados.isEmpty())
			throw new NotFoundException("No existen empleados");
		
		return new ResponseEntity<List<Persona>>(empleados, HttpStatus.OK);
	}
	
	@GetMapping("/empleado/{empleadoId}")
    public ResponseEntity<?> obtenerAlumnoPorId(@PathVariable Long empleadoId)
    {
        Optional<Persona> oEmpleado = empleadoDao.buscarPorId(empleadoId);
        
        if(!oEmpleado.isPresent()) 
            throw new NotFoundException(String.format("Empleado con id %d no existe", empleadoId));
        
        return new ResponseEntity<Persona>(oEmpleado.get(), HttpStatus.OK);
    }
	
	@DeleteMapping("/empleado/eliminar/empleadoId/{empleadoId}")
	public ResponseEntity<?> eliminarEmpleado(@PathVariable Long empleadoId)
	{
		Optional<Persona> oEmpleado = empleadoDao.buscarPorId(empleadoId);
		
		if(!oEmpleado.isPresent())
			throw new NotFoundException(String.format("El empleado con ID %d no existe", empleadoId));
		
		empleadoDao.eliminarPorId(oEmpleado.get().getId()); 
		return new ResponseEntity<String>("Empleado ID: " + empleadoId + " se elimino satisfactoriamente",  HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/empleado/actualizar/empleadoId/{empleadoId}")
	public ResponseEntity<?> actualizarEmpleado(@PathVariable Long empleadoId, @RequestBody Persona empleado)
	{
		Persona empleadoActualizado = ((EmpleadoDAO)empleadoDao).actualizar(empleadoId, empleado);
		return new ResponseEntity<Persona>(empleadoActualizado, HttpStatus.OK);
	}

	@GetMapping("/empleados/lista/{tipoEmpleado}")
	public ResponseEntity<?> empleadosPorTipo(@PathVariable String tipoEmpleado)
	{
		List<Persona> empleados = (List<Persona>) empleadoDao.buscarPorTipo(tipoEmpleado);
		
		if(empleados.isEmpty())
			throw new NotFoundException("No existen empleados");
		
		return new ResponseEntity<List<Persona>>(empleados, HttpStatus.OK);
	}
	
	@PutMapping("/empleado/asociar-pabellon")
	public ResponseEntity<?> asignarPabellonEmpleado(@RequestParam Long pabellonId, @RequestParam(name = "empleado_id") Long empleadoId)
	{
		Persona empleado = ((EmpleadoDAO)empleadoDao).asociarPabellonEmpleado(pabellonId, empleadoId); 
		return new ResponseEntity<Persona>(empleado, HttpStatus.OK);
	}

}
