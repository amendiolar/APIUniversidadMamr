package com.ibm.academia.restapi.universidad;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ApiUniversidadMApplicationTests 
{
	Calculadora calculadora = new Calculadora();
	
	@Test
	@DisplayName("Test: Suma de valores a y b")
	void sumarValores() 
	{
		// TÉCNICA GWT
		
		//Given --> Es cuando se define el contexto o las precondiciones.
		Integer valorA = 7;
		Integer valorB = 15;
		
		//When -->  Es cuando se ejecuta la acción, es decir que se quiere probar.
		Integer expected = calculadora.sumar(valorA, valorB);
		
		//Then --> Es cuando se debe validar que lo que se esta probando funciona correctamente.
		Integer resultadoEsperado = 22;
		assertThat(expected).isEqualTo(resultadoEsperado);
		
	}
	
	class Calculadora
	{
		Integer sumar(Integer valorA, Integer valorB)
		{
			return valorA + valorB;
		}
	}
}