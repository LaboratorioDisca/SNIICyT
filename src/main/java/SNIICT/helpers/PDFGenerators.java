package SNIICT.helpers;

import java.net.URL;
import java.util.Iterator;
import java.util.List;

import SNIICT.actions.DetallesAction;
import SNIICT.models.Acreditacion;
import SNIICT.models.DatoGeneral;
import SNIICT.models.DescripcionLaboratorio;
import SNIICT.models.EquipoLaboratorio;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Image;

/*
 * Esta clase contiene métodos estáticos para la generación de PDFs modularizada
 * respecto al controlador
 */
public class PDFGenerators {
		
    public static void addLogoToDocument(Document document, DetallesAction action) {
    	try { 
    		Image logo = Image.getInstance(new URL(action.getText("application.url")+"/SNIICyt/images/logo.jpg"));
    		logo.scalePercent(50);
    		logo.setAlignment(Element.ALIGN_CENTER);
    		document.add(logo); 
    	} catch (Exception bex) {
    		
    	}
    }
    
    public static Document equipoPDF(DetallesAction action) throws Exception {
    	int anchoTabla=100;
		Paragraph nuevalinea = new Paragraph(" ");
		Document document = new Document(PageSize.LETTER);
		
		DescripcionLaboratorio fichaTecnicaLaboratorio = action.getFichaTecnicaLaboratorio();

	    document.addAuthor("SNIICyT CONACYT");
	    document.addCreator("SNIICyT CONACYT");
	    document.open();
	      
	    addLogoToDocument(document, action);


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

	    document.close();
	    
	    return document;
    }
    
