package com.ibm.academia.restapi.universidad.servicios;

import java.util.Optional;

import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;

public interface AulaDAO extends GenericoDAO<Aula>
{
	public Iterable<Aula> aulasPorTipoPizarron(String tipoPizarron);
	
	public Iterable<Aula> aulasPorPabellon(String Pabellon);
	
	public Optional<Aula> aulaPorNumeroAula(Integer numeroAula);
}
