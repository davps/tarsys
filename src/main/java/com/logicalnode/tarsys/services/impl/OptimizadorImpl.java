package com.logicalnode.tarsys.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logicalnode.tarsys.domain.Estudio;
import com.logicalnode.tarsys.domain.TipoDeConsumo;
import com.logicalnode.tarsys.domain.tarifa.media.AbstractTarifas;
import com.logicalnode.tarsys.domain.tarifa.media.CostoDelTipoDeConsumo;
import com.logicalnode.tarsys.repository.EstudioDAO;
import com.logicalnode.tarsys.repository.FacturaDAO;
import com.logicalnode.tarsys.services.EstudioManager;
import com.logicalnode.tarsys.services.Optimizador;
import com.logicalnode.tarsys.services.TarifasManager;

@Service
public class OptimizadorImpl implements Optimizador{
	
	private static final Logger logger = LoggerFactory.getLogger(OptimizadorImpl.class);
	
	@Autowired private EstudioDAO estudioDAO;
	@Autowired private FacturaDAO facturaDAO;
	@Autowired private TarifasManager tarifasManager;
	@Autowired private EstudioManager estudioManager;
	
	public OptimizadorImpl() {}
	
	public OptimizadorImpl(EstudioDAO estudioDAO, FacturaDAO facturaDAO, 
			TarifasManager tarifasManager, EstudioManager estudioManager) {
		this.estudioDAO = estudioDAO;
		this.facturaDAO = facturaDAO;
		this.tarifasManager = tarifasManager;
		this.estudioManager = estudioManager;
	}

	@Override
	public AbstractTarifas calcularTarifaOptima(Estudio estudio){
		List<AbstractTarifas> tarifas = this.tarifasManager.getListaDeTarifas();
		
		double cOptimo = Math.pow(10, 99); //tiende a infinito
		AbstractTarifas tOptima = null;
		
		for (AbstractTarifas tarifa : tarifas) {
			double pago = this.calcularPagoPorTarifa(tarifa, estudio);
			logger.info("el pago por la tarifa {} es de : {}", tarifa.getCodigoDeCategoriaDeTarifa(), pago);
			logger.info("condición -  cOptimo:{} > pago:{}", cOptimo, pago);
			if(cOptimo > pago){
				cOptimo = pago;
				tOptima = tarifa;
				logger.info("condición cumplida, el nuevo cOptimo:{} para la categoria {}", cOptimo, tarifa.getCodigoDeCategoriaDeTarifa());
			}
		}
		
		return tOptima;
	}
	
	public double calcularPagoPorTarifa(AbstractTarifas tarifa, Estudio estudio){
		double promedioMensualDeEnergiaConsumida = this.estudioManager.obtenerPromedioMensualDeEnergiaConsumida(estudio.getId());
		double gasto = 0;
		
		for(CostoDelTipoDeConsumo costoObj : tarifa.getConsumos()){
			String categoriaTarifa = tarifa.getCodigoDeCategoriaDeTarifa();
			TipoDeConsumo tipoDeConsumo =  costoObj.getTipoDeConsumo();
			double costoUnitarioDeConsumo = this.tarifasManager.obtenerCostoUnitarioDeConsumo(categoriaTarifa, tipoDeConsumo); 
			gasto = gasto + costoUnitarioDeConsumo * promedioMensualDeEnergiaConsumida;
		}
		
		return gasto;
		
	}
	
}
