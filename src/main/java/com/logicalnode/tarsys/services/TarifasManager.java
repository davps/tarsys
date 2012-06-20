package com.logicalnode.tarsys.services;

import java.util.List;

import com.logicalnode.tarsys.domain.Consumo;
import com.logicalnode.tarsys.domain.Factura;
import com.logicalnode.tarsys.domain.TipoDeConsumo;
import com.logicalnode.tarsys.domain.tarifa.media.AbstractTarifas;
import com.logicalnode.tarsys.domain.tarifa.media.CostoDelTipoDeConsumo;
import com.logicalnode.tarsys.domain.tarifa.media.enumerations.GrupoDeConsumo;

public interface TarifasManager {
	
	public List<AbstractTarifas> getListaDeTarifas();
	
	public void setListaDeTarifas(List<AbstractTarifas> listaDeTarifas);
	
	public CostoDelTipoDeConsumo obtenerCostoUnitarioDeConsumo(Factura factura, Consumo consumo);
	
	public double 				 obtenerCostoUnitarioDeConsumo(String categoria, TipoDeConsumo tipoDeConsumo);
	
	public List<AbstractTarifas> filtrarPorGrupoDeConsumo(GrupoDeConsumo grupoDeConsumo);
	
	public AbstractTarifas obtenerTarifaPorCategoria (String categoria);

}
