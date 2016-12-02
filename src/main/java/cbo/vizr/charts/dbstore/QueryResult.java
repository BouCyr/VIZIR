package cbo.vizr.charts.dbstore;

import java.util.ArrayList;
import java.util.List;

public class QueryResult {

	private String sql;
	private String message;
	private boolean error;
	private List<String> headers = new ArrayList<>();
	private List<List<String>> rows = new ArrayList<>();
	
	public QueryResult(){
		super();
	}
	

	
	public QueryResult(String sql, boolean error, String message, List<String> headers, List<List<String>> rows) {
		super();
		this.sql = sql;
		this.error = error;
		this.message = message;
		this.headers = headers;
		this.rows = rows;
	}



	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<String> getHeaders() {
		return headers;
	}
	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}
	public List<List<String>> getRows() {
		return rows;
	}
	public void setRows(List<List<String>> rows) {
		this.rows = rows;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}
	
	
	
	
}
