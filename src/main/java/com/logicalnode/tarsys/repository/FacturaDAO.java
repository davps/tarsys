package com.logicalnode.tarsys.repository;

import java.util.List;

import com.logicalnode.tarsys.domain.Factura;

public interface FacturaDAO extends GenericDAO<Factura> {
	public List<Factura> findAllForEstudio(Long idEstudio);
}
