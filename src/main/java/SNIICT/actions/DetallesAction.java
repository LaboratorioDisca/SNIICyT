package SNIICT.actions;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Image;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import SNIICT.utils.Hibernatable;

import SNIICT.models.Acreditacion;
import SNIICT.models.Actividad;
import SNIICT.models.DatoGeneral;
import SNIICT.models.DescripcionLaboratorio;
import SNIICT.models.DescripcionEquipo;
import SNIICT.models.EquipoLaboratorio;
import SNIICT.models.LineasLaboratorio;

/*
 * El conjunto de acciones definidas en este controlador se encarga de desplegar
 * los detalles tanto para equipos de laboratorio tanto en PDF y HTML 
 */

@Results({
	@Result(name="success", location="/WEB-INF/Layout.jsp"),
	@Result(name="none", location="noencontrado.jsp")
})
public class DetallesAction extends PartialAwareAction implements ParameterAware, ServletResponseAware {

	private static final long serialVersionUID = 1L;
	private InputStream inputStream;

	private DescripcionLaboratorio fichaTecnicaLaboratorio;
	private List<EquipoLaboratorio> equiposLaboratorio;
	private List<LineasLaboratorio> lineasLaboratorio;
	private List<Acreditacion> acreditaciones;
	
	private DescripcionEquipo fichaTecnicaEquipo;
	
	private EquipoLaboratorio equipoLab;
	
	private DatoGeneral datoGeneral;
	private Actividad actividad;
	
	private HttpServletResponse response;
	private Map<String, String[]> parameters;
	
	private String tableSpaceSif = "RODRIGO.";
	private String tableSpaceSystem = "SYSTEM.";

	public Map<String, String[]> getParameters() {
		return this.parameters;
	}
	
	public void setParameters(Map<String, String[]> arg0) {
		this.parameters = arg0;
	}
	
	/*
	 * Despliega los detalles de un equipo en formato HTML
	 */
	@Action("detallesEquipo")
	public String dameDetallesEquipo() {
		this.setRenderPartial("detallesEquipo.jsp");
		String equipoId = this.getParameters().get("equipoid")[0];

		Session s = Hibernatable.getSession();
		s.beginTransaction();
		
		SQLQuery queryFicha = s.createSQLQuery("SELECT * FROM "+ DescripcionEquipo.tableName +" WHERE ICT_ID_EQUIPO_FLD = :equipoId");
		queryFicha.addEntity(DescripcionEquipo.class);
		queryFicha.setString("equipoId", equipoId);
		
		@SuppressWarnings("unchecked")
		List<DescripcionEquipo> fichas = queryFicha.list();
		
		if(fichas.isEmpty())
			return NONE;
		
		fichaTecnicaEquipo = (DescripcionEquipo) fichas.get(0);
		SQLQuery queryFichaEquipo = s.createSQLQuery("SELECT * FROM " + tableSpaceSif + "PS_ICT_EQUILAB_TBL WHERE ICT_ID_EQUIPO_FLD = :equipoId");
		queryFichaEquipo.addEntity(EquipoLaboratorio.class);
		queryFichaEquipo.setString("equipoId", equipoId);
		
		@SuppressWarnings("unchecked")
		List<EquipoLaboratorio> fichasConEquipo = queryFichaEquipo.list();
		
		if(fichas.isEmpty())
			return NONE;
		
		equipoLab = (EquipoLaboratorio) fichasConEquipo.get(0);
		
		s.getTransaction().commit();

		return SUCCESS;
	}
	
	/*
	 * Despliega los detalles de un laboratorio en formato HTML
	 */
	@Action("detallesLaboratorio")
	public String dameDetallesLaboratorio() {
		this.setRenderPartial("detallesLaboratorio.jsp");

        this.cargaLaboratorios();
		
        return SUCCESS;
	}

