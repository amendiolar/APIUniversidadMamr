package com.ibm.academia.restapi.universidad.controladores;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restapi")
public class MiPrimerControlador 
{
	private final static Logger logger = LoggerFactory.getLogger(MiPrimerControlador.class);
	
	@GetMapping("/hola-mundo")
	public ResponseEntity<?> holaMundo()
	{
		Map<String, String> respuesta = new HashMap<String, String>();
		respuesta.put("mensaje", "Hola mundo desde REST-API");
		
		logger.trace("trace log");
		logger.debug("debug log");
		logger.info("info log");
		logger.warn("warning log");
		logger.error("error log");
		
		return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.ACCEPTED); 
	}
}