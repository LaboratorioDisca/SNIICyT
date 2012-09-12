<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="field">
	<label><s:text name="busqueda.campo.institucion"/>:</label>
	<s:select name="institucion" list="instituciones" listKey="institucionId" listValue="descripcion" headerKey="" headerValue="Seleccionar Institución"/>
</div>

<s:div id="dependSel"></s:div>  
