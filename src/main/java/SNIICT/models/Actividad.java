package SNIICT.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// SIF.PS_ICT_ACTIVID_TBL
@Entity
@Table(name="SIF.PS_ICT_ACTIVID_TBL")
public class Actividad {
	
	public static String tableName="SIF.PS_ICT_ACTIVID_TBL";
	@Id
	@Column(name="ICT_ID_LABOR_FLD")
	private Integer laboratorioId;
	
	@Column(name="ICT_ANCARCUTIP_FLD")
	private String analisisYCaracterizacion;
	
	@Column(name="ICT_INVESAPLIC_FLD")
	private String investigacionAplicada;
	
	@Column(name="ICT_ELABPROT_FLD")
	private String elaboracionPrototipo;
	
	@Column(name="ICT_DOC_CAPAC_FLD")
	private String docenciaCapacitacion;
	
	@Column(name="ICT_PRUCUATIP_FLD")
	private String pruebasCualquierTipo;
	
	@Column(name="ICT_DESAEXP_FLD")
	private String desarrolloExperimental;
	
	@Column(name="ICT_PRODESCPIL_FLD")
	private String produccionesEscalaPiloto;
	
	@Column(name="ICT_OTROS_FLD")
	private String otros;
	
	@Column(name="ICT_OTROS_ESP_FLD")
	private String otrosEspecificados;
	
	@Column(name="ICT_INVCIEBAS_FLD")
	private String investigacionCientificaBasica;
	
	@Column(name="ICT_DESPROEQ_FLD")
	private String desarrolloProductosProcesosEquipos;
	
	@Column(name="ICT_PROESCSEMI_FLD")
	private String produccionesAEscalaSemiComerciales;
	
	@Column(name="ICT_CAPACIDADE_FLD")
	private String capacidadesHabilidades;
	
	@Column(name="ICT_LINEA1_FLD")
	private String lineaInvUno;
	
	@Column(name="ICT_LINEA2_FLD")
	private String lineaInvDos;
	
	@Column(name="ICT_LINEA3_FLD")
	private String lineaInvTres;
	
	@Column(name="ICT_IN_TOT_EST_FLD")
	private String inversionTotal;
	
	@Column(name="ICT_AREA_MCUAD_FLD")
	private String areaEstimada;
	
	@Column(name="ICT_C_AN_MANTO_FLD")
	private String costoAnualMantenimiento;
	
	
	public String getCapacidadesHabilidades() {
		return capacidadesHabilidades;
	}
	
	public void setCapacidadesHabilidades(String capacidadesHabilidades) {
		this.capacidadesHabilidades = capacidadesHabilidades;
	}
	
	public String getLineaInvUno() {
		return lineaInvUno;
	}
	
	public void setLineaInvUno(String lineaInvUno) {
		this.lineaInvUno = lineaInvUno;
	}
	
	public String getLineaInvDos() {
		return lineaInvDos;
	}
	
	public void setLineaInvDos(String lineaInvDos) {
		this.lineaInvDos = lineaInvDos;
	}
	
	public String getLineaInvTres() {
		return lineaInvTres;
	}
	
	public void setLineaInvTres(String lineaInvTres) {
		this.lineaInvTres = lineaInvTres;
	}
	
	public String getInversionTotal() {
		if(this.inversionTotal == null || this.inversionTotal.trim().isEmpty()) {
			return "---";
		} else {
			return "$ " + String.valueOf(this.inversionTotal) + " MXN";
		}
	}
	
	public void setInversionTotal(String inversionTotal) {
		this.inversionTotal = inversionTotal;
	}
	
	public String getAreaEstimada() {
		if(this.areaEstimada == null || this.areaEstimada.trim().isEmpty()) {
			return "--- ";
		} else {
			return String.valueOf(this.areaEstimada);
		}
	}
	
	public void setAreaEstimada(String areaEstimada) {
		this.areaEstimada = areaEstimada;
	}
	
	public String getCostoAnualMantenimiento() {
		if(this.costoAnualMantenimiento == null || this.costoAnualMantenimiento.trim().isEmpty()) {
			return "---";
		} else {
			return "$ " + String.valueOf(this.costoAnualMantenimiento) + " MXN";
		}
	}
	
	public void setCostoAnualMantenimiento(String costoAnualMantenimiento) {
		this.costoAnualMantenimiento = costoAnualMantenimiento;
	}

	public Integer getlaboratorioId() {
		return laboratorioId;
	}

