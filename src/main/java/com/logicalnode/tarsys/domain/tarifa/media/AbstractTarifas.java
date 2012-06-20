package com.logicalnode.tarsys.domain.tarifa.media;

import java.util.List;

import com.logicalnode.tarsys.domain.TipoDeConsumo;
import com.logicalnode.tarsys.domain.tarifa.media.enumerations.GrupoDeConsumo;

public abstract class AbstractTarifas {

	private Long id;

	private String codigoDeCategoriaDeTarifa;
	
	private List<CostoDelTipoDeConsumo> costoDelTipoDeConsumos;

	private GrupoDeConsumo grupoDeConsumo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoDeCategoriaDeTarifa() {
		return codigoDeCategoriaDeTarifa;
	}

	public void setCodigoDeCategoriaDeTarifa(String codigoDeCategoriaDeTarifa) {
		this.codigoDeCategoriaDeTarifa = codigoDeCategoriaDeTarifa;
	}

	public List<CostoDelTipoDeConsumo> getConsumos() {
		return costoDelTipoDeConsumos;
	}

	public void setConsumos(List<CostoDelTipoDeConsumo> costoDelTipoDeConsumos) {
		this.costoDelTipoDeConsumos = costoDelTipoDeConsumos;
	}

	public List<CostoDelTipoDeConsumo> getCostoDelTipoDeConsumos() {
		return costoDelTipoDeConsumos;
	}

	public void setCostoDelTipoDeConsumos(
			List<CostoDelTipoDeConsumo> costoDelTipoDeConsumos) {
		this.costoDelTipoDeConsumos = costoDelTipoDeConsumos;
	}

	public GrupoDeConsumo getGrupoDeConsumo() {
		return grupoDeConsumo;
	}

	public void setGrupoDeConsumo(GrupoDeConsumo grupoDeConsumo) {
		this.grupoDeConsumo = grupoDeConsumo;
	}
	
//	public abstract double obtenerPromedioMensualDeEnergiaConsumida(double costoUnitarioDeEnergiaConsuma);
	
//	public abstract double obtenerPrecioAPagarPorConsumoDeEnergia
}
