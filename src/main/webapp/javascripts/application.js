var heightSum=0;

$.extend({
	estaPresente: function(dom) {
		return $(dom).length;
	}
});

$(document).ready(function() {

   $('#buscar_area').bind('change', function() {
	   if($(this).val() == "00" || $(this).val() == "") {
		   //$('#page').height($('#page').height()-$('#dinDisc').height());
		   $('#dinDisc').empty();
	   } else {
		   var parametros = { "area" : $('#buscar_area').val() }
		   
		   $.post('catalogoDisciplinas', parametros, function(data) {
			   $('#dinDisc').html(data);
			   //$('#page').height($('#page').height()+$('#dinDisc').height());
		   });
	   }
   });
   
   $('#disciplina').live('change', function() {
	   if($(this).val() == "00" || $(this).val() == "") {
		   $('#page').height($('#page').height()-$('#subdisciplinasSel').height());
		   $('#subdisciplinasSel').empty();
	   } else {
		   var parametros = { "area" : $('#buscar_area').val() }

		   if($.estaPresente('#disciplina')) {
			   parametros["disciplina"] = $('#disciplina').val();
		   }
		   
		   $.post('catalogoSubdisciplinas', parametros, function(data) {
			   $('#subdisciplinasSel').html(data);
			   //$('#page').height($('#page').height()+$('#subdisciplinasSel').height());
		   });
	   }
   });
   $('#buscar_sector').live('change', function() {
	   if($(this).val() == "00" || $(this).val() == "") {
		   //$('#page').height($('#page').height()-$('#dinInst').height());
		   $('#dinInst').empty();
	   } else {
		   var parametros = { "sector" : $('#buscar_sector').val() }
		   $.post('catalogoInstituciones', parametros, function(data) {
			   $('#dinInst').html(data);
			   //$('#page').height($('#page').height()+$('#dinInst').height());
		   });
	   }
   });
   
   $('#institucion').live('change', function() {
	   if($(this).val() == "00" || $(this).val() == "") {
		   //$('#page').height($('#page').height()-$('#dependSel').height());
		   $('#dependSel').empty();
	   } else {
		   var parametros = { "institucion" : $('#institucion').val() }
		   $.post('catalogoDependencias', parametros, function(data) {
			   $('#dependSel').html(data);
			   //$('#page').height($('#page').height()+$('#dependSel').height());
		   });
	   }
	   
   });
   
   $('#dependencia').live('change', function() {
	   if($(this).val() == "00" || $(this).val() == "") {
		   //$('#page').height($('#page').height()-$('#subdependSel').height());
		   $('#subdependSel').empty();
	   } else {
		   var parametros = { "dependencia" : $('#dependencia').val() }
		   $.post('catalogoSubdependencias', parametros, function(data) {
			   $('#subdependSel').html(data);
			   //$('#page').height($('#page').height()+$('#subdependSel').height());
		   });
	   }
   });

   // Datatables config
   
/*   oTable = $('#tDatosMuestreo').dataTable( {
		"bAutoWidth": true,
		"bProcessing": true,
		
		"bFilter": false,
		"aoColumnDefs": [{ "sClass": "contenidoDatatable", "aTargets": [ "_all" ] }],
		"sPaginationType": "full_numbers",
		"oLanguage": {
			"sUrl": "localization/es_ES.txt"
		}
	});*/

   $(document).ready( function () {
	 	 $('#tDatosMuestreo').dataTable( {
	 		"fnInitComplete": function(oSettings, json) {
	 		   var dataTableHeight = $('#tDatosMuestreo').height();
	 		   var pageHeight = $('#page').height();
	 		   
	 		   var difference = pageHeight-dataTableHeight;
	 		   if(difference > 0) {
	 			   $('#page').attr('style', 'height: '+(dataTableHeight+200)+'px;');
	 		   }
	 		},
			"bProcessing": true,
			"bFilter": false,
			"sDom": 'T<"clear">rtip',
			"oTableTools": {
				"sSwfPath": "media/copy_cvs_xls_pdf.swf",
		  	"aButtons": [ {
                        "sExtends": "csv",
                        "sFileName": "SNIICyT.csv"}
                        ]
			},
   	     "aoColumnDefs": [{ "sClass": "contenidoDatatable", "aTargets": [ "_all" ] }],
		 "sPaginationType": "full_numbers",
		 "oLanguage": {
				"sUrl": "localization/es_ES.txt"
			}
		 } );
	 } );
   
   $('#nombre-equipo-toggle').live('click', function() {
	   $('.nombre-de-equipo-fld').removeClass('hidden');
	   $(this).addClass('hidden');
	   $('#nombre-lab-title').addClass('hidden');
	   $('#nombre-equipo-title').removeClass('hidden');
	   $('#nombre-lab-toggle').removeClass('hidden');
	   clearSelections();
   });
   
   $('#nombre-lab-toggle').live('click', function() {
	   $('.nombre-de-equipo-fld').addClass('hidden');
	   $(this).addClass('hidden');
	   $('#nombre-equipo-title').addClass('hidden');
	   $('#nombre-lab-title').removeClass('hidden');
	   $('#nombre-equipo-toggle').removeClass('hidden');
	   clearSelections();
   });
   
   var clearSelections = function() {
	   $('#dinInst').empty();
	   $('#dinDisc').empty();
	   $('.column-selection input').removeAttr("checked");
	   $('.column-selection .checked-default').attr("checked", "checked");
	   $('#buscar_equipoNombre').val("");
	   
	   $('#buscar_sector').val("");
	   $('#buscar_area').val("");
   }
});
