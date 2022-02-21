package com.ibm.academia.restapi.universidad.modelo.entidades;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class Direccion implements Serializable 
{
	private String calle;
	private String numero;
	private String codigoPostal;
	private String departamento;
	private String piso;
	private String localidad;
	
	public Direccion(String calle, String numero, String codigoPostal, String departamento, String piso, String localidad)
	{
		this.calle = calle;
		this.numero = numero;
		this.codigoPostal = codigoPostal;
		this.departamento = departamento;
		this.piso = piso;
		this.localidad = localidad;
	}
	
	private static final long serialVersionUID = 1742467524557831202L;
}