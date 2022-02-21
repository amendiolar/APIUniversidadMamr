package com.ibm.academia.restapi.universidad;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.universidad.enumeradores.TipoEmpleado;
import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.modelo.entidades.Alumno;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;
import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelo.entidades.Direccion;
import com.ibm.academia.restapi.universidad.modelo.entidades.Empleado;
import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.modelo.entidades.Profesor;
import com.ibm.academia.restapi.universidad.servicios.AlumnoDAO;
import com.ibm.academia.restapi.universidad.servicios.AulaDAO;
import com.ibm.academia.restapi.universidad.servicios.CarreraDAO;
import com.ibm.academia.restapi.universidad.servicios.EmpleadoDAO;
import com.ibm.academia.restapi.universidad.servicios.PabellonDAO;
import com.ibm.academia.restapi.universidad.servicios.ProfesorDAO;

@Component
public class TestUniversidad implements CommandLineRunner
{
	@Autowired
	private CarreraDAO carreraDao;
	
	@Autowired
	private AlumnoDAO alumnoDao;
	
	@Autowired
	private AulaDAO aulaDao;
	
	@Autowired
	private EmpleadoDAO empleadoDao;
	
	@Autowired
	private PabellonDAO pabellonDao;
	
	@Autowired
	private ProfesorDAO profesorDao;
	
	
	@Override
	public void run(String... args) throws Exception 
	{
		// AULA
		TipoPizarron tipoPizarron = TipoPizarron.PIZARRON_BLANCO; 
		Aula aula1 = new Aula(null, 1, "10 por 10", 25, tipoPizarron, "amendiola");
		Aula aula = aulaDao.guardar(aula1);
		System.out.println(aula.toString());
		
		// ALUMNO
		Direccion direccionAlumno = new Direccion("Calle ciega", "75", "8005", "601", "6", "Puebla");
		Persona alumno = new Alumno(null, "Pepito", "Perez", "123456789", "amendiola", direccionAlumno);
		Persona personaAlumno = alumnoDao.guardar(alumno);
		System.out.println(personaAlumno.toString());
		
		// CARRERA
		Carrera ingenieriaSistemas = new Carrera(null, "sistemas", 60, 5, "amendiola");
		Carrera carrera = carreraDao.guardar(ingenieriaSistemas);
		System.out.println(carrera.toString());
		
		//EMPLEADO
		Direccion direccionEmpleado = new Direccion("Calle ciega", "78", "8005", "601", "6", "Bogota");
		TipoEmpleado tipoEmpleado = TipoEmpleado.ADMINISTRATIVO;
		BigDecimal sueldo = new BigDecimal(2000000);
		Persona empleado = new Empleado(null, "David", "Segura", "MERA700528", "amendiola", direccionEmpleado, sueldo, tipoEmpleado);
		Persona empleado1 = empleadoDao.guardar(alumno);
		System.out.println(empleado1.toString());
		
		//PABELLON
		Direccion direccionPabellon = new Direccion("Calle ciega -A", "75", "8005", "601", "6", "Bogota");
		Pabellon ingenieria = new Pabellon(null, 6000.0, "Facultad Ingenieria", direccionPabellon, "amendiola");
		Pabellon pabellon1 = pabellonDao.guardar(ingenieria);
		System.out.println(pabellon1.toString());
		
		// PROFESOR
		Direccion direccionProfesor = new Direccion("Calle ciega B", "178", "8005", "601", "6", "Bogota");
		BigDecimal sueldoProfesor = new BigDecimal(20000000);
		Profesor profesor1 = new Profesor(null, "Alfredo", "De La Garza", "ALF729427", "amendiola", direccionProfesor, sueldoProfesor);
		Persona profesorAlf = profesorDao.guardar(profesor1);
		System.out.println(profesorAlf.toString());
	}

}
