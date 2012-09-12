package SNIICT.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SIF.PS_ICT_BUS_SECT_VW")
public class Subdependencia {

	@Id
	@Column(name="ICT_CVE_SECTOR_FLD")
	private String subdependenciaId;
	
	@Column(name="ICT_DES_SECTOR_FLD")
	private String descripcion;
	
	public String getSubdependenciaId() {
		return subdependenciaId;
	}

	public void setSubdependenciaId(String subdependenciaId) {
		this.subdependenciaId = subdependenciaId;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
