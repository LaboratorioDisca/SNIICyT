package SNIICT.models;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import SNIICT.utils.Hibernatable;

// SIF.PS_ICT_CONEQ_VW
@Entity
@Table(name="SIF.PS_ICT_CONEQ_VW")
public class DescripcionEquipo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static String tableName="SIF.PS_ICT_CONEQ_VW";
	
	@Column(name="ICT_ID_LABOR_FLD") 
	private Integer laboratorioId;
	
	@Id
	@Column(name="ICT_ID_EQUIPO_FLD")
	private Integer equipoId;
	
	@Column(name="ICT_ENT_FED_FLD")
	private String entidadId;
	
	@Column(name="ICT_CVE_SECTOR_FLD")
	private String sectorClave;
	// nuevos antes
	
	@Column(name="ICT_DESC_FLD")
	private String nombreEquipo;
	
	// Aqui
	@Column(name="ICT_NOM_LABOR_FLD")
	private String nombreLaboratorio;
	
	@Column(name="ICT_ID_SECTOR_FLD")
	private String sectorId;
	
	@Column(name="ICT_ID_INSTITU_FLD")
	private String institucionId;
	
	@Column(name="ICT_DESC_INST_FLD")
	private String descripcionInstitucion;
	
	@Column(name="ICT_CVE_DEPEN_FLD")
	private String dependenciaClave;

	@Column(name="ICT_DESC_DEPEN_FLD")
	private String descripcionDependencia;
	
	@Column(name="ICT_ID_AREA_FLD")
	private String areaId;
	@Column(name="ICT_DESCR_AREA_FLD")
	private String descripcionArea;
	
	@Column(name="ICT_CVE_DISCIP_FLD")
	private String disciplinaClave;
	
	@Column(name="ICT_DESCR_DISC_FLD")
	private String descripcionDisciplina;
		
	@Column(name="ICT_CVESDICIP_FLD")
	private String subdisciplinaClave;
	
	@Column(name="ICT_DESC1_FLD")
	private String descripcionSubdisciplina;
	
	@Column(name="ICT_PER_SERV_FLD")
	private String servicioDisponible;
	
	@Column(name="ICT_DES_SECTOR_FLD")
	private String descripcionSector;

	public static List<DescripcionEquipo> buscaPorParametros(Map<String, String> parametros) {
        Session s = Hibernatable.getSession();
		s.beginTransaction();
		
		String mainQuery = "SELECT * FROM "+tableName+" ";

		String equipoNombreQ = "ICT_DESC_FLD LIKE upper('%"+parametros.get("equipoNombre")+"%')";
		String entidadQ = "ICT_ENT_FED_FLD = :entidadId ";
		String sectorQ = "ICT_ID_SECTOR_FLD = :sectorId ";
		String areaQ = "ICT_ID_AREA_FLD = :areaId ";
		String servicioQ = "ICT_PER_SERV_FLD = :servicioDisp ";
		String institucionQ = "ICT_ID_INSTITU_FLD = :institucionId ";
		String dependenciaQ = "ICT_CVE_DEPEN_FLD = :dependenciaClave ";
		String disciplinaQ = "ICT_CVE_DISCIP_FLD = :disciplinaClave ";
		String subdisciplinaQ = "ICT_CVESDICIP_FLD = :subdisciplinaClave ";

		String restrictions="";
		
		if(parametros.containsKey("sector"))
			restrictions+="AND "+sectorQ;
		if(parametros.containsKey("entidad"))
			restrictions+="AND "+entidadQ;
		if(parametros.containsKey("area"))
			restrictions+="AND "+areaQ;
		if(parametros.containsKey("servicio"))
			restrictions+="AND "+servicioQ;
		if(parametros.containsKey("institucion"))
			restrictions+="AND "+institucionQ;
		if(parametros.containsKey("dependencia"))
			restrictions+="AND "+dependenciaQ;
		if(parametros.containsKey("disciplina"))
			restrictions+="AND "+disciplinaQ;
		if(parametros.containsKey("subdisciplina"))
			restrictions+="AND "+subdisciplinaQ;

		restrictions+="AND "+equipoNombreQ;
		
		if(!restrictions.isEmpty())
			mainQuery+="WHERE "+restrictions.substring(3);

		SQLQuery criteriaQuery = s.createSQLQuery(mainQuery).addEntity(DescripcionEquipo.class);
				
		if(parametros.containsKey("sector"))
			criteriaQuery.setString("sectorId", parametros.get("sector"));
		if(parametros.containsKey("entidad"))
			criteriaQuery.setString("entidadId", parametros.get("entidad"));
		if(parametros.containsKey("area"))
			criteriaQuery.setString("areaId", parametros.get("area"));
		if(parametros.containsKey("servicio"))
			criteriaQuery.setString("servicioDisp", parametros.get("servicio"));
		if(parametros.containsKey("institucion"))
			criteriaQuery.setString("institucionId", parametros.get("institucion"));
		if(parametros.containsKey("dependencia"))
			criteriaQuery.setString("dependenciaClave", parametros.get("dependencia"));
		if(parametros.containsKey("disciplina"))
			criteriaQuery.setString("disciplinaClave", parametros.get("disciplina"));
		if(parametros.containsKey("subdisciplina"))
			criteriaQuery.setString("subdisciplinaClave", parametros.get("subdisciplina"));
		
		List<DescripcionEquipo> resultados = criteriaQuery.list();
		
		s.getTransaction().commit();
		return resultados;
	}
	
	public String getNombreLaboratorio() {
		if(nombreLaboratorio == null)
			return "---";
		else
			return nombreLaboratorio;
	}

	public void setNombreLaboratorio(String nombreLaboratorio) {
		this.nombreLaboratorio = nombreLaboratorio;
	}
	
	public String getSectorId() {
		return sectorId;
	}

	public void setSectorId(String sectorId) {
		this.sectorId = sectorId;
	}

	public String getInstitucionId() {
		return institucionId;
	}

	public void setInstitucionId(String institucionId) {
		this.institucionId = institucionId;
	}

	public String getDescripcionInstitucion() {
		if(descripcionInstitucion == null)
			return "---";
		else
			return descripcionInstitucion;
	}

	public void setDescripcionInstitucion(String descripcionInstitucion) {
		this.descripcionInstitucion = descripcionInstitucion;
	}

	public String getDescripcionDependencia() {
		if(descripcionInstitucion == null)
			return "---";
		else
			return descripcionDependencia;
	}

	public void setDescripcionDependencia(String descripcionDependencia) {
		this.descripcionDependencia = descripcionDependencia;
	}

	public String getDescripcionSector() {
		if(descripcionSector == null)
			return "---";
		else
			return descripcionSector;
	}

	public void setDescripcionSector(String descripcionSector) {
		this.descripcionSector = descripcionSector;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getDescripcionArea() {
		return descripcionArea;
	}

	public void setDescripcionArea(String descripcionArea) {
		this.descripcionArea = descripcionArea;
	}

	public String getDisciplinaClave() {
		return disciplinaClave;
	}

	public void setDisciplinaClave(String disciplinaClave) {
		this.disciplinaClave = disciplinaClave;
	}

	public String getDescripcionDisciplina() {
		if(descripcionDisciplina == null)
			return "---";
		else
			return descripcionDisciplina;
	}

	public void setDescripcionDisciplina(String descripcionDisciplina) {
		this.descripcionDisciplina = descripcionDisciplina;
	}

	public String getSubdisciplinaClave() {
		return subdisciplinaClave;
	}

	public void setSubdisciplinaClave(String subdisciplinaClave) {
		this.subdisciplinaClave = subdisciplinaClave;
	}

	public String getDependenciaClave() {
		return dependenciaClave;
	}

	public void setDependenciaClave(String dependenciaClave) {
		this.dependenciaClave = dependenciaClave;
	}
	
	public String getSectorClave() {
		return sectorClave;
	}

	public void setSectorClave(String sectorClave) {
		this.sectorClave = dependenciaClave;
	}

	public String getDescripcionSubdisciplina() {
		if(this.descripcionSubdisciplina == null)
			return "---";
		else
			return descripcionSubdisciplina;
	}

	public void setDescripcionSubdisciplina(String descripcionSubdisciplina) {
		this.descripcionSubdisciplina = descripcionSubdisciplina;
	}

	public String getServicioDisponible() {
		return servicioDisponible;
	}

	public void setServicioDisponible(String servicioDisponible) {
		this.servicioDisponible = servicioDisponible;
	}

	public String getEntidadId() {
		return entidadId;
	}

	public void setEntidadId(String entidadId) {
		this.entidadId = entidadId;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}
	
	public Integer getLaboratorioId() {
		return this.laboratorioId;
	}
	
	public void setLaboratorioId(Integer laboratorioId) {
		this.laboratorioId= laboratorioId;
	}
	
	public Integer getEquipoId() {
		return this.equipoId;
	}
	
	public void setEquipoId(Integer equipoId) {
		this.equipoId = equipoId;
	}
	
}
