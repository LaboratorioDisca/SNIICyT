package SNIICT.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
// @ Conacyt : 'SIF.PS_CYT_SECTOR_INEG'
@Table(name="SIF.PS_CYT_SECTOR_INEG")
public class Sector {
	@Id
	@Column(name="CYT_SECTOR_ID")
	private String sectorId;
	
	@Column(name="CYT_DESCR_SECTOR2")
	private String descripcion;

	public String getSectorId() {
		return sectorId;
	}

	public void setSectorId(String sectorId) {
		this.sectorId = sectorId;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
