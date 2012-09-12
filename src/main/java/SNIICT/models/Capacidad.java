package SNIICT.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PS_ICT_CAPACID_TBL")
public class Capacidad {

	@Id
	@Column(name="ICT_ID_LABOR_FLD")
	private Integer laboratorioId;
	
	@Column(name="ICT_PER_SERV_FLD")
	private String campoPermisoServicio;
	
	public boolean getPermiteAcceso() {
		return campoPermisoServicio.equals('S');
	}
	
}
