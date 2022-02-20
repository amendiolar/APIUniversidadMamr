package com.ibm.academia.restapi.universidad.servicios;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;
import com.ibm.academia.restapi.universidad.repositorios.PabellonRepository;

@Service
public class PabellonDAOImpl extends GenericoDAOImpl<Pabellon, PabellonRepository> implements PabellonDAO
{
	public PabellonDAOImpl(PabellonRepository repository) 
	{
		super(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Pabellon> buscarPabellonPorCP(String codigoPostal) 
	{
		return repository.buscarPabellonPorCP(codigoPostal);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Pabellon> buscarPorNombre(String nombre) 
	{
		return repository.buscarPorNombre(nombre);
	}
}
