<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="content">
<div id="search-results" class="medium centrado results-table">

	<h3><s:text name="sistema.busqueda.resultados.titulo"/></h3>	
	<br/>
	<table class="display" id="tDatosMuestreo">
		<s:if test="%{!equipos.isEmpty}">
			<thead>
			<tr>
				<th>Equipo</th>
				<th>Institución</th>
				<th>Área de Conocimiento</th>
				<s:if test="%{debeMostrarEstado}">
					<th>Estado</th>
				</s:if>
				<s:if test="%{debeMostrarNombreDeLaboratorio}">
					<th>Nombre del Laboratorio</th>
				</s:if>	
				<s:if test="%{debeMostrarDependencia}">
					<th>Dependencia</th>
				</s:if>
					
				<s:if test="%{debeMostrarSubdependencia}">
					<th>Subdependencia</th>
				</s:if>
					
				<s:if test="%{debeMostrarDisciplina}">
					<th>Disciplina</th>
				</s:if>
					
				<s:if test="%{debeMostrarSubdisciplina}">
					<th>Subdisciplina</th>
				</s:if>
				<th>Detalles de Equipo</th>
			</tr>
			</thead>
			<s:iterator value="equipos">
				<tr>
					<td><s:property value="nombreEquipo" /></td>
					<td><s:property value="descripcionInstitucion" /></td>
					<td><s:property value="descripcionArea" /></td>
					
					<s:if test="%{debeMostrarEstado}">
						<td><s:property value="nombreLaboratorio" /></td>
					</s:if>
					<s:if test="%{debeMostrarNombreDeLaboratorio}">
						<td><s:property value="entidadId" /></td>
					</s:if>
					
					<s:if test="%{debeMostrarDependencia}">
						<td>
							<s:if test="%{descripcionDependencia==null}">
								<b><s:text name="resultados.no_disponible"/></b>
							</s:if>
							<s:else>
								<s:property value="descripcionDependencia" /><br>	
							</s:else>
						</td>
					</s:if>
					
					<s:if test="%{debeMostrarSubdependencia}">
						<td>
							<s:if test="%{descripcionSector==null}">
								<b><s:text name="resultados.no_disponible"/></b>
							</s:if>
							<s:else>
								<s:property value="descripcionSector" /><br>	
							</s:else>
						</td>
					</s:if>
					
					<s:if test="%{debeMostrarDisciplina}">
						<td><s:property value="descripcionDisciplina" /></td>
					</s:if>
					
					<s:if test="%{debeMostrarSubdisciplina}">
						<td>
							<s:if test="%{descripcionSubdisciplina==null}">
								<b><s:text name="resultados.no_disponible"/></b>
							</s:if>
							<s:else>
								<s:property value="descripcionSubdisciplina" /><br>	
							</s:else>
						</td>
					</s:if>
					<td>
						<s:url action="detallesEquipo.action" var="urlTag" >
						    <s:param name="equipoid"><s:property value="equipoId" /></s:param>
						</s:url>
						<a href="${urlTag}" target="_blank">Consultar</a>
					</td>
				</tr>
			</s:iterator>
		</s:if>
		<s:elseif test="%{!laboratorios.isEmpty}">
			<thead>
			<tr>
				<th>Institución</th>
				<th>Área de Conocimiento</th>
				
				<!-- Orden de los siguientes dos campos intercambiado por
				campos asociados en la vista de CONLAB cambiados -->
				<s:if test="%{debeMostrarNombreDeLaboratorio}">
					<th>Nombre del Laboratorio</th>
				</s:if>	
				<s:if test="%{debeMostrarEstado}">
					<th>Estado</th>
				</s:if>
				<s:if test="%{debeMostrarDependencia}">
					<th>Dependencia</th>
				</s:if>
					
				<s:if test="%{debeMostrarSubdependencia}">
					<th>Subdependencia</th>
				</s:if>
					
				<s:if test="%{debeMostrarDisciplina}">
					<th>Disciplina</th>
				</s:if>
					
				<s:if test="%{debeMostrarSubdisciplina}">
					<th>Subdisciplina</th>
				</s:if>
				<th>Detalles de Laboratorio</th>	
				
			</tr>
			</thead>
			<s:iterator value="laboratorios">
				<tr>
					<td><s:property value="descripcionInstitucion" /></td>
					<td><s:property value="descripcionArea" /></td>
					
					<s:if test="%{debeMostrarEstado}">
						<td><s:property value="entidadFederativa" /></td>
					</s:if>
					
					<s:if test="%{debeMostrarNombreDeLaboratorio}">
						<td><s:property value="nombreLaboratorio" /></td>
					</s:if>
					
					<s:if test="%{debeMostrarDependencia}">
						<td>
							<s:if test="%{descripcionDependencia==null}">
								<b><s:text name="resultados.no_disponible"/></b>
							</s:if>
							<s:else>
								<s:property value="descripcionDependencia" /><br>	
							</s:else>
						</td>
					</s:if>
					
					<s:if test="%{debeMostrarSubdependencia}">
						<td>
							<s:if test="%{descripcionSector==null}">
								<b><s:text name="resultados.no_disponible"/></b>
							</s:if>
							<s:else>
								<s:property value="descripcionSector" /><br>	
							</s:else>
						</td>
					</s:if>
					
					<s:if test="%{debeMostrarDisciplina}">
						<td><s:property value="descripcionDisciplina" /></td>
					</s:if>
					
					<s:if test="%{debeMostrarSubdisciplina}">
						<td>
							<s:if test="%{descripcionSubdisciplina==null}">
								<b><s:text name="resultados.no_disponible"/></b>
							</s:if>
							<s:else>
								<s:property value="descripcionSubdisciplina" /><br>	
							</s:else>
						</td>
					</s:if>
					<td>
						<s:url action="detallesLaboratorio.action" var="urlTag" >
						    <s:param name="laboratorio"><s:property value="laboratorioId" /></s:param>
						    <s:param name="sectorId"><s:property value="sectorId" /></s:param>
						    <s:param name="institucion"><s:property value="institucionId" /></s:param>
						    <s:param name="dependencia"><s:property value="dependenciaId" /></s:param>
						    <s:param name="sectorCve"><s:property value="claveSector" /></s:param>
						    <s:param name="area"><s:property value="areaId" /></s:param>
						    <s:param name="disciplina"><s:property value="disciplinaId" /></s:param>
						    <s:param name="subdisciplina"><s:property value="subdisciplinaId" /></s:param>
						</s:url>
						
						<a href="${urlTag}" target="_blank">Detalle</a>
					</td>
				</tr>
			</s:iterator>
		</s:elseif>
		
	</table>
</div>
</div>
