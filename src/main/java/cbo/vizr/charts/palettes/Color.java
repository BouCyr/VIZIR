package cbo.vizr.charts.palettes;

public class Color {

	
	private int r;
	private int g;
	private int b;
	
	private float a;

	public Color(int r, int g, int b){
		this(r,g,b,0.2f);
	}

	public Color(int r, int g, int b, float a) {
		this.r = r;
		this.g = g;
		this.b = b;
		
		this.a = a;
	}
	
	public String toRgb(){
		return "rgb("+r+","+g+","+b+")";
	}
	
	public String toRgba(){
		return "rgba("+r+","+g+","+b+","+a+")";
	}
	
	
	public int R(){
		return r;
	}
	public int G(){
		return g;
	}
	public int B(){
		return b;
	}
	public float A(){
		return a;
	}
	
	public static Color interpolate(Color one, Color two, float ratio){
		
		float rof = ((float) one.r)*ratio;
		float gof = ((float) one.g)*ratio;
		float bof = ((float) one.b)*ratio;
		
		float rtf = ((float) two.r)*(1.0f-ratio);
		float gtf = ((float) two.g)*(1.0f-ratio);
		float btf = ((float) two.b)*(1.0f-ratio);
		
		return new Color( (int) (rof + rtf), (int) (gof+gtf), (int) (bof+btf), one.a);
		
		
		
	}
}
