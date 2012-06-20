package com.logicalnode.tarsys.domain;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;
import com.logicalnode.tarsys.domain.TipoDeConsumo;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import com.logicalnode.tarsys.domain.Factura;
import javax.persistence.ManyToOne;

public class Consumo {

    private Long id;

    private Integer version;

    private TipoDeConsumo tipo;

    private long lectura;

    private double constante;

    private Long factura;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public Integer getVersion() {
        return this.version;
    }

	public void setVersion(Integer version) {
        this.version = version;
    }

	public TipoDeConsumo getTipo() {
        return this.tipo;
    }

	public void setTipo(TipoDeConsumo tipo) {
        this.tipo = tipo;
    }

	public long getLectura() {
        return this.lectura;
    }

	public void setLectura(long lectura) {
        this.lectura = lectura;
    }

	public double getConstante() {
        return this.constante;
    }

	public void setConstante(double constante) {
        this.constante = constante;
    }

	public Long getFactura() {
        return this.factura;
    }

	public void setFactura(Long factura) {
        this.factura = factura;
    }
	
	

	public Consumo(Long id, Integer version, TipoDeConsumo tipo, long lectura,
			double constante, Long factura) {
		super();
		this.id = id;
		this.version = version;
		this.tipo = tipo;
		this.lectura = lectura;
		this.constante = constante;
		this.factura = factura;
	}

	public Consumo() {

	}
	
	public Consumo(TipoDeConsumo tipo) {
		super();
		this.tipo = tipo;
	}

	public Consumo(TipoDeConsumo tipo, long lectura, double constante) {
		super();
		this.tipo = tipo;
		this.lectura = lectura;
		this.constante = constante;
	}

	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Constante: ").append(getConstante()).append(", ");
        sb.append("Factura: ").append(getFactura()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Lectura: ").append(getLectura()).append(", ");
        sb.append("Tipo: ").append(getTipo()).append(", ");
        sb.append("Version: ").append(getVersion());
        return sb.toString();
    }

}
