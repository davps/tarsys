package com.logicalnode.tarsys.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.logicalnode.tarsys.domain.Consumo;
import com.logicalnode.tarsys.domain.Estudio;
import com.logicalnode.tarsys.domain.Factura;
import com.logicalnode.tarsys.domain.TipoDeConsumo;
import com.logicalnode.tarsys.domain.tarifa.media.AbstractTarifas;
import com.logicalnode.tarsys.domain.tarifa.media.CostoDelTipoDeConsumo;
import com.logicalnode.tarsys.domain.tarifa.media.enumerations.GrupoDeConsumo;
import com.logicalnode.tarsys.repository.EstudioDAO;
import com.logicalnode.tarsys.services.TarifasManager;

public class TarifasManagerImpl implements TarifasManager{
	
	@Autowired private EstudioDAO estudioDAO;
	
	public TarifasManagerImpl() {}
	
	public TarifasManagerImpl(EstudioDAO estudioDAO) {
		this.estudioDAO = estudioDAO;
	}
	
	private List<AbstractTarifas> listaDeTarifas;

	@Override
	public List<AbstractTarifas> getListaDeTarifas() {
		return listaDeTarifas;
	}

	@Override
	public void setListaDeTarifas(List<AbstractTarifas> listaDeTarifas) {
		this.listaDeTarifas = listaDeTarifas;
	}
	
	@Override
	public List<AbstractTarifas> filtrarPorGrupoDeConsumo(GrupoDeConsumo grupoDeConsumo){
		List<AbstractTarifas> tarifaList = new ArrayList<AbstractTarifas>();
		for (AbstractTarifas tarifa : this.listaDeTarifas) {
			if( tarifa.getGrupoDeConsumo().toString().equals(grupoDeConsumo.toString()) ){
				tarifaList.add(tarifa);
			}
		}
		return tarifaList;
	}
	
	@Override
	public AbstractTarifas obtenerTarifaPorCategoria (String categoria){
		for (AbstractTarifas tarifa : this.listaDeTarifas) {
			if(tarifa.getCodigoDeCategoriaDeTarifa().equals(categoria)){
				return tarifa;
			}
		}
		return null;
	}
	
	@Override
	public double obtenerCostoUnitarioDeConsumo(String categoria, TipoDeConsumo tipoDeConsumo){
		CostoDelTipoDeConsumo costo = this.abstractObtenerCostoUnitarioDeConsumo(categoria, tipoDeConsumo);
		if(costo != null){
			return costo.getCosto();
		}else{
			return 0;
		}
	}
	
	@Override
	public CostoDelTipoDeConsumo obtenerCostoUnitarioDeConsumo(Factura factura, Consumo consumo){
		Long estudioID = factura.getEstudio();
		Estudio estudio = this.estudioDAO.find(estudioID);
		String categoria = estudio.getCategoria();
		TipoDeConsumo tipoDeConsumo = consumo.getTipo();
		
		return this.abstractObtenerCostoUnitarioDeConsumo(categoria, tipoDeConsumo);
	}

	private CostoDelTipoDeConsumo abstractObtenerCostoUnitarioDeConsumo(String categoria, TipoDeConsumo tipoDeConsumo){
		for (AbstractTarifas tarifa : this.listaDeTarifas) {
			if(categoria.equals(tarifa.getCodigoDeCategoriaDeTarifa())){
				for(CostoDelTipoDeConsumo costo : tarifa.getConsumos()){
					if(costo.getTipoDeConsumo().toString().equals(tipoDeConsumo.toString())){
						return costo;
					}
				}
			}
		}
		return null;
	}
	
}
