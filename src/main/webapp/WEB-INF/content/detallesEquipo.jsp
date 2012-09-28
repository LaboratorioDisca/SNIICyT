<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="details" class="centered big">

	<h3>Ficha Técnica de Equipo</h3>	
		<br>

	<table class="card centrado vertical">
        <tbody>
          <tr>
            <td class="titulo2 table-head" colspan="4" rowspan="1">Descripción del equipo<br></td>
          </tr>
          <tr class="centered-text">
            <th><span class="pregunta1">Nombre<br></span></th>
            <th><span class="pregunta1">Descripción<br></span></th>
            <th><span class="pregunta1">Año adquisición<br></span></th>
            <th><span class="pregunta1">Costo [pesos]<br></span></th>
          </tr>
          <tr class="centered-text">
            <td><span class="respuesta"><s:property value="fichaTecnicaEquipo.nombreEquipo" /><br></span></td>
            <td><span class="respuesta"><s:property value="equipoLab.fichaTecnica" /><br></span></td>
            <td><span class="respuesta"><s:property value="equipoLab.anioDeAdquisicion" /><br></span></td>
            <td style="vertical-align: top; width: 121px; text-align: center;"><span class="respuesta"><s:property value="equipoLab.costoEstimado" /><br>
            </span></td>
          </tr>
        </tbody>
      </table>
</div>
