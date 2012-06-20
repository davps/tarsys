package com.logicalnode.tarsys.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logicalnode.tarsys.domain.Consumo;
import com.logicalnode.tarsys.domain.Factura;
import com.logicalnode.tarsys.domain.tarifa.media.CostoDelTipoDeConsumo;
import com.logicalnode.tarsys.repository.ConsumoDAO;
import com.logicalnode.tarsys.repository.FacturaDAO;
import com.logicalnode.tarsys.services.FacturasManager;
import com.logicalnode.tarsys.services.TarifasManager;

@Service
public class FacturasManagerImpl implements FacturasManager {
	
	@Autowired private ConsumoDAO consumoDAO;
	@Autowired private FacturaDAO facturaDAO;
	@Autowired private TarifasManager tarifasManager;
	
	public FacturasManagerImpl() {}

	public FacturasManagerImpl(FacturaDAO facturaDAO, ConsumoDAO consumoDAO, 
			TarifasManagerImpl tarifasManager) {
		this.facturaDAO = facturaDAO;
		this.consumoDAO = consumoDAO;
		this.tarifasManager = tarifasManager;
	}

	public double calcularMontoAPagar(Factura factura){
		List<Consumo> consumoList = this.consumoDAO.findAllForFactura(factura.getId());
		
		double monto = 0;
		
		for (Consumo consumo : consumoList) {
			CostoDelTipoDeConsumo costo = this.tarifasManager.obtenerCostoUnitarioDeConsumo(factura, consumo);
			monto = monto + ( consumo.getLectura() * costo.getCosto() ); 
		}
		
		return monto;
	}
	
}
