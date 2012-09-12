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

@Entity
@Table(name="SIF.PS_ICT_LIN_INV_TBL")
public class LineasLaboratorio {

	@Id
	@Column(name="ICT_NUMLIN_INV_FLD")
	private Integer lineaId;
	
	@Column(name="ICT_LINEA1_FLD")
	private String linea;

	public Integer getLineaId() {
		return lineaId;
	}

	public void setLineaId(Integer lineaId) {
		this.lineaId = lineaId;
	}
}
