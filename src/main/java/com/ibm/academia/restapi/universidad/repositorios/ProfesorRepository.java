package com.ibm.academia.restapi.universidad.repositorios;

import org.springframework.stereotype.Repository;

import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;

@Repository("repositorioProfesor")
public interface ProfesorRepository extends PersonaRepository
{
	//@Query("select p from Profesor p join fetch p.carrera c where c.nombre = ?1")
	public Iterable<Persona> findProfesoresByCarrera(String carrera);
}
