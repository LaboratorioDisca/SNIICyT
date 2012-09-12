package SNIICT.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SIF.PS_ICT_BUS_DEPE_VW")
public class Dependencia {
	
	@Id
	@Column(name="ICT_CVE_DEPEN_FLD")
	private String id;
	
	@Column(name="ICT_DESC_DEPEN_FLD")
	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
