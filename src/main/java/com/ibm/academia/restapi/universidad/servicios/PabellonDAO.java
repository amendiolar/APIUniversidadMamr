package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;

public interface PabellonDAO extends GenericoDAO<Pabellon>
{
	public Iterable<Pabellon> buscarPabellonPorCP(String codigoPostal);
	public Iterable<Pabellon> buscarPorNombre(String nombre);
}
