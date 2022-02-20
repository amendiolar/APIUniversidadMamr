package com.ibm.academia.restapi.universidad.repositorios;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;

public interface AulaRepository extends CrudRepository<Aula, Long>
{
	//@Query("select a from Aula a where a.tipoPizarron = ?1")
	public Iterable<Aula> aulasPorTipoPizarron(String tipoPizarron);
		
	//@Query("select a from Aula a where a.pabellon = ?1")
	public Iterable<Aula> aulasPorPabellon(String Pabellon);
		
	//@Query("select a from Aula a where a.numeroAula = ?1")
	public Optional<Aula> aulaPorNumeroAula(Integer numeroAula);
}
