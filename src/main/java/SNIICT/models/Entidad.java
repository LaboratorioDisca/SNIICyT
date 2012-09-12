package SNIICT.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PS_STATE_TBL")
public class Entidad {
	
	@Column(name="COUNTRY")
	private String pais;

	@Column(name="DESCR")
	private String descripcion;
	
	@Id
	@Column(name="STATE")
	private String estado;
	
	@Column(name="DESCR_AC")
	private String descripcionAc;
	
	public Entidad() {
		
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getPais() {
		return pais;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcionAc(String descripcionAc) {
		this.descripcionAc = descripcionAc;
	}

	public String getDescripcionAc() {
		return descripcionAc;
	}
	
}
