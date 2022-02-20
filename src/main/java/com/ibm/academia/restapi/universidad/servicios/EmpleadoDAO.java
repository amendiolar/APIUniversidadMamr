package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;

public interface EmpleadoDAO extends PersonaDAO
{
	public Iterable<Persona> buscarEmpleadoPorTipo(String tipoEmpleado);
}
