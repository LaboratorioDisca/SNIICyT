package SNIICT.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import SNIICT.utils.Hibernatable;
import SNIICT.models.CategoriaEquipo;

// SIF.PS_ICT_EQUILAB_TBL
@Entity
@Table(name="SIF.PS_ICT_EQUILAB_TBL")
public class EquipoLaboratorio {

	public static String tableName="SIF.PS_ICT_EQUILAB_TBL";
	
	@Id
	@Column(name="ICT_ID_EQUIPO_FLD")
	private Integer equipoId;
	
	@Column(name="ICT_FICHA_TEC_FLD")
	private String fichaTecnica;
	
	@Column(name="ICT_COS_ESTIM_FLD")
	private Float costoEstimado;
	
	@Column(name="ICT_ANIOADQUIS_FLD")
	private Integer anioDeAdquisicion;
			
	public CategoriaEquipo getCategoriaEquipo() {
		Session s = Hibernatable.getSession();
		s.beginTransaction();
		
		// SIF.PS_ICT_CAT_EQU_TBL
		SQLQuery queryCat = s.createSQLQuery("SELECT * FROM PS_ICT_CAT_EQU_TBL WHERE ICT_ID_EQUIPO_FLD = :equipoId");
		queryCat.addEntity(CategoriaEquipo.class);
		queryCat.setString("equipoId", String.valueOf(equipoId));
		
		CategoriaEquipo ce = (CategoriaEquipo) queryCat.list().get(0);
		s.getTransaction().commit();

		return ce;
	}
	
	public String getFichaTecnica() {
		return this.fichaTecnica;
	}
	
	public void setFichaTecnica(String fichaTecnica) {
		this.fichaTecnica = fichaTecnica;
	}
	
	public Integer getEquipoId() {
		return this.equipoId;
	}
	
	public void setEquipoId(Integer equipoId) {
		this.equipoId = equipoId;
	}
	
	public String getCostoEstimado() {
		if(this.costoEstimado == null) {
			return "---";
		} else {
			return "$ " + String.valueOf(this.costoEstimado) + " MXN";
		}
	}
	
	public void setCostoEstimado(Float costoEstimado) {
		this.costoEstimado = costoEstimado;
	}
	
	public String getAnioDeAdquisicion() {
		if(this.anioDeAdquisicion == null) {
			return "---";
		} else {
			return String.valueOf(this.anioDeAdquisicion);
		}
	}
	
	public void setAnioDeAdquisicion(Integer anioDeAdquisicion) {
		this.anioDeAdquisicion = anioDeAdquisicion;
	}
}
