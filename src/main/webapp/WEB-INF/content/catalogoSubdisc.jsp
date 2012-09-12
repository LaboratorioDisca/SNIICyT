<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="field">
	<label><s:text name="busqueda.campo.subdisciplina"/>:</label>
	<s:select name="subdisciplina" list="subdisciplinas" listKey="id" listValue="descripcion" headerKey="" headerValue="Seleccionar Subdisciplina"/>
</div>