package radar.domain;

import java.util.List;

import org.hibernate.HibernateException;
import edu.uclm.esi.iso2.multas.dao.GeneralDao;
import edu.uclm.esi.iso2.multas.domain.Manager;
import edu.uclm.esi.iso2.multas.domain.SanctionHolder;
import edu.uclm.esi.iso2.multas.domain.Vehicle;

public class Radar {
	private boolean state;
	private double velocidad_max;
	
	private List<Vehicle> coches;
	private List<SanctionHolder> infracciones;
	
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
		String direccion = null;
		int numeroExpediente;
		Vehicle aux;
		state = true;
		coches = new GeneralDao<Vehicle>().findAll(Vehicle.class);
		infracciones = new GeneralDao<SanctionHolder>().findAll(SanctionHolder.class);
		do {
			double velocidad = speed_random();
			if (velocidad > velocidad_max) {
				aux = coches.get((int) Math.random() * (coches.size() - 1) + 0);
				direccion = infracciones.get((int) Math.random() * (infracciones.size() - 1) + 0).getFullAddress();
				System.out.println("Coche: " + aux.getLicense()+ " Localizacion: " + direccion);
				// Apertura de Expediente
				numeroExpediente = Manager.get().openInquiry(aux.getLicense(), velocidad, direccion, velocidad_max);
			}
		} while (state);
	}
	public double speed_random () {
		double speed = 0.0;
		speed = (double) (Math.random() * 199 + 0);
		return speed;
	}
}