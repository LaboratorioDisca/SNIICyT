<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="field">
	<label><s:text name="busqueda.campo.disciplina"/>:</label>
	<s:select name="disciplina" list="disciplinas" listKey="id" listValue="descripcion" headerKey="" headerValue="Seleccionar Disciplina"/><br/>
</div>

<s:div id="subdisciplinasSel"></s:div>  

