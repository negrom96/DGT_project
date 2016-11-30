package radar.domain;

import java.util.ArrayList;
import edu.uclm.esi.iso2.multas.domain.Vehicle;

public class Radar {
	private boolean state;
	private double velocidad_max;
	
	private ArrayList<Vehicle> coches;

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