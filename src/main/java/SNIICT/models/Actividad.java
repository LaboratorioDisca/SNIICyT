package SNIICT.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SIF.PS_ICT_ACTIVID_TBL")
public class Actividad {
	
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
		return inversionTotal;
	}
	public void setInversionTotal(String inversionTotal) {
		this.inversionTotal = inversionTotal;
	}
	public String getAreaEstimada() {
		return areaEstimada;
	}
	public void setAreaEstimada(String areaEstimada) {
		this.areaEstimada = areaEstimada;
	}
	public String getCostoAnualMantenimiento() {
		return costoAnualMantenimiento;
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
		return analisisYCaracterizacion;
	}
	public void setAnalisisYCaracterizacion(String analisisYCaracterizacion) {
		this.analisisYCaracterizacion = analisisYCaracterizacion;
	}
	public String getInvestigacionAplicada() {
		return investigacionAplicada;
	}
	public void setInvestigacionAplicada(String investigacionAplicada) {
		this.investigacionAplicada = investigacionAplicada;
	}
	public String getElaboracionPrototipo() {
		return elaboracionPrototipo;
	}
	public void setElaboracionPrototipo(String elaboracionPrototipo) {
		this.elaboracionPrototipo = elaboracionPrototipo;
	}
	public String getDocenciaCapacitacion() {
		return docenciaCapacitacion;
	}
	public void setDocenciaCapacitacion(String docenciaCapacitacion) {
		this.docenciaCapacitacion = docenciaCapacitacion;
	}
	public String getPruebasCualquierTipo() {
		return pruebasCualquierTipo;
	}
	public void setPruebasCualquierTipo(String pruebasCualquierTipo) {
		this.pruebasCualquierTipo = pruebasCualquierTipo;
	}
	public String getDesarrolloExperimental() {
		return desarrolloExperimental;
	}
	public void setDesarrolloExperimental(String desarrolloExperimental) {
		this.desarrolloExperimental = desarrolloExperimental;
	}
	public String getProduccionesEscalaPiloto() {
		return produccionesEscalaPiloto;
	}
	public void setProduccionesEscalaPiloto(String produccionesEscalaPiloto) {
		this.produccionesEscalaPiloto = produccionesEscalaPiloto;
	}
	public String getOtros() {
		return otros;
	}
	public void setOtros(String otros) {
		this.otros = otros;
	}
	public String getOtrosEspecificados() {
		return otrosEspecificados;
	}
	public void setOtrosEspecificados(String otrosEspecificados) {
		this.otrosEspecificados = otrosEspecificados;
	}
	public String getInvestigacionCientificaBasica() {
		return investigacionCientificaBasica;
	}
	public void setInvestigacionCientificaBasica(
			String investigacionCientificaBasica) {
		this.investigacionCientificaBasica = investigacionCientificaBasica;
	}
	public String getProduccionesAEscalaSemiComerciales() {
		return produccionesAEscalaSemiComerciales;
	}
	public void setProduccionesAEscalaSemiComerciales(
			String produccionesAEscalaSemiComerciales) {
		this.produccionesAEscalaSemiComerciales = produccionesAEscalaSemiComerciales;
	}
	public String getDesarrolloProductosProcesosEquipos() {
		return desarrolloProductosProcesosEquipos;
	}
	public void setDesarrolloProductosProcesosEquipos(
			String desarrolloProductosProcesosEquipos) {
		this.desarrolloProductosProcesosEquipos = desarrolloProductosProcesosEquipos;
	}
	
	public int getPorcentajeTotal() {
		return 100;
	}
}
