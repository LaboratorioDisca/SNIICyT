package SNIICT.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PS_CYT_CAMPO_TBL")
public class AreaConocimiento implements Serializable {

	@Column(name="CTY_DESCR_CAMPO")
	private String nombre;
	
	@Id
	@Column(name="CTY_CVE_CAMPO", nullable=false)
	private long id;

	private static final long serialVersionUID = 5445;

	public AreaConocimiento() {
		super();
	}
	
	public AreaConocimiento(long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public long getId() {
		return this.id;
	}

}
