package SNIICT.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SIF.PS_ICT_CAT_EQU_TBL")
public class CategoriaEquipo {

	@Id
	@Column(name="ICT_ID_EQUIPO_FLD")
	private Integer equipoId;
	
	@Column(name="ICT_DESC_FLD")
	private String nombreDeEquipo;
	
	public void setEquipoId(Integer equipoId) {
		this.equipoId = equipoId;
	}
	
	public Integer getEquipoId() {
		return this.equipoId;
	}
	
	public void setNombreDeEquipo(String nombreDeEquipo) {
		this.nombreDeEquipo = nombreDeEquipo;
	}
	
	public String getNombreDeEquipo() {
		return this.nombreDeEquipo;
	}
}
