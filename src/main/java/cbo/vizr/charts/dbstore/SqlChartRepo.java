package cbo.vizr.charts.dbstore;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface SqlChartRepo extends CrudRepository<SqlChart, Integer>{

	public SqlChart findByName(String name);

	@Query("select name from charts q")
	public List<String> findNames();

	
	

}