	public static Document fichaTecnicaPDF(DetallesAction action) throws Exception {
		int anchoTabla=100;
		Paragraph nuevalinea = new Paragraph(" ");
		Document document = new Document(PageSize.LETTER);
	    
	    document.addAuthor("SNIICyT CONACYT");
	    document.addCreator("SNIICyT CONACYT");
	    document.open();

	    addLogoToDocument(document, action);

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
        if(action.getFichaTecnicaLaboratorio().getDescripcionInstitucion() != null) 
            table.addCell(new Paragraph(new Chunk(action.getFichaTecnicaLaboratorio().getDescripcionInstitucion(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        else
            table.addCell(new Paragraph(new Chunk("No disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
       	table.addCell(new Paragraph(new Chunk("2do Nivel:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
        if(action.getFichaTecnicaLaboratorio().getDescripcionDependencia() != null)
        	table.addCell(new Paragraph(new Chunk(action.getFichaTecnicaLaboratorio().getDescripcionDependencia(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        else
            table.addCell(new Paragraph(new Chunk("No disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        table.addCell(new Paragraph(new Chunk("3er Nivel:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
        if(action.getFichaTecnicaLaboratorio().getDescripcionSector() != null) 
            table.addCell(new Paragraph(new Chunk(action.getFichaTecnicaLaboratorio().getDescripcionSector(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));  
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
        table2.addCell(new Paragraph(new Chunk(action.getFichaTecnicaLaboratorio().getNombreLaboratorio(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        table2.addCell(new Paragraph(new Chunk("Area:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
        table2.addCell(new Paragraph(new Chunk(action.getFichaTecnicaLaboratorio().getDescripcionArea(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        table2.addCell(new Paragraph(new Chunk("Disciplina:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
        if(action.getFichaTecnicaLaboratorio().getDescripcionDisciplina() != null)
        	table2.addCell(new Paragraph(new Chunk(action.getFichaTecnicaLaboratorio().getDescripcionDisciplina(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        else
        	table2.addCell(new Paragraph(new Chunk("No disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        table2.addCell(new Paragraph(new Chunk("Subdisciplina:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
        
        if(action.getFichaTecnicaLaboratorio().getDescripcionSubdisciplina() != null)
        	table2.addCell(new Paragraph(new Chunk(action.getFichaTecnicaLaboratorio().getDescripcionSubdisciplina(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        else
        	table2.addCell(new Paragraph(new Chunk("No disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        table2.addCell(new Paragraph(new Chunk("Calle y número:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
        table2.addCell(new Paragraph(new Chunk(action.getDatoGeneral().getCalle(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        table2.addCell(new Paragraph(new Chunk("Colonia:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
        table2.addCell(new Paragraph(new Chunk(action.getDatoGeneral().getColonia(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));	 
        table2.addCell(new Paragraph(new Chunk("Municipio/Delegación:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
        table2.addCell(new Paragraph(new Chunk(action.getDatoGeneral().getCiudad(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        table2.addCell(new Paragraph(new Chunk("Estado:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
        table2.addCell(new Paragraph(new Chunk(action.getDatoGeneral().getEstado(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        table2.addCell(new Paragraph(new Chunk("Código Postal:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
        table2.addCell(new Paragraph(new Chunk(action.getDatoGeneral().getCodigoPostal(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));	 
        table2.addCell(new Paragraph(new Chunk("Página Web:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
        if(action.getDatoGeneral().getPaginaWeb() != null)
           table2.addCell(new Paragraph(new Chunk(action.getDatoGeneral().getPaginaWeb(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));	 
        else
           table2.addCell(new Paragraph(new Chunk("No disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));	
        table2.setSpacingBefore(5);
        table2.setSpacingAfter(5);
        document.add(table2);       
      	
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
        Iterator iter = action.getAcreditaciones().iterator();
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

            if(action.getActividad().getAnalisisYCaracterizacion() != null)
                table5.addCell(new Paragraph(new Chunk(action.getActividad().getAnalisisYCaracterizacion()+"%", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
            else
                    table5.addCell(new Paragraph(new Chunk("N/D", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        	table5.addCell(new Paragraph(new Chunk("Pruebas cualquier tipo:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(action.getActividad().getPruebasCualquierTipo() != null)
                table5.addCell(new Paragraph(new Chunk(action.getActividad().getPruebasCualquierTipo()+"%", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));   
            else
                table5.addCell(new Paragraph(new Chunk("N/D", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));     	        	
        	table5.addCell(new Paragraph(new Chunk("Investigación científica básica:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(action.getActividad().getInvestigacionCientificaBasica() != null)
        	    table5.addCell(new Paragraph(new Chunk(action.getActividad().getInvestigacionCientificaBasica()+"%", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
            else
                table5.addCell(new Paragraph(new Chunk("N/D", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));     
        	table5.addCell(new Paragraph(new Chunk("Investigación aplicada:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(action.getActividad().getInvestigacionAplicada() != null)
               	table5.addCell(new Paragraph(new Chunk(action.getActividad().getInvestigacionAplicada()+"%", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
            else
                table5.addCell(new Paragraph(new Chunk("N/D", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));     
        	table5.addCell(new Paragraph(new Chunk("Desarrollo experimental:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(action.getActividad().getDesarrolloExperimental() != null)
        	    table5.addCell(new Paragraph(new Chunk(action.getActividad().getDesarrolloExperimental()+"%", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
            else
                table5.addCell(new Paragraph(new Chunk("N/D", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));             	        	
        	table5.addCell(new Paragraph(new Chunk("Desarrollo de productos, procesos y equipos:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(action.getActividad().getDesarrolloProductosProcesosEquipos() != null)
        	    table5.addCell(new Paragraph(new Chunk(action.getActividad().getDesarrolloProductosProcesosEquipos()+"%", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));        	
            else
                table5.addCell(new Paragraph(new Chunk("N/D", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));    
        	table5.addCell(new Paragraph(new Chunk("Elaboración de prototipos:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(action.getActividad().getElaboracionPrototipo() != null)
            	table5.addCell(new Paragraph(new Chunk(action.getActividad().getElaboracionPrototipo()+"%", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
            else
                table5.addCell(new Paragraph(new Chunk("N/D", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));   
        	table5.addCell(new Paragraph(new Chunk("Producciones a escala piloto:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(action.getActividad().getProduccionesEscalaPiloto() != null)
        	    table5.addCell(new Paragraph(new Chunk(action.getActividad().getProduccionesEscalaPiloto()+"%", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));   
            else
                table5.addCell(new Paragraph(new Chunk("N/D", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));      	        	
        	table5.addCell(new Paragraph(new Chunk("Producciones a escala semi-comercial:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(action.getActividad().getProduccionesAEscalaSemiComerciales() != null)
            	table5.addCell(new Paragraph(new Chunk(action.getActividad().getProduccionesAEscalaSemiComerciales()+"%", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));  
            else
                table5.addCell(new Paragraph(new Chunk("N/D", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));        
        	table5.addCell(new Paragraph(new Chunk("Docencia y capacitación:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(action.getActividad().getDocenciaCapacitacion() != null)
        	    table5.addCell(new Paragraph(new Chunk(action.getActividad().getDocenciaCapacitacion()+"%", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
            else
                table5.addCell(new Paragraph(new Chunk("N/D", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));       
        	table5.addCell(new Paragraph(new Chunk("Otros:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(action.getActividad().getOtros() != null)
        	    table5.addCell(new Paragraph(new Chunk(action.getActividad().getOtros()+"%", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));              
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

            try{
 	 		PdfPTable table6 = new PdfPTable(2);
 	 		table6.setWidthPercentage(anchoTabla);
 	 		table6.setWidths(new int[]{1, 3});
    		Paragraph tituloTabla6 = new Paragraph("");
        	tituloTabla6.add(new Chunk("action.getActividad()es", FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.BOLD,new BaseColor(255, 255, 255))));
             cell = new PdfPCell(tituloTabla6);		 	 	 		
        	cell.setColspan(2);
             cell.setBackgroundColor(new BaseColor(60, 59, 84));       	
        	table6.addCell(cell);
        	table6.addCell(new Paragraph(new Chunk("Principales capacidades y habilidades:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(action.getActividad().getCapacidadesHabilidades() != null)
        	    table6.addCell(new Paragraph(new Chunk(action.getActividad().getCapacidadesHabilidades(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87,
 87)))));
            else
                table6.addCell(new Paragraph(new Chunk("No Disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));	
        	table6.addCell(new Paragraph(new Chunk("Línea de investigación 1:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(action.getActividad().getLineaInvUno() != null)
        	    table6.addCell(new Paragraph(new Chunk(action.getActividad().getLineaInvUno(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
            else
                table6.addCell(new Paragraph(new Chunk("No Disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));	
        	table6.addCell(new Paragraph(new Chunk("Línea de investigación 2:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(action.getActividad().getLineaInvDos() != null)
        	    table6.addCell(new Paragraph(new Chunk(action.getActividad().getLineaInvDos(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
            else
                table6.addCell(new Paragraph(new Chunk("No Disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));	
        	table6.addCell(new Paragraph(new Chunk("Línea de investigación 3:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(action.getActividad().getLineaInvTres() != null)
        	    table6.addCell(new Paragraph(new Chunk(action.getActividad().getLineaInvTres(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
            else
                table6.addCell(new Paragraph(new Chunk("No Disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));	
        	table6.addCell(new Paragraph(new Chunk("Inversión tota estimada:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(action.getActividad().getInversionTotal() != null)
        	    table6.addCell(new Paragraph(new Chunk(action.getActividad().getInversionTotal(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
            else
                table6.addCell(new Paragraph(new Chunk("No Disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));	
        	table6.addCell(new Paragraph(new Chunk("Area estimada de laboratorio:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(action.getActividad().getAreaEstimada() != null)
        	    table6.addCell(new Paragraph(new Chunk(action.getActividad().getAreaEstimada(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));	 
            else
                table6.addCell(new Paragraph(new Chunk("No Disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));	
        	table6.addCell(new Paragraph(new Chunk("Costo anual de mantenimiento:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(action.getActividad().getCostoAnualMantenimiento() != null)
        	    table6.addCell(new Paragraph(new Chunk(action.getActividad().getCostoAnualMantenimiento(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));     
            else
                table6.addCell(new Paragraph(new Chunk("No Disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));	
         table6.setSpacingBefore(5);
         table6.setSpacingAfter(5);        	 	
        	document.add(table6);    
            }
            catch(NullPointerException e){ 
                System.out.println("Los campos son nulos");
            }
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
        	
        Iterator iterEquipo = action.getEquiposLaboratorio().iterator();
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
       	
         if(action.getFichaTecnicaLaboratorio().getPermiteServicio()){
	 	    PdfPTable table9 = new PdfPTable(2);
	 		table9.setWidthPercentage(anchoTabla);
	 	    table9.setWidths(new int[]{1, 3});	 		
    		Paragraph tituloTabla9 = new Paragraph("");
      	    tituloTabla9.add(new Chunk("Contacto para acceder a las capacidades y servicios del laboratorio", FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.BOLD,new BaseColor(255, 255, 255))));
            cell = new PdfPCell(tituloTabla9);	 		
            cell.setColspan(2);
            cell.setBackgroundColor(new BaseColor(60, 59, 84));       	
        	table9.addCell(cell);
        	table9.addCell(new Paragraph(new Chunk("Nombre:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(action.getDatoGeneral().getNombreDeContacto() != null)
            	table9.addCell(new Paragraph(new Chunk(action.getDatoGeneral().getNombreDeContacto(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
            else
                table9.addCell(new Paragraph(new Chunk("No Disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        	table9.addCell(new Paragraph(new Chunk("Puesto:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(action.getDatoGeneral().getPuestoDeContacto() != null)
            	table9.addCell(new Paragraph(new Chunk(action.getDatoGeneral().getPuestoDeContacto(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87,87,87))))); 
            else
                table9.addCell(new Paragraph(new Chunk("No Disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        	table9.addCell(new Paragraph(new Chunk("Teléfono:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(action.getDatoGeneral().getTelefonoDeContacto() != null)
        	    table9.addCell(new Paragraph(new Chunk(action.getDatoGeneral().getTelefonoDeContacto(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87))))); 	
            else
                table9.addCell(new Paragraph(new Chunk("No Disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
        	table9.addCell(new Paragraph(new Chunk("Correo electrónico:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(117, 53, 153)))));
            if(action.getDatoGeneral().getEmail() != null)
        	    table9.addCell(new Paragraph(new Chunk(action.getDatoGeneral().getEmail(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87))))); 	      
            else
                table9.addCell(new Paragraph(new Chunk("No Disponible", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new BaseColor(87, 87, 87)))));
            table9.setSpacingBefore(5);
            table9.setSpacingAfter(5);        	  	 	
        	document.add(table9);
          }

	    document.close();
	    return document;
	}
}
