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
@Table(name="PS_ICT_ACRECER_TBL")
public class Acreditacion implements Serializable {

	@Id
	@Column(name="ICT_ACRED_CERT_FLD")
	private String nombre;
	
	@Id
	@Column(name="ICT_QUIEN_OTOR_FLD")
	private String otorgante;
	
	@Id
	@Column(name="ICT_FECINI_VIG_FLD")
	private Date fechaDeInicio;
	
	@Id
	@Column(name="ICT_FECFIN_VIG_FLD")
	private Date fechaDeTermino;
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getOtorgante() {
		return this.otorgante;
	}
	
	public String getFechaDeInicio() {
		SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
		return formateador.format(this.fechaDeInicio);
	}

	public String getFechaDeTermino() {
		SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
		return formateador.format(this.fechaDeTermino);
	}
}
