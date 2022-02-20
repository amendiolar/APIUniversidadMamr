package com.ibm.academia.restapi.universidad.repositorios;

import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;

public interface EmpleadoRepository extends PersonaRepository
{
	//@Query("select e from Empleado e where e.tipoEmpleado = ?1")
	public Iterable<Persona> buscarEmpleadoPorTipo(String tipoEmpleado);
}
