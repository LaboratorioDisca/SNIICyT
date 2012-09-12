package SNIICT.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="PS_CYT_DISCIP_TBL")
public class Disciplina {

	@Column(name="CTY_CVE_CAMPO")
	private String campo;
	@Id
	@Column(name="CYT_CVE_DISCIPLINA")
	private long id;
	@Column(name="CYT_DESCR_DISC")
	private String descripcion;
	@Column(name="STATUS")
	private String status;
	@Column(name="EFFDT")
	private Date date;
	
	public Disciplina() {
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
