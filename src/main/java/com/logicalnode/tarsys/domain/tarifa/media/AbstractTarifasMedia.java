package com.logicalnode.tarsys.domain.tarifa.media;

import java.util.List;

import com.logicalnode.tarsys.domain.tarifa.media.enumerations.InstalacionDeEntrega;

public abstract class AbstractTarifasMedia extends AbstractTarifas{

	private List<InstalacionDeEntrega> instalacionDeEntrega;

	private List<Double> tensionDeAbastecimiento;


	public List<InstalacionDeEntrega> getInstalacionDeEntrega() {
		return instalacionDeEntrega;
	}

	public void setInstalacionDeEntrega(
			List<InstalacionDeEntrega> instalacionDeEntrega) {
		this.instalacionDeEntrega = instalacionDeEntrega;
	}

	public List<Double> getTensionDeAbastecimiento() {
		return tensionDeAbastecimiento;
	}

	public void setTensionDeAbastecimiento(List<Double> tensionDeAbastecimiento) {
		this.tensionDeAbastecimiento = tensionDeAbastecimiento;
	}
	
}
