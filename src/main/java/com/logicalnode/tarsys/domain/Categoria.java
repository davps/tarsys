package com.logicalnode.tarsys.domain;

import java.util.List;
import com.logicalnode.tarsys.domain.Estudio;

public class Categoria {

    private String codigo;

    private String descripcion;

    private Estudio estudio;
    

	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Codigo: ").append(getCodigo()).append(", ");
        sb.append("Descripcion: ").append(getDescripcion()).append(", ");
        sb.append("Estudio: ").append(getEstudio()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Version: ").append(getVersion());
        return sb.toString();
    }

	public String getCodigo() {
        return this.codigo;
    }

	public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

	public String getDescripcion() {
        return this.descripcion;
    }

	public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

	public Estudio getEstudio() {
        return this.estudio;
    }

	public void setEstudio(Estudio estudio) {
        this.estudio = estudio;
    }

    private Long id;

    private Integer version;

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

}