	public void setlaboratorioId(Integer laboratorioId) {
		this.laboratorioId = laboratorioId;
	}
	
	public String getAnalisisYCaracterizacion() {
		if(this.analisisYCaracterizacion == null) {
			return "---";
		}
		return String.valueOf(analisisYCaracterizacion)+" %";
	}
	
	public void setAnalisisYCaracterizacion(String analisisYCaracterizacion) {
		this.analisisYCaracterizacion = analisisYCaracterizacion;
	}
	
	public String getInvestigacionAplicada() {
		if(this.investigacionAplicada == null) {
			return "---";
		}
		return String.valueOf(investigacionAplicada)+" %";
	}
	
	public void setInvestigacionAplicada(String investigacionAplicada) {
		this.investigacionAplicada = investigacionAplicada;
	}
	
	public String getElaboracionPrototipo() {
		if(this.elaboracionPrototipo == null) {
			return "---";
		}
		return String.valueOf(elaboracionPrototipo)+" %";
	}
	
	public void setElaboracionPrototipo(String elaboracionPrototipo) {
		this.elaboracionPrototipo = elaboracionPrototipo;
	}
	
	public String getDocenciaCapacitacion() {
		if(this.docenciaCapacitacion == null) {
			return "---";
		}
		return String.valueOf(docenciaCapacitacion)+" %";
	}
	
	public void setDocenciaCapacitacion(String docenciaCapacitacion) {
		this.docenciaCapacitacion = docenciaCapacitacion;
	}
	
	public String getPruebasCualquierTipo() {
		if(this.pruebasCualquierTipo == null) {
			return "---";
		}
		return pruebasCualquierTipo;
	}
	
	public void setPruebasCualquierTipo(String pruebasCualquierTipo) {
		this.pruebasCualquierTipo = pruebasCualquierTipo;
	}
	
	public String getDesarrolloExperimental() {
		if(this.desarrolloExperimental == null) {
			return "---";
		}
		return  String.valueOf(this.desarrolloExperimental)+" %";
	}
	
	public void setDesarrolloExperimental(String desarrolloExperimental) {
		this.desarrolloExperimental = desarrolloExperimental;
	}
	
	public String getProduccionesEscalaPiloto() {
		if(this.produccionesEscalaPiloto == null) {
			return "---";
		}
		return produccionesEscalaPiloto;
	}
	
	public void setProduccionesEscalaPiloto(String produccionesEscalaPiloto) {
		this.produccionesEscalaPiloto = produccionesEscalaPiloto;
	}
	
	public String getOtros() {
		if(this.otros == null) {
			return "---";
		}
		return String.valueOf(this.otros)+" %";
	}
	
	public void setOtros(String otros) {
		this.otros = otros;
	}
	
	public String getOtrosEspecificados() {
		if(this.otrosEspecificados == null) {
			return "---";
		}
		return otrosEspecificados;
	}
	
	public void setOtrosEspecificados(String otrosEspecificados) {
		this.otrosEspecificados = otrosEspecificados;
	}
	
	public String getInvestigacionCientificaBasica() {
		if(this.investigacionCientificaBasica == null) {
			return "---";
		}
		return String.valueOf(this.investigacionCientificaBasica)+" %";
	}
	
	public void setInvestigacionCientificaBasica(
			String investigacionCientificaBasica) {
		this.investigacionCientificaBasica = investigacionCientificaBasica;
	}
	
	public String getProduccionesAEscalaSemiComerciales() {
		if(this.produccionesAEscalaSemiComerciales == null) {
			return "---";
		}
		return  String.valueOf(this.produccionesAEscalaSemiComerciales)+" %";
	}
	
	public void setProduccionesAEscalaSemiComerciales(
			String produccionesAEscalaSemiComerciales) {
		this.produccionesAEscalaSemiComerciales = produccionesAEscalaSemiComerciales;
	}
	
	public String getDesarrolloProductosProcesosEquipos() {
		if(this.desarrolloProductosProcesosEquipos == null) {
			return "---";
		}
		return  String.valueOf(this.desarrolloProductosProcesosEquipos)+" %";
	}
	
	public void setDesarrolloProductosProcesosEquipos(
			String desarrolloProductosProcesosEquipos) {
		this.desarrolloProductosProcesosEquipos = desarrolloProductosProcesosEquipos;
	}
	
	public int getPorcentajeTotal() {
		return 100;
	}
}
