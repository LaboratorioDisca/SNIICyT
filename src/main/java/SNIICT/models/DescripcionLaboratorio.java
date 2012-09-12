package SNIICT.models;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import SNIICT.utils.Hibernatable;

// SIF.PS_ICT_CONLAB_VW
@Entity
@Table(name="PS_ICT_CONLAB_VW")
public class DescripcionLaboratorio {
	
	public static String tableName="PS_ICT_CONLAB_VW";
	
	@Id
	@Column(name="ICT_NOM_LABOR_FLD")
	private String nombreLaboratorio;
	
	@Column(name="ICT_ID_LABOR_FLD")
	private Integer laboratorioId;
	
	@Column(name="ICT_ENT_FED_FLD")
	private String entidadFederativa;
	
	@Column(name="ICT_ID_SECTOR_FLD")
	private String sectorId;
	
	@Column(name="ICT_ID_INSTITU_FLD")
	private String institucionId;
	
	@Column(name="ICT_DESC_INST_FLD")
	private String descripcionInstitucion;
	
	@Column(name="ICT_CVE_DEPEN_FLD")
	private String dependenciaId;

	@Column(name="ICT_DESC_DEPEN_FLD")
	private String descripcionDependencia;
	
	@Column(name="ICT_CVE_SECTOR_FLD")
	private String claveSector;
	
	@Column(name="ICT_DES_SECTOR_FLD")
	private String descripcionSector;
	
	@Column(name="ICT_ID_AREA_FLD")
	private String areaId;
	
	@Column(name="ICT_DESCR_AREA_FLD")
	private String descripcionArea;
	
	@Column(name="ICT_CVE_DISCIP_FLD")
	private String disciplinaId;
	
	@Column(name="ICT_DESCR_DISC_FLD")
	private String descripcionDisciplina;
	
	@Column(name="ICT_CVESDICIP_FLD")
	private String subdisciplinaId;
	
	@Column(name="ICT_DESC1_FLD")
	private String descripcionSubdisciplina;
	
	@Column(name="ICT_PER_SERV_FLD")
	private String permiteServicio;
	
	public static List<DescripcionLaboratorio> buscaPorParametros(Map<String, String> parametros) {
        Session s = Hibernatable.getSession();
		s.beginTransaction();
		
		String mainQuery = "SELECT * FROM "+tableName+" ";

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
		
		if(!restrictions.isEmpty())
			mainQuery+="WHERE "+restrictions.substring(3);

		SQLQuery criteriaQuery = s.createSQLQuery(mainQuery).addEntity(DescripcionLaboratorio.class);
				
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
		
		List<DescripcionLaboratorio> resultados = criteriaQuery.list();
		
		s.getTransaction().commit();
		return resultados;
	}
	
	public void setPermiteServicio(String permiteServicio) {
		this.permiteServicio = permiteServicio;
	}
	
	public boolean getPermiteServicio() {
		return this.permiteServicio.equals("S");
	}
	
	public void setDescripcionSector(String desc) {
		this.descripcionSector = desc;
	}
	
	public String getDescripcionSector() {
		return this.descripcionSector;
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
		return descripcionInstitucion;
	}

	public void setDescripcionInstitucion(String descripcionInstitucion) {
		this.descripcionInstitucion = descripcionInstitucion;
	}

	public String getDescripcionDependencia() {
		return descripcionDependencia;
	}

	public void setDescripcionDependencia(String descripcionDependencia) {
		this.descripcionDependencia = descripcionDependencia;
	}

	public String getClaveSector() {
		return claveSector;
	}

	public void setClaveSector(String claveSector) {
		this.claveSector = claveSector;
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

	public String getDisciplinaId() {
		return disciplinaId;
	}

	public void setDisciplinaId(String disciplinaId) {
		this.disciplinaId = disciplinaId;
	}

	public String getDescripcionDisciplina() {
		return descripcionDisciplina;
	}

	public void setDescripcionDisciplina(String descripcionDisciplina) {
		this.descripcionDisciplina = descripcionDisciplina;
	}

	public String getSubdisciplinaId() {
		return subdisciplinaId;
	}

	public void setSubdisciplinaId(String subdisciplinaId) {
		this.subdisciplinaId = subdisciplinaId;
	}

	public String getDependenciaId() {
		return dependenciaId;
	}

	public void setDependenciaId(String dependenciaId) {
		this.dependenciaId = dependenciaId;
	}

	public String getDescripcionSubdisciplina() {
		return descripcionSubdisciplina;
	}

	public void setDescripcionSubdisciplina(String descripcionSubdisciplina) {
		this.descripcionSubdisciplina = descripcionSubdisciplina;
	}
	
	public Integer getLaboratorioId() {
		return this.laboratorioId;
	}
	
	public void setLaboratorioId(Integer laboratorioId) {
		this.laboratorioId= laboratorioId;
	}
	
	public String getNombreLaboratorio() {
		return this.nombreLaboratorio;
	}
	
	public void setNombreLaboratorio(String nombreLaboratorio) {
		this.nombreLaboratorio= nombreLaboratorio;
	}
	
	public String getEntidadFederativa() {
		return this.entidadFederativa;
	}
	
	public void setEntidadFederativa(String entidadFederativa) {
		this.entidadFederativa = entidadFederativa;
	}
}

