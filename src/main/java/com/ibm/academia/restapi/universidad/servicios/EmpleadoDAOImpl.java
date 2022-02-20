package com.ibm.academia.restapi.universidad.servicios;

import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.repositorios.EmpleadoRepository;
import com.ibm.academia.restapi.universidad.repositorios.PersonaRepository;

public class EmpleadoDAOImpl extends PersonaDAOImpl implements EmpleadoDAO
{
	public EmpleadoDAOImpl(PersonaRepository repository) 
	{
		super(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Persona> buscarEmpleadoPorTipo(String tipoEmpleado) 
	{
		return ((EmpleadoRepository)repository).buscarEmpleadoPorTipo(tipoEmpleado);
	}
}
