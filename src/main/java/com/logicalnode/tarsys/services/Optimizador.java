package com.logicalnode.tarsys.services;

import com.logicalnode.tarsys.domain.Estudio;
import com.logicalnode.tarsys.domain.tarifa.media.AbstractTarifas;

public interface Optimizador {

	public AbstractTarifas calcularTarifaOptima(Estudio estudio);
	
}
