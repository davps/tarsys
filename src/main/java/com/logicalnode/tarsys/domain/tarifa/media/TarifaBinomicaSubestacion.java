package com.logicalnode.tarsys.domain.tarifa.media;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.logicalnode.tarsys.domain.Consumo;
import com.logicalnode.tarsys.domain.Estudio;
import com.logicalnode.tarsys.domain.Factura;
import com.logicalnode.tarsys.domain.TipoDeConsumo;
import com.logicalnode.tarsys.repository.ConsumoDAO;
import com.logicalnode.tarsys.repository.EstudioDAO;
import com.logicalnode.tarsys.repository.FacturaDAO;
import com.logicalnode.tarsys.services.TarifasManager;

public class TarifaBinomicaSubestacion extends AbstractTarifaBinomicaNoDiferencial {
//
//	@Autowired private EstudioDAO estudioDAO;
//	@Autowired private FacturaDAO facturaDAO;
//	@Autowired private ConsumoDAO consumoDAO;
//	@Autowired private TarifasManager tarifasManager;
//
//	public TarifaBinomicaSubestacion(EstudioDAO estudioDAO, FacturaDAO facturaDAO, ConsumoDAO consumoDAO, 
//			TarifasManager tarifasManager) {
//		this.estudioDAO = estudioDAO;
//		this.facturaDAO = facturaDAO;
//		this.consumoDAO = consumoDAO;
//		this.tarifasManager = tarifasManager;
//	}
//	
//	public TarifaBinomicaSubestacion() {
//
//	}
//	
//	public void obtenerPrecioAPagarPorConsumos(Estudio estudio){
////		this.tarifasManager.obtenerTarifaPorCategoria(estudio.getCategoria());
//		List<Factura> facturaList = this.facturaDAO.findAllForEstudio(estudio.getId());
//		
//		double pago = 0;
//		for (Factura factura : facturaList) {
//			for( Consumo consumo : this.consumoDAO.findAllForFactura(factura.getId()) ){
//				TipoDeConsumo.PotenciaReservada;
//				TipoDeConsumo.ExcesoDePotenciaReservada;
//				TipoDeConsumo.EnergiaEnPunta;
//				TipoDeConsumo.EnergiaFueraDePunta;
//			}
//		}
//	}
	
}
