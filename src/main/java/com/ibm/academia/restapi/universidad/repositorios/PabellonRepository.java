package com.ibm.academia.restapi.universidad.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;

@Repository
public interface PabellonRepository extends CrudRepository<Pabellon, Long>
{
	//@Query("select p from Pabellon p where p.codigoPostal = ?1")
	public Iterable<Pabellon> buscarPabellonPorCP(String codigoPostal);
		
	//@Query("select p from Pabellon p where p.nombre = ?1")
	public Iterable<Pabellon> buscarPorNombre(String nombre);
}
