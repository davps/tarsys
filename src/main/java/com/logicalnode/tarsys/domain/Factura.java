package com.logicalnode.tarsys.domain;

public class Factura {

    private Long id;

    private Integer version;

    private long numeroDeFactura;

    private Long estudio;
    
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

	public long getNumeroDeFactura() {
        return this.numeroDeFactura;
    }

	public void setNumeroDeFactura(long numeroDeFactura) {
        this.numeroDeFactura = numeroDeFactura;
    }

	public Long getEstudio() {
		return estudio;
	}

	public void setEstudio(Long idEstudio) {
		this.estudio = idEstudio;
	}

	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Estudio: ").append(getEstudio()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("NumeroDeFactura: ").append(getNumeroDeFactura()).append(", ");
        sb.append("Version: ").append(getVersion());
        return sb.toString();
    }

}