	/*
	 * Extracción de campos común para las representaciones de los recursos
	 * que son generadas por este controlador
	 */
    public String cargaLaboratorios() {
        Session s = Hibernatable.getSession();
		s.beginTransaction();
		

		String obligatorios = " ICT_ID_LABOR_FLD = :laboratorioId AND ICT_ID_SECTOR_FLD = :sectorId";
		String queryInstitucion = " AND ICT_ID_INSTITU_FLD = :institucionId";
		String queryParcial = obligatorios+queryInstitucion;

		String queryArea = " AND ICT_ID_AREA_FLD = :areaId";
		String queryClaveDisciplina = " AND ICT_CVE_DISCIP_FLD = :claveDisciplina";
		String queryClaveSubdisciplina = " AND ICT_CVESDICIP_FLD = :claveSubDisciplina";
		String queryClaveSector = " AND ICT_CVE_SECTOR_FLD = :claveSector";
		String queryDependencia = " AND ICT_CVE_DEPEN_FLD = :claveDependencia";
		
        String laboratorioId = this.getParameters().get("laboratorio")[0];
        String sectorId = this.getParameters().get("sectorId")[0];
        String institucionId = this.getParameters().get("institucion")[0];
        String dependencia = this.getParameters().get("dependencia")[0];
        String sectorClave = this.getParameters().get("sectorCve")[0];
        String area = this.getParameters().get("area")[0];
        String disciplina = this.getParameters().get("disciplina")[0];
        String subdisciplina = this.getParameters().get("subdisciplina")[0];
		
        if(!area.isEmpty())
        	queryParcial+= queryArea;
        if(!disciplina.isEmpty())
        	queryParcial+= queryClaveDisciplina;
        if(!subdisciplina.isEmpty())
        	queryParcial+= queryClaveSubdisciplina;
        if(!sectorClave.isEmpty())
        	queryParcial+= queryClaveSector;
        if(!dependencia.isEmpty())
        	queryParcial+= queryDependencia;
        
        SQLQuery queryFicha = s.createSQLQuery("SELECT * FROM "+DescripcionLaboratorio.tableName+" WHERE " + queryParcial);
		queryFicha.addEntity(DescripcionLaboratorio.class);
		queryFicha.setString("laboratorioId", laboratorioId);
		queryFicha.setString("sectorId", sectorId);
		queryFicha.setString("institucionId", institucionId);
		

		if(!area.isEmpty())
			queryFicha.setString("areaId", area);
        if(!disciplina.isEmpty())
        	queryFicha.setString("claveDisciplina", disciplina);
        if(!subdisciplina.isEmpty())
        	queryFicha.setString("claveSubDisciplina", subdisciplina);
        if(!sectorClave.isEmpty())
        	queryFicha.setString("claveSector", sectorClave);
        if(!dependencia.isEmpty())
        	queryFicha.setString("claveDependencia", dependencia);
        
		@SuppressWarnings("unchecked")
		List<DescripcionLaboratorio> fichas = queryFicha.list();
		
		if(fichas.isEmpty())
			return NONE;

		if(!fichas.isEmpty())
			this.setFichaTecnicaLaboratorio(fichas.get(0));
		
		SQLQuery queryDatoGeneral = s.createSQLQuery("SELECT * FROM " + tableSpaceSif + "PS_ICT_DATGRAL_TBL WHERE " + queryParcial);
		queryDatoGeneral.addEntity(DatoGeneral.class);
		queryDatoGeneral.setString("laboratorioId", laboratorioId);
		queryDatoGeneral.setString("sectorId", sectorId);
		queryDatoGeneral.setString("institucionId", institucionId);
		
		if(!area.isEmpty())
			queryDatoGeneral.setString("areaId", area);
        if(!disciplina.isEmpty())
        	queryDatoGeneral.setString("claveDisciplina", disciplina);
        if(!subdisciplina.isEmpty())
        	queryDatoGeneral.setString("claveSubDisciplina", subdisciplina);
        if(!sectorClave.isEmpty())
        	queryDatoGeneral.setString("claveSector", sectorClave);
        if(!dependencia.isEmpty())
        	queryDatoGeneral.setString("claveDependencia", dependencia);
        
		@SuppressWarnings("unchecked")
		List<DatoGeneral> datosGrales = queryDatoGeneral.list();
		
		if(!datosGrales.isEmpty())
			setDatoGeneral(datosGrales.get(0));
		
		
		SQLQuery queryActividades = s.createSQLQuery("SELECT * FROM " + tableSpaceSif + "PS_ICT_ACTIVID_TBL WHERE "+ obligatorios+queryDependencia+queryInstitucion+queryClaveSector);
		queryActividades.addEntity(Actividad.class);
		queryActividades.setString("laboratorioId", laboratorioId);
		queryActividades.setString("sectorId", sectorId);
		queryActividades.setString("institucionId", institucionId);
		queryActividades.setString("claveSector", sectorClave);
		queryActividades.setString("claveDependencia", dependencia);
		
		@SuppressWarnings("unchecked")
		List<Actividad> actividades = queryActividades.list();
		
		if(!actividades.isEmpty())
			setActividad(actividades.get(0));
		
		SQLQuery queryFichaEquipo = s.createSQLQuery("SELECT * FROM " + tableSpaceSif + "PS_ICT_EQUILAB_TBL WHERE " + obligatorios+queryDependencia+queryInstitucion+queryClaveSector);
		queryFichaEquipo.addEntity(EquipoLaboratorio.class);
		queryFichaEquipo.setString("laboratorioId", laboratorioId);
		queryFichaEquipo.setString("sectorId", sectorId);
		queryFichaEquipo.setString("institucionId", institucionId);
		queryFichaEquipo.setString("claveSector", sectorClave);
		queryFichaEquipo.setString("claveDependencia", dependencia);
		
		equiposLaboratorio = queryFichaEquipo.list();
		
		SQLQuery queryLineaLaboratorio = s.createSQLQuery("SELECT * FROM " + tableSpaceSif + "PS_ICT_LIN_INV_TBL WHERE " + obligatorios+queryDependencia+queryInstitucion+queryClaveSector);
		queryLineaLaboratorio = s.createSQLQuery("SELECT * FROM " + tableSpaceSif + "PS_ICT_LIN_INV_TBL WHERE ICT_ID_LABOR_FLD = " + laboratorioId +
		" AND ICT_ID_SECTOR_FLD = '0" + sectorId + "' AND ICT_CVE_DEPEN_FLD = '" + dependencia + "' AND ICT_ID_INSTITU_FLD = '000" + institucionId + 
		"' AND ICT_CVE_SECTOR_FLD = '0" + sectorClave + "'");
//		System.out.println("sql::SELECT * FROM " + tableSpaceSif + "PS_ICT_LIN_INV_TBL WHERE ICT_ID_LABOR_FLD = " + laboratorioId +
//				" AND ICT_ID_SECTOR_FLD = '0" + sectorId + "' AND ICT_CVE_DEPEN_FLD = '" + dependencia + "' AND ICT_ID_INSTITU_FLD = '000" + institucionId + 
//				"' AND ICT_CVE_SECTOR_FLD = '0" + sectorClave + "'");
//		System.out.println("laboratorioid: " + laboratorioId + " sectorId: " + sectorId + " institucionId: " + institucionId +
//				" claveSector: " + sectorClave + " claveDep: " + dependencia);
		queryLineaLaboratorio.addEntity(LineasLaboratorio.class);
		//queryLineaLaboratorio.setString("laboratorioId", laboratorioId);
		//queryLineaLaboratorio.setString("sectorId", sectorId);
//		queryLineaLaboratorio.setString("institucionId", institucionId);
//		queryLineaLaboratorio.setString("claveSector", sectorClave);
//		queryLineaLaboratorio.setString("claveDependencia", dependencia);
		System.out.println("sql: " + queryLineaLaboratorio.getQueryString());
		
		lineasLaboratorio = queryLineaLaboratorio.list();
		
		SQLQuery queryAcreditacion = s.createSQLQuery("SELECT * FROM " + tableSpaceSif + "PS_ICT_ACRECER_TBL WHERE ICT_ID_LABOR_FLD = :laboratorioId AND ICT_ID_INSTITU_FLD = :institucionId AND ICT_CVE_DEPEN_FLD IN (:dependenciaClave, NULL) AND ICT_CVE_SECTOR_FLD IN (:sectorClave, NULL)");
		queryAcreditacion.addEntity(Acreditacion.class);
		queryAcreditacion.setString("laboratorioId", laboratorioId);
		queryAcreditacion.setString("institucionId", institucionId);
		queryAcreditacion.setString("dependenciaClave", dependencia);
		queryAcreditacion.setString("sectorClave", sectorClave);
		
		acreditaciones = queryAcreditacion.list();
		
		s.getTransaction().commit();
        return SUCCESS;
    }
	
