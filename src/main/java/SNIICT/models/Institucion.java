package SNIICT.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SIF.PS_ICT_BUS_INST_VW")
public class Institucion {
	@Id
	@Column(name="ICT_ID_INSTITU_FLD")
	private String institucionId;
	
	@Column(name="ICT_DESC_INST_FLD")
	private String descripcion;

	public String getInstitucionId() {
		return institucionId;
	}

	public void setInstitucionId(String institucionId) {
		this.institucionId = institucionId;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
