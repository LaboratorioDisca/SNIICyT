<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="field">
	<label><s:text name="busqueda.campo.dependencia"/>:</label>
	<s:select name="dependencia" list="dependencias" listKey="id" listValue="descripcion" headerKey="" headerValue="Seleccionar Dependencia"/><br/>
</div>

<s:div id="subdependSel"></s:div>  
