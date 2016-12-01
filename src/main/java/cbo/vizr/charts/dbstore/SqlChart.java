package cbo.vizr.charts.dbstore;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity(name="charts")
public class SqlChart {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Basic
	@NotNull
	private String name;
	
	@Basic
	private String axisX;
	
	@ElementCollection
	private List<String> axisY ;
	
	@Basic
	private String sql ;
	
	
	@Basic
	private String type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAxisX() {
		return axisX;
	}

	public void setAxisX(String axisX) {
		this.axisX = axisX;
	}

	public List<String> getAxisY() {
		return axisY;
	}

	public void setAxisY(List<String> axisY) {
		this.axisY = axisY;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String ttype) {
		this.type = ttype;
	}
	
}
