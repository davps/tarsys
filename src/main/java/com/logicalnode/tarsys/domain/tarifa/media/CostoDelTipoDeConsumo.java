package com.logicalnode.tarsys.domain.tarifa.media;

import javax.persistence.Id;

import com.logicalnode.tarsys.domain.TipoDeConsumo;
import com.logicalnode.tarsys.domain.tarifa.media.enumerations.Unidad;

public class CostoDelTipoDeConsumo {
	
	@Id
	private String id;

	private Unidad unidad;
	
	private TipoDeConsumo tipoDeConsumo;
	
	private Double costo;
	
	private Long idTarifa;

		
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public TipoDeConsumo getNombreDeConcepto() {
		return tipoDeConsumo;
	}

	public void setNombreDeConcepto(TipoDeConsumo tipoDeConsumo) {
		this.tipoDeConsumo = tipoDeConsumo;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public TipoDeConsumo getTipoDeConsumo() {
		return tipoDeConsumo;
	}

	public void setTipoDeConsumo(TipoDeConsumo tipoDeConsumo) {
		this.tipoDeConsumo = tipoDeConsumo;
	}

	public Long getIdTarifa() {
		return idTarifa;
	}

	public void setIdTarifa(Long idTarifa) {
		this.idTarifa = idTarifa;
	}

	public CostoDelTipoDeConsumo(Unidad unidad, TipoDeConsumo tipoDeConsumo,
			double costo) {
		super();
		this.unidad = unidad;
		this.tipoDeConsumo = tipoDeConsumo;
		this.costo = costo;
	}

	
	public CostoDelTipoDeConsumo() {

	}	

}
