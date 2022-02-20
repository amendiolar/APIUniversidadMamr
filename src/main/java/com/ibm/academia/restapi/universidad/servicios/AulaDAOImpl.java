package com.ibm.academia.restapi.universidad.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;
import com.ibm.academia.restapi.universidad.repositorios.AulaRepository;

@Service
public class AulaDAOImpl extends GenericoDAOImpl<Aula, AulaRepository> implements AulaDAO
{
	@Autowired
	public AulaDAOImpl(AulaRepository repository) 
	{
		super(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Aula> aulasPorTipoPizarron(String tipoPizarron) 
	{
		return repository.aulasPorTipoPizarron(tipoPizarron);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Aula> aulasPorPabellon(String Pabellon) 
	{
		return repository.aulasPorPabellon(Pabellon);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Aula> aulaPorNumeroAula(Integer numeroAula) 
	{
		return repository.aulaPorNumeroAula(numeroAula);
	}

}
