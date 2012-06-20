package com.logicalnode.tarsys.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logicalnode.tarsys.domain.Consumo;
import com.logicalnode.tarsys.domain.Estudio;
import com.logicalnode.tarsys.domain.Factura;
import com.logicalnode.tarsys.domain.TipoDeConsumo;
import com.logicalnode.tarsys.domain.tarifa.media.AbstractTarifaBinomica;
import com.logicalnode.tarsys.domain.tarifa.media.AbstractTarifas;
import com.logicalnode.tarsys.repository.ConsumoDAO;
import com.logicalnode.tarsys.repository.EstudioDAO;
import com.logicalnode.tarsys.repository.FacturaDAO;
import com.logicalnode.tarsys.services.EstudioManager;
import com.logicalnode.tarsys.services.TarifasManager;

@Service
public class EstudioManagerImpl implements EstudioManager{

	@Autowired private EstudioDAO estudioDAO;
	@Autowired private FacturaDAO facturaDAO;
	@Autowired private ConsumoDAO consumoDAO;
	@Autowired private TarifasManager tarifasManager;
	
	public EstudioManagerImpl() {}

	public EstudioManagerImpl(EstudioDAO estudioDAO, FacturaDAO facturaDAO, ConsumoDAO consumoDAO, 
			TarifasManager tarifasManager) {
		this.estudioDAO = estudioDAO;
		this.facturaDAO = facturaDAO;
		this.consumoDAO = consumoDAO;
		this.tarifasManager = tarifasManager;
	}
	
	@Override
	public double obtenerPromedioMensualDeEnergiaConsumida(Long idEstudio){
		Estudio estudio = this.estudioDAO.find(idEstudio);
		String categoria = estudio.getCategoria();
		
		List<Factura> facturaList = this.facturaDAO.findAllForEstudio(idEstudio);
		int cantidadDeFacturas = facturaList.size();
		
		double totalDeEnergiaConsumida = 0;
		
		for (Factura factura : facturaList) {
			List<Consumo> consumoList = this.consumoDAO.findAllForFactura(factura.getId());
			for (Consumo consumo : consumoList) {
					String tipo = consumo.getTipo().toString();
				if(tipo.equals(TipoDeConsumo.Energia.toString()) ||
			       tipo.equals(TipoDeConsumo.EnergiaEnPunta.toString()) ||
			       tipo.equals(TipoDeConsumo.EnergiaFueraDePunta.toString()) ){
					totalDeEnergiaConsumida = totalDeEnergiaConsumida + consumo.getLectura() *  this.tarifasManager.obtenerCostoUnitarioDeConsumo(categoria, consumo.getTipo());
				}
			}
		}
		
		double promedio = totalDeEnergiaConsumida / cantidadDeFacturas;
		return promedio;
	}
	
	@Override
	public double obtenerPromedioDeExcesoDePotencia(Long idEstudio){
		Estudio estudio = this.estudioDAO.find(idEstudio);
		String categoria = estudio.getCategoria();
		
		AbstractTarifas tarifa = this.tarifasManager.obtenerTarifaPorCategoria(categoria);
		
		double totalDeExcesoDePotencia = 0;
		int cantidadDeFacturas;
		
		try {
			tarifa.getClass().asSubclass(AbstractTarifaBinomica.class);
			List<Factura> facturaList = this.facturaDAO.findAllForEstudio(idEstudio);
			cantidadDeFacturas = facturaList.size();
			for (Factura factura : facturaList) {
				List<Consumo> consumoList = this.consumoDAO.findAllForFactura(factura.getId());
				for (Consumo consumo : consumoList) {
					String tipo = consumo.getTipo().toString();
					if(tipo.equals(TipoDeConsumo.ExcesoDePotenciaReservada.toString()) ||
				       tipo.equals(TipoDeConsumo.ExcesoDePotenciaReservadaEnPunta.toString()) ||
				       tipo.equals(TipoDeConsumo.ExcesoDePotenciaReservadaFueraDePunta.toString()) ){
						totalDeExcesoDePotencia = totalDeExcesoDePotencia + consumo.getLectura() * this.tarifasManager.obtenerCostoUnitarioDeConsumo(categoria, consumo.getTipo());
					}
				}
			}
			
		} catch (ClassCastException e) {
			cantidadDeFacturas = 1;
		}		
		
		double promedio = totalDeExcesoDePotencia / cantidadDeFacturas;
		return promedio;
	}
	
}
