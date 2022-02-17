package com.ibm.academia.restapi.universidad.datos;

import java.math.BigDecimal;

import com.ibm.academia.restapi.universidad.enumeradores.TipoEmpleado;
import com.ibm.academia.restapi.universidad.modelo.entidades.Alumno;
import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelo.entidades.Direccion;
import com.ibm.academia.restapi.universidad.modelo.entidades.Empleado;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.modelo.entidades.Profesor;

public class DatosDummy 
{
	public static Carrera carrera01() 
	{
		return new Carrera(null, "Ingenieria en Sistemas", 50, 5, "nsegura"); 
	}

	public static Carrera carrera02() 
	{
		return new Carrera(null, "Licenciatura en Sistemas", 45, 4, "nsegura");
	}

	public static Carrera carrera03() 
	{
		return new Carrera(null, "Ingenieria Industrial", 60, 5, "nsegura");
	}
	
	public static Persona empleado01() 
	{
		return new Empleado(null, "Lautaro", "Lopez", "25147036", "nsegura", new Direccion(), new BigDecimal("46750"), TipoEmpleado.ADMINISTRATIVO);
	}
	
	public static Persona empleado02() 
	{
		return new Empleado(null, "Lenadro", "Lopez", "25174630", "nsegura", new Direccion(), new BigDecimal("46750.70"), TipoEmpleado.MANTENIMIENTO);
	}
	
	public static Persona profesor01()
	{
		return new Profesor(null, "Martin", "Axacar", "4477899", "nsegura", new Direccion(), new BigDecimal("600000"));
	}
	
	public static Persona alumno01()
	{
		return new Alumno(null, "Jhon", "Benitez", "4223715", "nsegura", new Direccion());
	}
	
	public static Persona alumno02() 
	{
		return new Alumno(null, "Andres", "Benitez", "45233891", "nsegura", new Direccion());
	}

	public static Persona alumno03() 
	{
		return new Alumno(null, "Joaquin", "Leon", "45233012", "nsegura", new Direccion());
	}
}