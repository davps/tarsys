package com.logicalnode.tarsys.repository;

import java.util.List;

import com.logicalnode.tarsys.domain.Consumo;

public interface ConsumoDAO extends GenericDAO<Consumo> {

	List<Consumo> findAllForFactura(Long idFactura);

}
