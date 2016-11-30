package radar.domain;

import java.util.List;

import org.hibernate.HibernateException;

import edu.uclm.esi.iso2.multas.dao.GeneralDao;
import edu.uclm.esi.iso2.multas.domain.Vehicle;

public class Radar {
	private boolean state;
	private double velocidad_max;
	
	private List<Vehicle> coches;
	
	public Radar (double velocidad_max) {
		this.velocidad_max = velocidad_max;
		coches = null;
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
	public void iniciar_radar () throws HibernateException {
		state = true;
		coches = new GeneralDao().findAll(Vehicle.class);
		do {
			double velocidad = speed_random();
			Vehicle aux;
			if (velocidad > velocidad_max) {
				aux = get_vehicles().get((int) Math.random() * (coches.size() - 1) + 0);
				System.out.println("Coche: " + aux.getLicense());
			}
		} while (state);
	}
	public double speed_random () {
		double speed = 0.0;
		speed = (double) (Math.random() * 199 + 0);
		return speed;
	}
	
	public List<Vehicle> get_vehicles () {
		return coches;
	}
}