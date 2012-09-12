<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="content centered">
<div id="details" class="centered big">

	<h3><s:text name="resultados.laboratorio.titulo"/></h3>	
	<br>
    
    <s:url action="fetchPDF.action" var="urlTag" >
	    <s:param name="dependencia"><s:property value="fichaTecnicaLaboratorio.dependenciaId" /></s:param>
	    <s:param name="sectorCve"><s:property value="fichaTecnicaLaboratorio.claveSector" /></s:param>
	    <s:param name="area"><s:property value="fichaTecnicaLaboratorio.areaId" /></s:param>
	    <s:param name="disciplina"><s:property value="fichaTecnicaLaboratorio.disciplinaId" /></s:param>
	    <s:param name="subdisciplina"><s:property value="fichaTecnicaLaboratorio.subdisciplinaId" /></s:param>
		<s:param name="laboratorio"><s:property value="fichaTecnicaLaboratorio.laboratorioId" /></s:param>
		<s:param name="institucion"><s:property value="fichaTecnicaLaboratorio.institucionId" /></s:param>
		<s:param name="sectorId"><s:property value="fichaTecnicaLaboratorio.sectorId" /></s:param>
	</s:url>
						
	<a href="${urlTag}" target="_blank">Descargar PDF</a>

	<table class="card centered">
        <tbody>
          <tr>
            <td class="titulo2 table-head" colspan="2" rowspan="1">
            Datos generales de la organización<br>
            </td>
          </tr>
          <tr>
            <td class="left"><span class="pregunta1">Nombre:</span><br></td>
            <td><span class="respuesta"><s:property value="fichaTecnicaLaboratorio.descripcionInstitucion" /><br></span></td>
          </tr>
          <tr>
            <td class="left"><span class="pregunta1">2do Nivel:</span></td>
            <td>
            	<span class="respuesta">
	            	<s:if test="%{fichaTecnicaLaboratorio.descripcionDependencia==null}">
						<b><s:text name="resultados.no_disponible"/></b>
					</s:if>
					<s:else>
						<s:property value="fichaTecnicaLaboratorio.descripcionDependencia" /><br>	
					</s:else>
            	</span>
            </td>
          </tr>
          <tr>
            <td class="left"><span class="pregunta1">3er Nivel:</span></td>
            <td>
	            <span class="respuesta">
		            <s:if test="%{fichaTecnicaLaboratorio.descripcionSector==null}">
						<b><s:text name="resultados.no_disponible"/></b>
					</s:if>
					<s:else>
						<s:property value="fichaTecnicaLaboratorio.descripcionSector" /><br>	
					</s:else>
	            </span>
            </td>
          </tr>
        </tbody>
    </table>
    
	<br/>
		
	<table class="card centrado">
        <tbody>
          <tr>
            <td class="titulo2 table-head" colspan="2" rowspan="1">
            Datos generales del laboratorio<br>
            </td>
          </tr>
          <tr>
            <td class="left"><span class="pregunta1">Nombre:</span><br></td>
            <td>
            	<span class="respuesta">
            		<s:property value="fichaTecnicaLaboratorio.nombreLaboratorio" /><br>
            	</span>
            </td>
          </tr>
          <tr>
            <td class="left"><span class="pregunta1">Area:</span></td>
            <td>
            	<span class="respuesta">
            		<s:property value="fichaTecnicaLaboratorio.descripcionArea" /><br>
            	</span>
            </td>
          </tr>
          <tr>
            <td class="left"><span class="pregunta1">Disciplina:</span></td>
            <td>
            	<span class="respuesta">
            		<s:property value="fichaTecnicaLaboratorio.descripcionDisciplina" /><br>
            	</span>
            </td>
          </tr>
          <tr>
            <td class="left"><span class="pregunta1">Subdisciplina:</span></td>
            <td>
            	<span class="respuesta">
		            <s:if test="%{fichaTecnicaLaboratorio.descripcionSubdisciplina==null}">
						<b><s:text name="resultados.no_disponible"/></b>
					</s:if>
					<s:else>
						<s:property value="fichaTecnicaLaboratorio.descripcionSubdisciplina" /><br>	
					</s:else>
	            </span>
            </td>
          </tr>
          <tr>
            <td class="left"><span class="pregunta1">Calle y número:<br></span></td>
            <td>
            	<span class="respuesta">
					<s:property value="datoGeneral.calle" /><br>
            	</span>
            </td>
          </tr>
          <tr>
            <td class="left"><span class="pregunta1">Colonia:<br></span></td>
            <td><span class="respuesta"><s:property value="datoGeneral.colonia" /><br></span></td>
          </tr>
          <tr>
            <td class="left"><span class="pregunta1">Municipio/Delegación:<br></span></td>
            <td><span class="respuesta"><s:property value="datoGeneral.ciudad" /><br></span></td>
          </tr>
          <tr>
            <td class="left"><span class="pregunta1">Estado:<br></span></td>
            <td><span class="respuesta"><s:property value="datoGeneral.estado"/><br></span></td>
          </tr>
          <tr>
            <td class="left"><span class="pregunta1">Código postal:<br></span></td>
            <td><span class="respuesta"><s:property value="datoGeneral.codigoPostal"/><br></span></td>
          </tr>
          <tr>
          </tr>
          <tr>
            <td class="left"><span class="pregunta1">Página Web:<br></span></td>
            <td><span class="respuesta"><s:property value="datoGeneral.paginaWeb" /><br></span></td>
          </tr>
        </tbody>
      </table>
      <br>
      <table class="card centrado">
        <tbody>
          <tr>
            <td class="titulo2 table-head" colspan="6" rowspan="1">
            	Tipos de actividades en los que se usa el activo del laboratorio
            </td>
          </tr>
          <tr>
            <td><span class="pregunta1">Análisis y caracterización de cualquier equipo:<br></span></td>
            <td><span class="respuesta"><s:property value="actividad.analisisYCaracterizacion" />%<br></span></td>
            <td><span class="pregunta1">Pruebas cualquier tipo:<br></span></td>
            <td><span class="respuesta"><s:property value="actividad.pruebasCualquierTipo" />%<br></span></td>
            <td><span class="pregunta1">Investigación científica básica:<br></span></td>
            <td><span class="respuesta"><s:property value="actividad.investigacionCientificaBasica" />%<br></span></td>
          </tr>
          <tr>
            <td><span class="pregunta1">Investigación aplicada:<br><br></span></td>
            <td><span class="respuesta"><s:property value="actividad.investigacionAplicada" />%<br></span></td>
            <td><span class="pregunta1">Desarrollo experimental:<br></span></td>
            <td><span class="respuesta"><s:property value="actividad.desarrolloExperimental" />%<br></span></td>
            <td><span class="pregunta1">Desarrollo de productos, procesos y equipos:<br></span></td>
            <td><span class="respuesta"><s:property value="actividad.desarrolloProductosProcesosEquipos" />%<br></span></td>
          </tr>
          <tr>
            <td><span class="pregunta1">Elaboración de prototipos:<br></span></td>
            <td><span class="respuesta"><s:property value="actividad.elaboracionPrototipo" />%<br></span></td>
            <td><span class="pregunta1">Producciones a escala piloto:<br></span></td>
            <td><span class="respuesta"><s:property value="actividad.produccionesEscalaPiloto" />%<br></span></td>
            <td><span class="pregunta1">Producciones a escala semi-comerciales:<br></span></td>
            <td><span class="respuesta"><s:property value="actividad.produccionesAEscalaSemiComerciales" />%<br></span></td>
          </tr>
          <tr>
            <td><span class="pregunta1">Docencia y capacitación:<br></span></td>
            <td><span class="respuesta"><s:property value="actividad.docenciaCapacitacion" />%<br></span></td>
            <td><span class="pregunta1">Otros (especificar):<br></span></td>
            <td><span class="respuesta"><s:property value="actividad.otros" />%<br></span></td>
            <td><span class="pregunta1">TOTAL:<br></span></td>
            <td><span class="respuesta"><s:property value="actividad.porcentajeTotal" />%<br></span></td>
          </tr>
        </tbody>
      </table>
	  <br/>
	  
	  <div class="card centrado vertical">
	  	<div class="titulo2 head">Actividades</div>
	  	
	  	<div class="recuadro">
	  		<p class="left left-larga">Principales capacidades y habilidades</p>
	  		<p><s:property value="actividad.capacidadesHabilidades" /></p>
	  	</div>
	  	
	  	<div class="recuadro">
	  		<p class="left">Línea de investigación 1</p>
	  		<p><s:property value="actividad.lineaInvUno" /></p>
	  	</div>
	  	
	  	<div class="recuadro">
	  		<p class="left">Línea de investigación 2</p>
	  		<p><s:property value="actividad.lineaInvDos" /></p>
	  	</div>
	  	
	  	<div class="recuadro">
	  		<p class="left">Línea de investigación 3</p>
	  		<p><s:property value="actividad.lineaInvTres" /></p>
	  	</div>
	  	
	  	<div class="division"></div>
	  	
	  	<table>
	        <tbody>
	          <tr>
	            <td class="left"><span class="pregunta1">Inversión total estimada:<br></span></td>
	            <td><span class="respuesta">$<s:property value="actividad.inversionTotal" /><br></span></td>
	          </tr>
	          <tr>
	            <td class="left"><span class="pregunta1">Area estimada de laboratorio:<br></span></td>
	            <td><span class="respuesta"><s:property value="actividad.areaEstimada" />m<sup>2</sup></span><br></td>
	          </tr>
	          <tr>
	            <td class="left"><span class="pregunta1">Costo anual de mantenimiento:<br></span></td>
	            <td><span class="respuesta">$<s:property value="actividad.costoAnualMantenimiento" /><br></span></td>
	          </tr>
	          <tr>
	          </tr>
	        </tbody>
      	</table>
	  </div>
	  
	  <table class="card centrado vertical">
        <tbody>

          <tr>
            <td class="titulo2 table-head" colspan="4" rowspan="1">Equipo principal<br></td>

          </tr>
          <tr class="centered-text">

            <th><span class="pregunta1">Nombre<br></span></th>

            <th><span class="pregunta1">Detalles<br></span></th>

          </tr>
          <s:iterator value="equiposLaboratorio">
			<tr class="centered-text">
	            <td>
	            	<span class="respuesta"><s:property value="categoriaEquipo.nombreDeEquipo" /></span>
	            </td>
	            <td>
	            	<span class="respuesta">
	            		<s:url action="detallesEquipo.action" var="urlTag" >
					    	<s:param name="equipoid"><s:property value="equipoId" /></s:param>
						</s:url>
						<a href="${urlTag}" target="_blank">Ver Ficha</a>
	            	</span>
	            </td>
          	</tr>
		  </s:iterator>
        </tbody>
      </table>
	  
	  <table class="card centrado vertical">
        <tbody>

          <tr>
            <td class="titulo2 table-head" colspan="4" rowspan="1">Acceso y uso de equipos e instalaciones<br></td>
          </tr>
          
          <tr>
            <td class="left"><span class="pregunta1">¿Permite el acceso?:<br></span></td>
            <td>
            	<span class="respuesta">
            	<s:if test="%{fichaTecnicaLaboratorio.permiteServicio}">
            		Si
				</s:if>
				<s:else>
					No
				</s:else>
            	</span>
            	
            </td>
          </tr>
          
        </tbody>
      </table>
	  
	  <s:if test="%{fichaTecnicaLaboratorio.permiteServicio}">
		<table class="card centrado">
	        <tbody>
	          <tr>
	            <td class="titulo2 table-head" colspan="4" rowspan="1">Contacto
	para acceder a las capacidades y servicios del laboratorio:<br></td>
	          </tr>
	          <tr>
	            <td class="left"><span class="pregunta1">Nombre:<br></span></td>
	            <td><span class="respuesta"><s:property value="datoGeneral.nombreDeContacto" /><br></span></td>
	          </tr>
	          <tr>
	            <td class="left"><span class="pregunta1">Puesto:<br></span></td>
	            <td><span class="respuesta"><s:property value="datoGeneral.puestoDeContacto" /><br></span></td>
	          </tr>
	          <tr>
	            <td class="left"><span class="pregunta1">Teléfono:<br></span></td>
	            <td><span class="respuesta"><s:property value="datoGeneral.telefonoDeContacto" /><br></span></td>
	          </tr>
	          <tr>
	            <td class="left"><span class="pregunta1">Correo Electrónico:<br></span></td>
	            <td><span class="respuesta"><s:property value="datoGeneral.email" /><br></span></td>
	          </tr>
	        </tbody>
	      </table>
	  </s:if>
      
      
      <table class="card centrado vertical">
        <tbody>

          <tr>
            <td class="titulo2 table-head" colspan="4" rowspan="1">Acreditaciones<br></td>

          </tr>
          <tr class="centered-text">

            <th><span class="pregunta1">Acreditaciones/Certificaciones<br></span></th>

            <th><span class="pregunta1">Organismo que Otorga<br></span></th>
            <th><span class="pregunta1">Fecha de Inicio<br></span></th>
            <th><span class="pregunta1">Fecha de Término<br></span></th>

          </tr>
          <s:iterator value="acreditaciones">
			<tr class="centered-text">
	            <td>
	            	<span class="respuesta"><s:property value="nombre" /></span>
	            </td>
	            <td>
	            	<span class="respuesta"><s:property value="otorgante" /></span>
	            </td>
	            <td>
	            	<span class="respuesta"><s:property value="fechaDeInicio" /></span>
	            </td>
	            <td>
	            	<span class="respuesta"><s:property value="fechaDeTermino" /></span>
	            </td>
          	</tr>
		  </s:iterator>
        </tbody>
      </table>
</div>
</div>