package SNIICT.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//SIF.PS_ICT_DATGRAL_TBL
@Entity
@Table(name="PS_ICT_DATGRAL_TBL")
public class DatoGeneral {
	
	public static String tableName="PS_ICT_DATGRAL_TBL";
	@Id
	@Column(name="ICT_ID_LABOR_FLD")
	private Integer laboratorioId;
	
	@Column(name="ICT_PAG_WEB_FLD")
	private String paginaWeb;
	
	@Column(name="ICT_NOM_CONTAC_FLD")
	private String nombreDeContacto;
	
	@Column(name="ICT_PTO_CONTAC_FLD")
	private String puestoDeContacto;
	
	@Column(name="ICT_EMAIL_FLD")
	private String email;
	
	@Column(name="ICT_TEL_CONTAC_FLD")
	private String telefonoDeContacto;
	
	@Column(name="ICT_COLONIA_FLD")
	private String colonia;
	
	@Column(name="ICT_CIUDAD_FLD")
	private String ciudad;
	
	@Column(name="ICT_CALLE_FLD")
	private String calle;

	@Column(name="ICT_CODPOS_FLD")
	private String codigoPostal;
	
	@Column(name="ICT_ENT_FED_FLD")
	private String estado;
	
	@Column(name="ICT_LATITUD_FLD")
	private Double latitud;
	
	@Column(name="ICT_LONGITUD_FLD")
	private Double longitud;
	
	public String getPaginaWeb() {
		if(paginaWeb == null)
			return "---";
		else
			return paginaWeb;
	}

	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}

	public String getNombreDeContacto() {
		if(this.nombreDeContacto == null) {
			return "No Disponible";
		}
		return nombreDeContacto;
	}

	public void setNombreDeContacto(String nombreDeContacto) {
		this.nombreDeContacto = nombreDeContacto;
	}

	public String getPuestoDeContacto() {
		if(this.puestoDeContacto == null) {
			return "No Disponible";
		}
		return puestoDeContacto;
	}

	public void setPuestoDeContacto(String puestoDeContacto) {
		this.puestoDeContacto = puestoDeContacto;
	}

	public String getEmail() {
		if(this.email == null) {
			return "No Proporcionado";
		}
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefonoDeContacto() {
		if(this.telefonoDeContacto == null) {
			return "No Proporcionado";
		}
		return telefonoDeContacto;
	}

	public void setTelefonoDeContacto(String telefonoDeContacto) {
		this.telefonoDeContacto = telefonoDeContacto;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getlaboratorioId() {
		return laboratorioId;
	}

	public void setlaboratorioId(Integer laboratorioId) {
		this.laboratorioId = laboratorioId;
	}

	public Double getLatitud() {
		if(latitud == null)
			return 0.0;
		else
			return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		if(longitud == null)
			return 0.0;
		else
			return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
}