    public void addLogoToDocument(Document document) {
    	try { 
    		Image logo = Image.getInstance(new URL(this.getText("application.url")+"/SNIICyt/images/logo.jpg"));
    		logo.scalePercent(50);
    		logo.setAlignment(Element.ALIGN_CENTER);
    		document.add(logo); 
    	} catch (Exception bex) {
    		
    	}
    }
    
    /*
     * Genera los detalles de un equipo en formato PDF
     */
    @Action("equipoPDF")
	public void equipoPDF() {
		this.cargaLaboratorios();

		int anchoTabla=100;
		Paragraph nuevalinea = new Paragraph(" ");
		Document document = new Document(PageSize.LETTER);
	    try{
	    	response.setContentType("application/pdf");
	    	response.setHeader("Content-Disposition",
	    			"attachment;filename=Detalles del laboratorio.pdf");
	   	 	
	      PdfWriter.getInstance(document, response.getOutputStream());
	      document.addAuthor("SNIICyT CONACYT");
	      document.addCreator("SNIICyT CONACYT");
	      document.open();
	      
	      this.addLogoToDocument(document);


			Paragraph titulo = new Paragraph(" ");
			titulo.add(new Chunk("SNIICyT", FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.BOLD,new BaseColor(60, 59, 84))));
			titulo.setAlignment(Element.ALIGN_CENTER);
 			document.add(titulo);	      
	      document.add(new Paragraph(" "));
	      
			PdfPTable table = new PdfPTable(2);
			table.setWidthPercentage(anchoTabla);
			table.setWidths(new int[]{1, 5});
         PdfPCell cell;   
      	Paragraph tituloTabla1 = new Paragraph("");      	
      	tituloTabla1.add(new Chunk("Datos generales de la organización", FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.BOLD,new BaseColor(255, 255, 255))));
         cell = new PdfPCell(tituloTabla1);
         cell.setColspan(2);
         cell.setBackgroundColor(new BaseColor(60, 59, 84));
         table.addCell(cell);
         table.addCell(new Paragraph(new Chunk("Nombre:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
         if(fichaTecnicaLaboratorio.getDescripcionInstitucion() != null) 
            table.addCell(new Paragraph(new Chunk(fichaTecnicaLaboratorio.getDescripcionInstitucion(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        else
            table.addCell(new Paragraph(new Chunk("No disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
       	table.addCell(new Paragraph(new Chunk("2do Nivel:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
         if(fichaTecnicaLaboratorio.getDescripcionDependencia() != null)
        	table.addCell(new Paragraph(new Chunk(fichaTecnicaLaboratorio.getDescripcionDependencia(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
         else
            table.addCell(new Paragraph(new Chunk("No disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
         table.addCell(new Paragraph(new Chunk("3er Nivel:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
         if(fichaTecnicaLaboratorio.getDescripcionSector() != null) 
            table.addCell(new Paragraph(new Chunk(fichaTecnicaLaboratorio.getDescripcionSector(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));  
         else
            table.addCell(new Paragraph(new Chunk("No disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
         table.setSpacingBefore(5);
         table.setSpacingAfter(5);
    	   document.add(table);

        }catch(Exception e){
	        e.printStackTrace();
	    }
	    document.close();
	}

    /*
     * Genera los detalles de un laboratorio en formato PDF
     */
	@Action("fetchPDF")
	public void fetchPDF() {
		this.cargaLaboratorios();

		int anchoTabla=100;
		Paragraph nuevalinea = new Paragraph(" ");
		Document document = new Document(PageSize.LETTER);
	    try{
	    	response.setContentType("application/pdf");
	    	response.setHeader("Content-Disposition",
	    			"attachment;filename=Detalles del laboratorio.pdf");
	   	 	
	      PdfWriter.getInstance(document, response.getOutputStream());
	      document.addAuthor("SNIICyT CONACYT");
	      document.addCreator("SNIICyT CONACYT");
	      document.open();

	      this.addLogoToDocument(document);

			Paragraph titulo = new Paragraph(" ");
			titulo.add(new Chunk("SNIICyT", FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.BOLD,new BaseColor(60, 59, 84))));
			titulo.setAlignment(Element.ALIGN_CENTER);
 			document.add(titulo);	      
	      document.add(new Paragraph(" "));
	      
			PdfPTable table = new PdfPTable(2);
			table.setWidthPercentage(anchoTabla);
			table.setWidths(new int[]{1, 5});
         PdfPCell cell;   
      	Paragraph tituloTabla1 = new Paragraph("");      	
      	tituloTabla1.add(new Chunk("Datos generales de la organización", FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.BOLD,new BaseColor(255, 255, 255))));
         cell = new PdfPCell(tituloTabla1);
         cell.setColspan(2);
         cell.setBackgroundColor(new BaseColor(60, 59, 84));
         table.addCell(cell);
         table.addCell(new Paragraph(new Chunk("Nombre:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
         if(fichaTecnicaLaboratorio.getDescripcionInstitucion() != null) 
            table.addCell(new Paragraph(new Chunk(fichaTecnicaLaboratorio.getDescripcionInstitucion(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        else
            table.addCell(new Paragraph(new Chunk("No disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
       	table.addCell(new Paragraph(new Chunk("2do Nivel:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
         if(fichaTecnicaLaboratorio.getDescripcionDependencia() != null)
        	table.addCell(new Paragraph(new Chunk(fichaTecnicaLaboratorio.getDescripcionDependencia(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
         else
            table.addCell(new Paragraph(new Chunk("No disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
         table.addCell(new Paragraph(new Chunk("3er Nivel:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
         if(fichaTecnicaLaboratorio.getDescripcionSector() != null) 
            table.addCell(new Paragraph(new Chunk(fichaTecnicaLaboratorio.getDescripcionSector(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));  
         else
            table.addCell(new Paragraph(new Chunk("No disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
         table.setSpacingBefore(5);
         table.setSpacingAfter(5);
    	   document.add(table);
 	 		PdfPTable table2 = new PdfPTable(2);
 	 		table2.setWidthPercentage(anchoTabla);
			table2.setWidths(new int[]{2, 5});
      	Paragraph tituloTabla2 = new Paragraph("");
      	tituloTabla2.add(new Chunk("Datos generales del laboratorio", FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.BOLD,new BaseColor(255, 255, 255))));
         cell = new PdfPCell(tituloTabla2);
       	cell.setColspan(2);
       	cell.setBackgroundColor(new BaseColor(60, 59, 84));
        	table2.addCell(cell);
        	table2.addCell(new Paragraph(new Chunk("Nombre:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
        	table2.addCell(new Paragraph(new Chunk(fichaTecnicaLaboratorio.getNombreLaboratorio(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        	table2.addCell(new Paragraph(new Chunk("Area:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
        	table2.addCell(new Paragraph(new Chunk(fichaTecnicaLaboratorio.getDescripcionArea(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        	table2.addCell(new Paragraph(new Chunk("Disciplina:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(fichaTecnicaLaboratorio.getDescripcionDisciplina() != null)
        	    table2.addCell(new Paragraph(new Chunk(fichaTecnicaLaboratorio.getDescripcionDisciplina(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
            else
                table2.addCell(new Paragraph(new Chunk("No disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        	table2.addCell(new Paragraph(new Chunk("Subdisciplina:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(fichaTecnicaLaboratorio.getDescripcionSubdisciplina() != null)
        	    table2.addCell(new Paragraph(new Chunk(fichaTecnicaLaboratorio.getDescripcionSubdisciplina(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
            else
                table2.addCell(new Paragraph(new Chunk("No disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        	table2.addCell(new Paragraph(new Chunk("Calle y número:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
        	table2.addCell(new Paragraph(new Chunk(datoGeneral.getCalle(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        	table2.addCell(new Paragraph(new Chunk("Colonia:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
        	table2.addCell(new Paragraph(new Chunk(datoGeneral.getColonia(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));	 
        	table2.addCell(new Paragraph(new Chunk("Municipio/Delegación:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
        	table2.addCell(new Paragraph(new Chunk(datoGeneral.getCiudad(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        	table2.addCell(new Paragraph(new Chunk("Estado:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
        	table2.addCell(new Paragraph(new Chunk(datoGeneral.getEstado(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        	table2.addCell(new Paragraph(new Chunk("Código Postal:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
        	table2.addCell(new Paragraph(new Chunk(datoGeneral.getCodigoPostal(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        	table2.addCell(new Paragraph(new Chunk("Latitud:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
        	table2.addCell(new Paragraph(new Chunk(datoGeneral.getLatitud().toString(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        	table2.addCell(new Paragraph(new Chunk("Longitud:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
        	table2.addCell(new Paragraph(new Chunk(datoGeneral.getLongitud().toString(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        	table2.addCell(new Paragraph(new Chunk("Página Web:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(datoGeneral.getPaginaWeb() != null)
            	table2.addCell(new Paragraph(new Chunk(datoGeneral.getPaginaWeb(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));	 
            else
                table2.addCell(new Paragraph(new Chunk("No disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));	
        	table2.setSpacingBefore(5);
         table2.setSpacingAfter(5);
        	document.add(table2);       
 			//document.add(nuevalinea);        	 	
/*	  	 	PdfPTable table3 = new PdfPTable(3);
	  	 	table3.setWidthPercentage(anchoTabla);
      	Paragraph tituloTabla3 = new Paragraph("");
      	tituloTabla3.add(new Chunk("Personal perteneciente al SNI que trabaja en el laboratorio", FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.BOLD,new BaseColor(255, 255, 255))));
         cell = new PdfPCell(tituloTabla3);	  	 	
       	cell.setColspan(3);
         cell.setBackgroundColor(new BaseColor(60, 59, 84));       	
        	table3.addCell(cell);
        	
        	cell = new PdfPCell(new Paragraph(new Chunk("Clave SNI", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));	  	 	
 			cell.setHorizontalAlignment (Element.ALIGN_CENTER);       	
        	table3.addCell(cell);

        	cell = new PdfPCell(new Paragraph(new Chunk("Nombre", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));	  	 	
 			cell.setHorizontalAlignment (Element.ALIGN_CENTER);       	
        	table3.addCell(cell);

        	cell = new PdfPCell(new Paragraph(new Chunk("Nivel", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));	  	 	
 			cell.setHorizontalAlignment (Element.ALIGN_CENTER);       	
        	table3.addCell(cell);
         table3.setSpacingBefore(5);
         table3.setSpacingAfter(5);
        	document.add(table3);
        	*/
        	
 			//document.add(nuevalinea);        	
		 	PdfPTable table4 = new PdfPTable(4);
		 	table4.setWidthPercentage(anchoTabla);
     		Paragraph tituloTabla4 = new Paragraph("");
      	tituloTabla4.add(new Chunk("Acreditaciones/Certificaciones del laboratorio", FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.BOLD,new BaseColor(255, 255, 255))));
         cell = new PdfPCell(tituloTabla4);	  	 	
       	cell.setColspan(4);
         cell.setBackgroundColor(new BaseColor(60, 59, 84));       	
        	table4.addCell(cell);
        	
       	cell = new PdfPCell(new Paragraph(new Chunk("Acreditaciones/Certificaciones", FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD,new BaseColor(117, 53, 153)))));	  	 	
 			cell.setHorizontalAlignment (Element.ALIGN_CENTER);       	
        	table4.addCell(cell);

        	cell = new PdfPCell(new Paragraph(new Chunk("Organismo que otorga", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));	  	 	
 			cell.setHorizontalAlignment (Element.ALIGN_CENTER);       	
        	table4.addCell(cell);

        	cell = new PdfPCell(new Paragraph(new Chunk("Fecha inicio", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));	  	 	
 			cell.setHorizontalAlignment (Element.ALIGN_CENTER);       	
        	table4.addCell(cell);
        	
       	cell = new PdfPCell(new Paragraph(new Chunk("Fecha fin", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));	  	 	
 		cell.setHorizontalAlignment (Element.ALIGN_CENTER);       	
        table4.addCell(cell);        	
        Iterator iter = acreditaciones.iterator();
        while (iter.hasNext()){
            Acreditacion acreditacion = (Acreditacion) iter.next();
            cell = new PdfPCell(new Paragraph(new Chunk(acreditacion.getNombre(), FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD,new BaseColor(87, 87, 87)))));	  	 	
 			cell.setHorizontalAlignment (Element.ALIGN_CENTER);       	
        	table4.addCell(cell);

        	cell = new PdfPCell(new Paragraph(new Chunk(acreditacion.getOtorgante(), FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD,new BaseColor(87, 87, 87)))));	  	 	
 			cell.setHorizontalAlignment (Element.ALIGN_CENTER);       	
        	table4.addCell(cell);
            if(acreditacion.getFechaDeInicio() != null)
            	cell = new PdfPCell(new Paragraph(new Chunk(acreditacion.getFechaDeInicio(), FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD,new BaseColor(87, 87, 87)))));	  	 	
            else
                cell = new PdfPCell(new Paragraph(new Chunk("No disponible", FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD,new BaseColor(117, 53, 153)))));
 			cell.setHorizontalAlignment (Element.ALIGN_CENTER);       	
        	table4.addCell(cell);
        	
            try{
                if(acreditacion.getFechaDeTermino() != null)
              	    cell = new PdfPCell(new Paragraph(new Chunk(acreditacion.getFechaDeTermino(), FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD,new BaseColor(87, 87, 87)))));	 
                else
                    cell = new PdfPCell(new Paragraph(new Chunk("No disponible", FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD,new BaseColor(87, 87, 87))))); 	 	
            }
            catch(NullPointerException e){ 
                    cell = new PdfPCell(new Paragraph(new Chunk("No disponible", FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD,new BaseColor(87, 87, 87)))));
            }

 			cell.setHorizontalAlignment (Element.ALIGN_CENTER);       	
        	table4.addCell(cell);        	
         }
         table4.setSpacingBefore(5);
         table4.setSpacingAfter(5);        	
        	document.add(table4);     
 			//document.add(nuevalinea);    
        	
        	
            try{      
		 	PdfPTable table5 = new PdfPTable(6);
		 	table5.setWidthPercentage(anchoTabla);
		 	table5.setWidths(new int[]{4, 1, 4, 1, 4, 1});
    		Paragraph tituloTabla5 = new Paragraph("");
      	    tituloTabla5.add(new Chunk("Tipos de actividades en los que se usa el activo del laboratorio", FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.BOLD,new BaseColor(255, 255, 255))));
            cell = new PdfPCell(tituloTabla5);		 	
       	    cell.setColspan(6);
            cell.setBackgroundColor(new BaseColor(60, 59, 84));       	
        	table5.addCell(cell);
        	table5.addCell(new Paragraph(new Chunk("Análisis y caracterización de cualquier tipo:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));

            if(actividad.getAnalisisYCaracterizacion() != null)
                table5.addCell(new Paragraph(new Chunk(actividad.getAnalisisYCaracterizacion()+"%", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
            else
                    table5.addCell(new Paragraph(new Chunk("N/D", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        	table5.addCell(new Paragraph(new Chunk("Pruebas cualquier tipo:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(actividad.getPruebasCualquierTipo() != null)
                table5.addCell(new Paragraph(new Chunk(actividad.getPruebasCualquierTipo()+"%", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));   
            else
                table5.addCell(new Paragraph(new Chunk("N/D", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));     	        	
        	table5.addCell(new Paragraph(new Chunk("Investigación científica básica:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(actividad.getInvestigacionCientificaBasica() != null)
        	    table5.addCell(new Paragraph(new Chunk(actividad.getInvestigacionCientificaBasica()+"%", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
            else
                table5.addCell(new Paragraph(new Chunk("N/D", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));     
        	table5.addCell(new Paragraph(new Chunk("Investigación aplicada:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(actividad.getInvestigacionAplicada() != null)
               	table5.addCell(new Paragraph(new Chunk(actividad.getInvestigacionAplicada()+"%", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
            else
                table5.addCell(new Paragraph(new Chunk("N/D", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));     
        	table5.addCell(new Paragraph(new Chunk("Desarrollo experimental:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(actividad.getDesarrolloExperimental() != null)
        	    table5.addCell(new Paragraph(new Chunk(actividad.getDesarrolloExperimental()+"%", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
            else
                table5.addCell(new Paragraph(new Chunk("N/D", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));             	        	
        	table5.addCell(new Paragraph(new Chunk("Desarrollo de productos, procesos y equipos:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(actividad.getDesarrolloProductosProcesosEquipos() != null)
        	    table5.addCell(new Paragraph(new Chunk(actividad.getDesarrolloProductosProcesosEquipos()+"%", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));        	
            else
                table5.addCell(new Paragraph(new Chunk("N/D", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));    
        	table5.addCell(new Paragraph(new Chunk("Elaboración de prototipos:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(actividad.getElaboracionPrototipo() != null)
            	table5.addCell(new Paragraph(new Chunk(actividad.getElaboracionPrototipo()+"%", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
            else
                table5.addCell(new Paragraph(new Chunk("N/D", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));   
        	table5.addCell(new Paragraph(new Chunk("Producciones a escala piloto:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(actividad.getProduccionesEscalaPiloto() != null)
        	    table5.addCell(new Paragraph(new Chunk(actividad.getProduccionesEscalaPiloto()+"%", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));   
            else
                table5.addCell(new Paragraph(new Chunk("N/D", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));      	        	
        	table5.addCell(new Paragraph(new Chunk("Producciones a escala semi-comercial:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(actividad.getProduccionesAEscalaSemiComerciales() != null)
            	table5.addCell(new Paragraph(new Chunk(actividad.getProduccionesAEscalaSemiComerciales()+"%", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));  
            else
                table5.addCell(new Paragraph(new Chunk("N/D", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));        
        	table5.addCell(new Paragraph(new Chunk("Docencia y capacitación:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(actividad.getDocenciaCapacitacion() != null)
        	    table5.addCell(new Paragraph(new Chunk(actividad.getDocenciaCapacitacion()+"%", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
            else
                table5.addCell(new Paragraph(new Chunk("N/D", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));       
        	table5.addCell(new Paragraph(new Chunk("Otros:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(actividad.getOtros() != null)
        	    table5.addCell(new Paragraph(new Chunk(actividad.getOtros()+"%", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));              
            else
                table5.addCell(new Paragraph(new Chunk("N/D", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));	        	
        	table5.addCell(new Paragraph(new Chunk("", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
        	table5.addCell(new Paragraph(new Chunk("", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));        	  
            table5.setSpacingBefore(5);
            table5.setSpacingAfter(5);        	 
        	document.add(table5);    
            }
            catch(NullPointerException e){ 
                System.out.println("Los campos son nulos");
            }
 			//document.add(nuevalinea);        	
            try{
 	 		PdfPTable table6 = new PdfPTable(2);
 	 		table6.setWidthPercentage(anchoTabla);
 	 		table6.setWidths(new int[]{1, 3});
    		Paragraph tituloTabla6 = new Paragraph("");
        	tituloTabla6.add(new Chunk("Actividades", FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.BOLD,new BaseColor(255, 255, 255))));
             cell = new PdfPCell(tituloTabla6);		 	 	 		
        	cell.setColspan(2);
             cell.setBackgroundColor(new BaseColor(60, 59, 84));       	
        	table6.addCell(cell);
        	table6.addCell(new Paragraph(new Chunk("Principales capacidades y habilidades:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(actividad.getCapacidadesHabilidades() != null)
        	    table6.addCell(new Paragraph(new Chunk(actividad.getCapacidadesHabilidades(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87,
 87)))));
            else
                table6.addCell(new Paragraph(new Chunk("No Disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));	
        	table6.addCell(new Paragraph(new Chunk("Línea de investigación 1:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(actividad.getLineaInvUno() != null)
        	    table6.addCell(new Paragraph(new Chunk(actividad.getLineaInvUno(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
            else
                table6.addCell(new Paragraph(new Chunk("No Disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));	
        	table6.addCell(new Paragraph(new Chunk("Línea de investigación 2:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(actividad.getLineaInvDos() != null)
        	    table6.addCell(new Paragraph(new Chunk(actividad.getLineaInvDos(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
            else
                table6.addCell(new Paragraph(new Chunk("No Disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));	
        	table6.addCell(new Paragraph(new Chunk("Línea de investigación 3:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(actividad.getLineaInvTres() != null)
        	    table6.addCell(new Paragraph(new Chunk(actividad.getLineaInvTres(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
            else
                table6.addCell(new Paragraph(new Chunk("No Disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));	
        	table6.addCell(new Paragraph(new Chunk("Inversión tota estimada:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(actividad.getInversionTotal() != null)
        	    table6.addCell(new Paragraph(new Chunk(actividad.getInversionTotal(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
            else
                table6.addCell(new Paragraph(new Chunk("No Disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));	
        	table6.addCell(new Paragraph(new Chunk("Area estimada de laboratorio:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(actividad.getAreaEstimada() != null)
        	    table6.addCell(new Paragraph(new Chunk(actividad.getAreaEstimada(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));	 
            else
                table6.addCell(new Paragraph(new Chunk("No Disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));	
        	table6.addCell(new Paragraph(new Chunk("Costo anual de mantenimiento:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(actividad.getCostoAnualMantenimiento() != null)
        	    table6.addCell(new Paragraph(new Chunk(actividad.getCostoAnualMantenimiento(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));     
            else
                table6.addCell(new Paragraph(new Chunk("No Disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));	
         table6.setSpacingBefore(5);
         table6.setSpacingAfter(5);        	 	
        	document.add(table6);    
            }
            catch(NullPointerException e){ 
                System.out.println("Los campos son nulos");
            }
 			//document.add(nuevalinea);        	        	   
		 	PdfPTable table7 = new PdfPTable(3);
		 	table7.setWidthPercentage(anchoTabla);
    		Paragraph tituloTabla7 = new Paragraph("");
      	tituloTabla7.add(new Chunk("Equipo principal", FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.BOLD,new BaseColor(255, 255, 255))));
         cell = new PdfPCell(tituloTabla7);		 	 	 		 	
       	cell.setColspan(3);
         cell.setBackgroundColor(new BaseColor(60, 59, 84));       	
        	table7.addCell(cell);
        	
       	cell = new PdfPCell(new Paragraph(new Chunk("Nombre", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));	  	 	
 			cell.setHorizontalAlignment (Element.ALIGN_CENTER);       	
        	table7.addCell(cell);

        	cell = new PdfPCell(new Paragraph(new Chunk("Descripción", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));	  	 	
 			cell.setHorizontalAlignment (Element.ALIGN_CENTER);       	
        	table7.addCell(cell);

        	cell = new PdfPCell(new Paragraph(new Chunk("Año adquisición", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));	  	 	
 			cell.setHorizontalAlignment (Element.ALIGN_CENTER);       	
        	table7.addCell(cell);
        	
 /*      	cell = new PdfPCell(new Paragraph(new Chunk("Costo [pesos]", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));	  	 	
 			cell.setHorizontalAlignment (Element.ALIGN_CENTER);       	
        	table7.addCell(cell);        	*/
        Iterator iterEquipo = equiposLaboratorio.iterator();
        while (iterEquipo.hasNext()){
            EquipoLaboratorio equipoLaboratorio = (EquipoLaboratorio) iterEquipo.next();	
            
            String nombreEquipo = "";
            String fichaTecnica="";
            String anio="";        
            
            if(equipoLaboratorio != null) {
                if(equipoLaboratorio.getCategoriaEquipo() != null) {
                	nombreEquipo+=equipoLaboratorio.getCategoriaEquipo().getNombreDeEquipo();
                }
                anio+= String.valueOf(equipoLaboratorio.getAnioDeAdquisicion());
                fichaTecnica+= String.valueOf(equipoLaboratorio.getFichaTecnica());
            }

            cell = new PdfPCell(new Paragraph(new Chunk(nombreEquipo, FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD,new BaseColor(87, 87, 87)))));	  	 	
 			cell.setHorizontalAlignment (Element.ALIGN_CENTER);       	
        	table7.addCell(cell);

        	cell = new PdfPCell(new Paragraph(new Chunk(fichaTecnica, FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD,new BaseColor(87, 87, 87)))));	  	 	
 			cell.setHorizontalAlignment (Element.ALIGN_CENTER);       	
        	table7.addCell(cell);

        	cell = new PdfPCell(new Paragraph(new Chunk(anio, FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD,new BaseColor(87, 87, 87)))));	  	 	
 			cell.setHorizontalAlignment (Element.ALIGN_CENTER);       	
        	table7.addCell(cell);
         }
         table7.setSpacingBefore(5);
         table7.setSpacingAfter(5);        	   
        	document.add(table7);   

 			//document.add(nuevalinea);        	       	        	   
/*	 		PdfPTable table8 = new PdfPTable(2);
	 		table8.setWidthPercentage(anchoTabla);
	 		table8.setWidths(new int[]{1, 3});
    		Paragraph tituloTabla8 = new Paragraph("");
      	tituloTabla8.add(new Chunk("Situación tecnológica", FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.BOLD,new BaseColor(255, 255, 255))));
         cell = new PdfPCell(tituloTabla8);		 		
       	cell.setColspan(2);
         cell.setBackgroundColor(new BaseColor(60, 59, 84));       	
        	table8.addCell(cell);
        	table8.addCell(new Paragraph(new Chunk("Situación del laboratorio con respecto a sus pares a nivel nacional o internacional:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
        	table8.addCell(new Paragraph(new Chunk("", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        	table8.addCell(new Paragraph(new Chunk("Cuenta con los insumos y recursos necesarios para la óptima operación del laboratorio:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
        	table8.addCell(new Paragraph(new Chunk("", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        	table8.addCell(new Paragraph(new Chunk("Capacidad utilizada para el uso del laboratorio:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
        	table8.addCell(new Paragraph(new Chunk("", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        	table8.addCell(new Paragraph(new Chunk("Suficiencia presupuestal para el mantenimiento del laboratorio:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
        	table8.addCell(new Paragraph(new Chunk("", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        	table8.addCell(new Paragraph(new Chunk("Disponibilidad de personal técnico capacitado para el mantenimiento del laboratorio:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
        	table8.addCell(new Paragraph(new Chunk("", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87))))); 	
         table8.setSpacingBefore(5);
         table8.setSpacingAfter(5);        	
        	document.add(table8);*/
 			//document.add(nuevalinea);        	
         if(fichaTecnicaLaboratorio.getPermiteServicio()){
	 	    PdfPTable table9 = new PdfPTable(2);
	 		table9.setWidthPercentage(anchoTabla);
	 	    table9.setWidths(new int[]{1, 3});	 		
    		Paragraph tituloTabla9 = new Paragraph("");
      	    tituloTabla9.add(new Chunk("Contacto para acceder a las capacidades y servicios del laboratorio", FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.BOLD,new BaseColor(255, 255, 255))));
            cell = new PdfPCell(tituloTabla9);	 		
            cell.setColspan(2);
            cell.setBackgroundColor(new BaseColor(60, 59, 84));       	
        	table9.addCell(cell);
/*        	table9.addCell(new Paragraph(new Chunk("Capcidad disponible actual para ampliar sus operaciones:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
        	table9.addCell(new Paragraph(new Chunk("", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        	table9.addCell(new Paragraph(new Chunk("Acceso y uso a las instalaciones a terceros:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
        	table9.addCell(new Paragraph(new Chunk("", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        	table9.addCell(new Paragraph(new Chunk("Uso de las instalaciones vía terceros:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
        	table9.addCell(new Paragraph(new Chunk("", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));*/
        	table9.addCell(new Paragraph(new Chunk("Nombre:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(datoGeneral.getNombreDeContacto() != null)
            	table9.addCell(new Paragraph(new Chunk(datoGeneral.getNombreDeContacto(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
            else
                table9.addCell(new Paragraph(new Chunk("No Disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        	table9.addCell(new Paragraph(new Chunk("Puesto:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(datoGeneral.getPuestoDeContacto() != null)
            	table9.addCell(new Paragraph(new Chunk(datoGeneral.getPuestoDeContacto(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87,87,87))))); 
            else
                table9.addCell(new Paragraph(new Chunk("No Disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        	table9.addCell(new Paragraph(new Chunk("Teléfono:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(datoGeneral.getTelefonoDeContacto() != null)
        	    table9.addCell(new Paragraph(new Chunk(datoGeneral.getTelefonoDeContacto(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87))))); 	
            else
                table9.addCell(new Paragraph(new Chunk("No Disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        	table9.addCell(new Paragraph(new Chunk("Correo electrónico:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(datoGeneral.getEmail() != null)
        	    table9.addCell(new Paragraph(new Chunk(datoGeneral.getEmail(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87))))); 	      
            else
                table9.addCell(new Paragraph(new Chunk("No Disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
            table9.setSpacingBefore(5);
            table9.setSpacingAfter(5);        	  	 	
        	document.add(table9);
          }
 			//document.add(nuevalinea);        	
/*		 	PdfPTable table10 = new PdfPTable(3);
			table10.setWidthPercentage(anchoTabla);
    		Paragraph tituloTabla10 = new Paragraph("");
      	tituloTabla10.add(new Chunk("Equipo requerido", FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.BOLD,new BaseColor(255, 255, 255))));
         cell = new PdfPCell(tituloTabla10);	 				 	
       	cell.setColspan(3);
         cell.setBackgroundColor(new BaseColor(60, 59, 84));       	
        	table10.addCell(cell);

       	cell = new PdfPCell(new Paragraph(new Chunk("Nombre", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));	  	 	
 			cell.setHorizontalAlignment (Element.ALIGN_CENTER);       	
        	table10.addCell(cell);

        	cell = new PdfPCell(new Paragraph(new Chunk("Descripción", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));	  	 	
 			cell.setHorizontalAlignment (Element.ALIGN_CENTER);       	
        	table10.addCell(cell);

        	cell = new PdfPCell(new Paragraph(new Chunk("Costo estimado[pesos]", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));	  	 	
 			cell.setHorizontalAlignment (Element.ALIGN_CENTER);       	
        	table10.addCell(cell);
            Iterator equipoRequerido = equiposRequerido.iterator();
            while (equipoRequerido.hasNext()){	
                equipoRequerido.next();
                cell = new PdfPCell(new Paragraph(new Chunk("", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));	  	 	
 			cell.setHorizontalAlignment (Element.ALIGN_CENTER);       	
        	table10.addCell(cell);

        	    cell = new PdfPCell(new Paragraph(new Chunk("", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));	  	 	
 			cell.setHorizontalAlignment (Element.ALIGN_CENTER);       	
        	table10.addCell(cell);

        	    cell = new PdfPCell(new Paragraph(new Chunk("", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));	  	 	
 			cell.setHorizontalAlignment (Element.ALIGN_CENTER);       	
        	table10.addCell(cell);
            }
        	table.setSpacingBefore(5);
         //table10.setSpacingAfter(5);   
        	document.add(table10);     */  	         	             	            	   		
	    }catch(Exception e){
	        e.printStackTrace();
	    }
	    document.close();
	}


	public InputStream getInputStream() { 
		return inputStream; 
	} 
		
	public void setInputStream(InputStream inputStream) { 
		this.inputStream = inputStream; 
	} 
	
	public DescripcionLaboratorio getFichaTecnicaLaboratorio() {
		return fichaTecnicaLaboratorio;
	}

	public void setFichaTecnicaLaboratorio(DescripcionLaboratorio fichaTecnicaLaboratorio) {
		this.fichaTecnicaLaboratorio = fichaTecnicaLaboratorio;
	}
	
	public void setFichaTecnicaEquipo(DescripcionEquipo fichaTecnicaEquipo) {
		this.fichaTecnicaEquipo = fichaTecnicaEquipo;
	}
	
	public DescripcionEquipo getFichaTecnicaEquipo() {
		return this.fichaTecnicaEquipo;
	}

	public DatoGeneral getDatoGeneral() {
		return datoGeneral;
	}

	public void setDatoGeneral(DatoGeneral datoGeneral) {
		this.datoGeneral = datoGeneral;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}
	
	public EquipoLaboratorio getEquipoLab() {
		return this.equipoLab;
	}
	
	public void setEquipoLab(EquipoLaboratorio equipoLab) {
		this.equipoLab = equipoLab;
	}
	
	public void setEquiposLaboratorio(List<EquipoLaboratorio> equiposLaboratorio) {
		this.equiposLaboratorio = equiposLaboratorio;
	}
	
	public List<EquipoLaboratorio> getEquiposLaboratorio() {
		return this.equiposLaboratorio;
	}

	public List<LineasLaboratorio> getLineasLaboratorio() {
		return lineasLaboratorio;
	}

	public void setLineasLaboratorio(List<LineasLaboratorio> lineasLaboratorio) {
		this.lineasLaboratorio = lineasLaboratorio;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}
	
	public List<Acreditacion> getAcreditaciones() {
		return acreditaciones;
	}

	public void setAcreditaciones(List<Acreditacion> acreditaciones) {
		this.acreditaciones = acreditaciones;
	}
	
	public HttpServletResponse getServletResponse(){
		return response;
	}

}
