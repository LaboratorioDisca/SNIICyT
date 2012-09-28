package SNIICT.actions;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ParameterAware;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import SNIICT.models.DescripcionEquipo;
import SNIICT.models.DescripcionLaboratorio;
import SNIICT.models.EquipoLaboratorio;
import SNIICT.utils.Hibernatable;

/*
 * Este controlador contiene acciones y métodos que realizan la búsqueda basada en 
 * los parámetros recibidos y generan una vista para el listado de resultados
 */
@Results({
	@Result(name="success", location="/WEB-INF/Layout.jsp")
})
public class ResultadosAction extends PartialAwareAction implements ParameterAware {

	private static final long serialVersionUID = 1L;
	private ArrayList<DescripcionEquipo> equipos;
	private ArrayList<DescripcionLaboratorio> laboratorios;
	private boolean busquedaConEquipo = false;
	
	private Map<String, String[]> parameters;
	
	// Lista de columnas a desplegar en el resultado de la búsqueda
	private String estadoStr="desplegarEstado";
	private String dependenciaStr="desplegarDependencia";
	private String subdependenciaStr="desplegarSubdependencia";
	private String disciplinaStr="desplegarDisciplina";
	private String subdisciplinaStr="desplegarSubdisciplina";
	private String permisosStr="desplegarPermisos";
	private String nombreLaboratorioStr="desplegarNombreLaboratorio";
	private Map<String, Boolean> camposSeleccionados; 

	/*
	 * Se encarga de ejecutar la búsqueda a partir de los parámetros de búsqueda recibidos
	 */
	@SuppressWarnings("unchecked")
	@Action("buscar")
	public String execute() {
		this.setRenderPartial("resultados.jsp");
		
		// Activa ó desactiva columnas de la tabla para ser mostradas dependiendo
		// la elección del usuario en el panel de búsqueda
		this.camposSeleccionados = new HashMap<String, Boolean>();
		String[] listaDeCamposSeleccionables = {estadoStr,
				dependenciaStr, 
				subdependenciaStr, 
				disciplinaStr, 
				subdisciplinaStr,
				permisosStr,
				nombreLaboratorioStr};
		
		for(String campoSeleccionable : listaDeCamposSeleccionables)  {
			if(this.getParameters().containsKey(campoSeleccionable)) {
				this.camposSeleccionados.put(campoSeleccionable, true);
	        }
		}
		
		// Averiguar si el usuario proporcionó una cadena de búsqueda por equipo
        Map<String, String> parametros = this.extraeParametros();
		
        if(this.busquedaConEquipo) {
    		this.setEquipos(new ArrayList<DescripcionEquipo>());
        	equipos.addAll(DescripcionEquipo.buscaPorParametros(parametros));
        } else {
    		this.setLaboratorios(new ArrayList<DescripcionLaboratorio>());
    		laboratorios.addAll(DescripcionLaboratorio.buscaPorParametros(parametros));
        }
        
        return SUCCESS;
	}
	
	/*
	 * Se encarga de extraer los parámetros recibidos desde el campo de búsqueda
	 */
	public Map<String, String> extraeParametros() {
		HashMap<String, String> parameterList = new HashMap<String, String>();
		
        String equipoNombre = this.parameters.get("equipoNombre")[0];
        
        setBusquedaConEquipo(Boolean.parseBoolean(this.parameters.get("equipoNombreFueDado")[0]));
        
        parameterList.put("equipoNombre", equipoNombre);
        if(parametroIncluidoYNoVacio("entidad"))
        	parameterList.put("entidad", this.parameters.get("entidad")[0]);
        if(parametroIncluidoYNoVacio("sector"))
        	parameterList.put("sector", this.parameters.get("sector")[0]);
        if(parametroIncluidoYNoVacio("area"))
        	parameterList.put("area", this.parameters.get("area")[0]);
        
        if(parametroIncluidoYNoVacio("servicio"))
        	parameterList.put("servicio", this.parameters.get("servicio")[0]);
        if(parametroIncluidoYNoVacio("institucion"))
        	parameterList.put("institucion", this.parameters.get("institucion")[0]);
        if(parametroIncluidoYNoVacio("dependencia"))
        	parameterList.put("dependencia", this.parameters.get("dependencia")[0]);
        if(parametroIncluidoYNoVacio("disciplina"))
        	parameterList.put("disciplina", this.parameters.get("disciplina")[0]);
        if(parametroIncluidoYNoVacio("subdisciplina"))
        	parameterList.put("subdisciplina", this.parameters.get("subdisciplina")[0]);
        
        return parameterList;
	}
	
	public boolean parametroIncluidoYNoVacio(String param) {
		return this.parameters.containsKey(param) && !this.parameters.get(param)[0].isEmpty();
	}
	
	public Map<String, String[]> getParameters() {
		return this.parameters;
	}
	
	public void setParameters(Map<String, String[]> arg0) {
		this.parameters = arg0;
	}

	public boolean isBusquedaConEquipo() {
		return busquedaConEquipo;
	}

	public void setBusquedaConEquipo(boolean busquedaConEquipo) {
		this.busquedaConEquipo = busquedaConEquipo;
	}

	public ArrayList<DescripcionEquipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(ArrayList<DescripcionEquipo> equipos) {
		this.equipos = equipos;
	}
	
	public ArrayList<DescripcionLaboratorio> getLaboratorios() {
		return laboratorios;
	}

	public void setLaboratorios(ArrayList<DescripcionLaboratorio> laboratorios) {
		this.laboratorios = laboratorios;
	}
	
	public Map<String, Boolean> getCamposSeleccionados() {
		return this.camposSeleccionados;
	}
	
	public boolean isDebeMostrarDependencia() {
		return this.camposSeleccionados.containsKey(dependenciaStr);
	}
	
	public boolean isDebeMostrarSubdependencia() {
		return this.camposSeleccionados.containsKey(subdependenciaStr);
	}
	
	public boolean isDebeMostrarDisciplina() {
		return this.camposSeleccionados.containsKey(disciplinaStr);
	}
	
	public boolean isDebeMostrarSubdisciplina() {
		return this.camposSeleccionados.containsKey(subdisciplinaStr);
	}
	
	public boolean isDebeMostrarPermisos() {
		return this.camposSeleccionados.containsKey(permisosStr);
	}
	
	public boolean isDebeMostrarNombreDeLaboratorio() {
		return this.camposSeleccionados.containsKey(nombreLaboratorioStr);
	}
	
	public boolean isDebeMostrarEstado() {
		return this.camposSeleccionados.containsKey(estadoStr);
	}
}
