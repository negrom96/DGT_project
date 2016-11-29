package radar.domain;

public class Radar {
	private boolean state;
	private double velocidad_max;

	public Radar(double velocidad_max) {
		this.velocidad_max = velocidad_max;
		// TODO Auto-generated constructor stub
	}
	public double getVelocidad_max() {
		return velocidad_max;
	}
	public void setVelocidad_max(double velocidad_max) {
		this.velocidad_max = velocidad_max;
	}
	public boolean isState() {
		return state;
	}

	public void iniciar_radar () {
		state = true;
	}
	
}