package com.ibm.academia.restapi.universidad.servicios;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ibm.academia.restapi.universidad.repositorios.AlumnoRepository;

@SpringBootTest
public class AlumnoDAOImplTest 
{
	@MockBean
	private AlumnoRepository alumnoRepository;
	
	@Autowired
	private AlumnoDAO alumnoDao;
	
	@Test
	void buscarAlumnosPorNombreCarrera() 
	{
		// When
		alumnoDao.buscarAlumnosPorNombreCarrera(anyString());

		// Then
		verify(alumnoRepository).buscarAlumnosPorNombreCarrera(anyString());
	}
}