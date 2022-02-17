package com.ibm.academia.restapi.universidad.servicios;

import static com.ibm.academia.restapi.universidad.datos.DatosDummy.carrera01;
import static com.ibm.academia.restapi.universidad.datos.DatosDummy.carrera03;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.repositorios.CarreraRepository;

public class CarreraDAOImplTest 
{
	CarreraDAO carreraDao;
	CarreraRepository carreraRepository;
	
	@BeforeEach
	void setUp()
	{
		carreraRepository = mock(CarreraRepository.class);
		carreraDao = new CarreraDAOImpl(carreraRepository);
	}
	
	@Test
	@DisplayName("Test: Buscar carreras por nombre")
	void findCarrerasByNombreContains()
	{
		//Given
		String nombreCarrera = "Ingenieria";
		when(carreraRepository.findCarrerasByNombreContains(nombreCarrera))
        		.thenReturn(Arrays.asList(carrera01(), carrera03()));
		
		//When
		List<Carrera> expected = (List<Carrera>) carreraDao.findCarrerasByNombreContains(nombreCarrera);
		
		//Then
		assertThat(expected.get(0)).isEqualTo(carrera01());
		assertThat(expected.get(1)).isEqualTo(carrera03());
		
		verify(carreraRepository).findCarrerasByNombreContains(nombreCarrera);
		
	}
	
	@Test
	@DisplayName("Test: Buscar carreras por nombre NO case sensitive")
	void findCarrerasByNombreContainsIgnoreCase()
	{
		//Given
        String nombre = "ingenieria";
        when(carreraRepository.findCarrerasByNombreContainsIgnoreCase(nombre))
                .thenReturn(Arrays.asList(carrera01(), carrera03()));

        //When
        List<Carrera> expected = (List<Carrera>) carreraDao.findCarrerasByNombreContainsIgnoreCase(nombre);

        //Then
        assertThat(expected.get(0)).isEqualTo(carrera01());
        assertThat(expected.get(1)).isEqualTo(carrera03());

        verify(carreraRepository).findCarrerasByNombreContainsIgnoreCase(nombre);
	}
	
	@Test
	@DisplayName("Test: Buscar carreras mayores a N años")
	void findCarrerasByCantidadAniosAfter()
	{
		//Given
        Integer cantidad = 4;
        when(carreraRepository.findCarrerasByCantidadAniosAfter(cantidad))
                .thenReturn(Arrays.asList(carrera01(), carrera03()));

        //When
        List<Carrera> expected = (List<Carrera>) carreraDao.findCarrerasByCantidadAniosAfter(cantidad);

        //Then
        assertThat(expected.get(0)).isEqualTo(carrera01());
        assertThat(expected.get(1)).isEqualTo(carrera03());

        verify(carreraRepository).findCarrerasByCantidadAniosAfter(cantidad);
	}
}