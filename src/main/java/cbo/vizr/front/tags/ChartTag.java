package cbo.vizr.front.tags ;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ChartTag extends SimpleTagSupport {

	private String name = UUID.randomUUID().toString();
	private boolean load = true;
	
	@Override
	public void doTag() throws JspException, IOException {
	    JspWriter out = getJspContext().getOut();
	    
	    out.println("<canvas id=\""+name+"\" />");
	    if(load){
	    	out.println("<script>loadChart('/vizr/chart/"+ name+"','"+name+"');</script>");
	    }
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public boolean isLoad() {
		return load;
	}


	public void setLoad(boolean load) {
		this.load = load;
	}

	
	
	
}
