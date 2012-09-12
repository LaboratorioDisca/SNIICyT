package SNIICT.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ParameterAware;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import SNIICT.models.*;
import SNIICT.utils.Hibernatable;

/**
 * Esta acci�n se encarga de desplegar el formulario de busqueda
 * El c�digo de la vista asociada a esta acci�n est� en el archivo busqueda.jsp
 */

@Results({
	  @Result(name="success", location="/WEB-INF/Layout.jsp"),
	  @Result(name="success-inst", location="catalogoInst.jsp"),
	  @Result(name="success-depen", location="catalogoDepen.jsp"),
	  @Result(name="success-subdepen", location="catalogoSubdepen.jsp"),
	  @Result(name="success-disc", location="catalogoDisc.jsp"),
	  @Result(name="success-subdisc", location="catalogoSubdisc.jsp")
})
public class BusquedaAction extends PartialAwareAction implements ParameterAware  {

	private ArrayList<Entidad> entidades;
	private ArrayList<Sector> sectores;
	private ArrayList<Institucion> instituciones;
	private ArrayList<Dependencia> dependencias;
	private ArrayList<Subdependencia> subdependencias;

	private ArrayList<AreaConocimiento> areas;
	private ArrayList<Disciplina> disciplinas;
	private ArrayList<Subdisciplina> subdisciplinas;
	private Map<String, String[]> parameters;
		
	private static final long serialVersionUID = 1L;
	
	public void setEntidades(ArrayList<Entidad> entidades) {
		this.entidades = entidades;
	}
	public ArrayList<Entidad> getEntidades() {
		return entidades;
	}
	
	public void setAreas(ArrayList<AreaConocimiento> areas) {
		this.areas = areas;
	}
	public ArrayList<AreaConocimiento> getAreas() {
		return areas;
	}
	
	public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	public ArrayList<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	
	public ArrayList<Subdisciplina> getSubdisciplinas() {
		return subdisciplinas;
	}
	public void setSubdisciplinas(ArrayList<Subdisciplina> subdisciplinas) {
		this.subdisciplinas = subdisciplinas;
	}
	
	public ArrayList<Institucion> getInstituciones() {
		return instituciones;
	}
	
	public  void setInstituciones(ArrayList<Institucion> instituciones) {
		this.instituciones = instituciones;
	}
	
	public void setParameters(Map<String, String[]> arg0) {
		this.parameters = arg0;		
	}
	
	public Map<String, String[]> getParameters() {
		return this.parameters;
	}
	public ArrayList<Sector> getSectores() {
		return sectores;
	}
	public void setSectores(ArrayList<Sector> sectores) {
		this.sectores = sectores;
	}
	public ArrayList<Subdependencia> getSubdependencias() {
		return subdependencias;
	}
	public void setSubdependencias(ArrayList<Subdependencia> subdependencias) {
		this.subdependencias = subdependencias;
	}
	
	public ArrayList<Dependencia> getDependencias() {
		return dependencias;
	}
	
	public void setDependencias(ArrayList<Dependencia> dependencias) {
		this.dependencias = dependencias;
	}
	
	/*
	 * Genera el formulario de búsqueda con campos iniciales
	 */
	@SuppressWarnings("unchecked")
	public String execute() {
		this.setRenderPartial("busqueda.jsp");
		entidades = new ArrayList<Entidad>();
		areas = new ArrayList<AreaConocimiento>();
		sectores = new ArrayList<Sector>();
		
		Session s = Hibernatable.getSession();
		s.beginTransaction();
		
		List<Entidad> entidadesC = (List<Entidad>) s.createSQLQuery("SELECT * FROM PS_STATE_TBL WHERE COUNTRY = 'MEX'").
		addEntity(Entidad.class).list();
		entidades.addAll(entidadesC);
		
		List<Sector> sectoresC = s.createCriteria(Sector.class).list();
		sectores.addAll(sectoresC);
		
		this.cargaAreas(s);
		
		s.getTransaction().commit();
				
		return SUCCESS;
	}
	
    // Todos los métodos siguientes se encargan de cargar categorias en el formulario de búsqueda
    // Desde el lado del cliente, estas se van desplegando vía ajax
	
