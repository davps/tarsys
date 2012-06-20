package com.logicalnode.tarsys.services;

public interface EstudioManager {
	
	public double obtenerPromedioMensualDeEnergiaConsumida(Long idEstudio);
	public double obtenerPromedioDeExcesoDePotencia(Long idEstudio);

}
