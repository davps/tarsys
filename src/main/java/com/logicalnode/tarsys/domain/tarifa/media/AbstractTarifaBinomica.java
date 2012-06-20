package com.logicalnode.tarsys.domain.tarifa.media;

public abstract class AbstractTarifaBinomica extends AbstractTarifasMedia {

	private double potenciaReservadaMaxima;

	private double potenciaReservadaMinima;

	public double getPotenciaReservadaMaxima() {
		return potenciaReservadaMaxima;
	}

	public void setPotenciaReservadaMaxima(double potenciaReservadaMaxima) {
		this.potenciaReservadaMaxima = potenciaReservadaMaxima;
	}

	public double getPotenciaReservadaMinima() {
		return potenciaReservadaMinima;
	}

	public void setPotenciaReservadaMinima(double potenciaReservadaMinima) {
		this.potenciaReservadaMinima = potenciaReservadaMinima;
	}

}
