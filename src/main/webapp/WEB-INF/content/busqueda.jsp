<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="content centered">
<div class="centrado small" id="search">
	
	<h3><b>
		<span id="nombre-equipo-title">
			<s:text name="busqueda.por_equipo"/>
		</span>
		<span id="nombre-lab-title" class="hidden">
			<s:text name="busqueda.por_laboratorio"/>
		</span>
		</b> 
	</h3>
	<span class="cambiar-busqueda"><b>(Cambiar a <a href="javascript:void(0);" id="nombre-lab-toggle"><s:text name="busqueda.por_laboratorio"/></a>
	 <a href="javascript:void(0);" class="hidden" id="nombre-equipo-toggle"><s:text name="busqueda.por_equipo"/></a>)</b></span>
	<s:form action="buscar">
				
		<div class="text field nombre-de-equipo-fld">
			<label><s:text name="busqueda.campo.equipo"/>:</label>
			<s:textfield name="equipoNombre" /><br/>
		</div>
		<div class="field">
			<label><s:text name="busqueda.campo.entidad"/>:</label>
			<s:select name="entidad" list="entidades" listKey="estado" listValue="descripcion" headerKey="" headerValue="Seleccionar Estado"/><br/>
		</div>
		
		<div class="field">
			<label><s:text name="busqueda.campo.sector"/>:</label>
			<s:select name="sector" list="sectores" listKey="sectorId" listValue="descripcion" headerKey="" headerValue="Seleccionar Sector"/><br/>
		</div>
		<s:div id="dinInst"></s:div>
		<div class="field">
			<label><s:text name="busqueda.campo.area"/>:</label>
			<s:select name="area" list="areas" listKey="id" listValue="nombre" headerKey="" headerValue="Seleccionar Área de Conocimiento"/><br/>
		</div>
		<s:div id="dinDisc"></s:div>
		<div class="field">
			<label><s:text name="busqueda.campo.servicio"/>:</label>
			<input id="buscar_servicioSi" type="radio" value="S" name="servicio">
			<span>Si</span>
			<input id="buscar_servicioNo" type="radio" value="N" name="servicio">
			<span>No</span>
			<input id="buscar_servicioNo" type="radio" value="" name="servicio">
			<span>No reelevante</span>
		</div>
			
		<b><s:text name="busqueda.campos.seleccion.adicionales"/>:</b>
		<div class="field column-selection">
			
			<input type="checkbox" name="desplegarEstado">
			<span><s:text name="busqueda.campo.estado"/></span>
			
			<input type="checkbox" name="desplegarDependencia">
			<span><s:text name="busqueda.campo.dependencia"/></span>
			
			<input type="checkbox" name="desplegarSubdependencia">
			<span><s:text name="busqueda.campo.subdependencia"/></span>
			
			<input type="checkbox" name="desplegarDisciplina">
			<span><s:text name="busqueda.campo.disciplina"/></span>
			
			<input type="checkbox" name="desplegarSubdisciplina">
			<span><s:text name="busqueda.campo.subdisciplina"/></span>
			
			<input type="checkbox" name="desplegarPermisos">
			<span><s:text name="busqueda.campo.otorga_permiso"/></span>
			
			<input type="checkbox" checked="checked" name="desplegarNombreLaboratorio" class="checked-default">
			<span><s:text name="busqueda.campo.nombre_laboratorio"/></span>
		</div>
		<br>
		<input id="search-submit" type="submit" value="Buscar">
	</s:form>
</div>
</div>