	@Action("catalogoInstituciones")
	public String cargaInstituciones() {
		String sectorId = this.getParameters().get("sector")[0];
		setInstituciones(new ArrayList<Institucion>());
	
		String query = "SELECT * FROM RODRIGO.PS_ICT_BUS_INST_VW WHERE ICT_ID_SECTOR_FLD = :sectorId ORDER BY ICT_DESC_INST_FLD ASC";
		execute(query, Institucion.class, "sectorId", sectorId, instituciones);
		
		return SUCCESS+"-inst";
	}
	
	@Action("catalogoDependencias")
	public String cargaDependencias() {
		String institucionId = this.getParameters().get("institucion")[0];
		setDependencias(new ArrayList<Dependencia>());
		
		String query = "SELECT * FROM RODRIGO.PS_ICT_BUS_DEPE_VW WHERE ICT_ID_INSTITU_FLD = :instId ORDER BY ICT_DESC_DEPEN_FLD ASC";
		execute(query, Dependencia.class, "instId", institucionId, dependencias);
		
		return SUCCESS+"-depen";
	}
	
	@Action("catalogoSubdependencias")
	public String cargaSubdependencias() {
		String dependenciaId = this.getParameters().get("dependencia")[0];
		setSubdependencias(new ArrayList<Subdependencia>());
		
		String query = "SELECT * FROM RODRIGO.PS_ICT_BUS_SECT_VW WHERE ICT_CVE_DEPEN_FLD = :depenId ORDER BY ICT_CVE_SECTOR_FLD ASC";
		execute(query, Subdependencia.class, "depenId", dependenciaId, subdependencias);
		
		return SUCCESS+"-subdepen";
	}
	
	@SuppressWarnings("unchecked")
	private void execute(String query, Class<?> klass, String queryField, String value, ArrayList<?> list) {
		Session s = Hibernatable.getSession();
		s.beginTransaction();

		@SuppressWarnings("rawtypes")
		List results = s.createSQLQuery(query).addEntity(klass).setString(queryField, value).list();
		list.addAll(results);

		s.getTransaction().commit();
	}
	
	
	@Action("catalogoDisciplinas")
	public String cargaDisciplinas() {
		
		String areaId = this.getParameters().get("area")[0];
		setDisciplinas(new ArrayList<Disciplina>());
		
		String query = "SELECT * FROM PS_CYT_DISCIP_TBL WHERE CTY_CVE_CAMPO = :areaId ORDER BY CYT_DESCR_DISC ASC";
		execute(query, Disciplina.class, "areaId", areaId, disciplinas);
		
		return SUCCESS+"-disc";
	}
	
	@Action("catalogoSubdisciplinas")
	public String cargaSubdisciplinas() {
		
		int areaId = Integer.parseInt(this.getParameters().get("area")[0]);
		int disciplinaId = 0;
		
		if(this.getParameters().containsKey("disciplina"))
			disciplinaId = Integer.parseInt(this.getParameters().get("disciplina")[0]);

		setSubdisciplinas(new ArrayList<Subdisciplina>());
		
		Session s = Hibernatable.getSession();
		s.beginTransaction();
			
		if(disciplinaId != 0)
			this.cargaSubdisciplinas(s, areaId, disciplinaId);
		
		s.getTransaction().commit();
		
		return SUCCESS+"-subdisc";
	}
	
	@SuppressWarnings("unchecked")
	protected void cargaSubdisciplinas(Session s, int areaId, int disciplinaId) {
		Query query = s.createSQLQuery("SELECT * FROM \"PS_CYT_SUBDISC_TBL\" WHERE \"CTY_CVE_CAMPO\" = :areaId AND \"CYT_CVE_DISCIPLINA\" = :disciplinaId ORDER BY CYT_DESCR_DISC ASC").
		addEntity(Subdisciplina.class).setInteger("areaId", areaId).setInteger("disciplinaId", disciplinaId);
		
		for(Subdisciplina subdisciplina : (List<Subdisciplina>) query.list()) {
			subdisciplinas.add(subdisciplina);
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void cargaAreas(Session s) {
		List<AreaConocimiento> areasC = s.createCriteria(AreaConocimiento.class).addOrder( Order.asc("nombre")).list();
		areas.addAll(areasC);
	}
	
}
