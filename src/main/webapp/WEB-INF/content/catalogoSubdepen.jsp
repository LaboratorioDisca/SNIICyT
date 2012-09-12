<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="field">
	<label><s:text name="busqueda.campo.subdependencia"/>:</label>
	<s:select name="subdependencia" list="subdependencias" listKey="subdependenciaId" listValue="descripcion" headerKey="" headerValue="Seleccionar Subdependencia"/><br/>
</div>

<s:div id="subdependSel"></s:div>  
