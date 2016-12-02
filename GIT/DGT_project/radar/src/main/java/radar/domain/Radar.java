package radar.domain;

import java.util.List;
import org.hibernate.HibernateException;
import edu.uclm.esi.iso2.multas.dao.GeneralDao;
import edu.uclm.esi.iso2.multas.domain.Inquiry;
import edu.uclm.esi.iso2.multas.domain.Manager;
import edu.uclm.esi.iso2.multas.domain.SanctionHolder;
import edu.uclm.esi.iso2.multas.domain.Vehicle;

public class Radar {
	private boolean state; // Estado del radar (funcionando o apagado)
	private double velocidad_max; // Velocidad maxima
	private List<Vehicle> coches; // Lista de coches
	private List<SanctionHolder> infractores; // Lista de infractores
	
	public Radar (double velocidad_max) {
		this.velocidad_max = velocidad_max;
		coches = null;
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
		int infractor; // posicion aleatoria en la lista SanctionHolder
		String direccion; // FullAddress del infractor
		int numeroExpediente;
		Inquiry infraccion; // Objeto tipo Inquiry para crear la sancion
		Vehicle aux; // Objeto tipo Vehicle para obtener la licencia
		
		state = true;
		coches = new GeneralDao<Vehicle>().findAll(Vehicle.class);
		infractores = new GeneralDao<SanctionHolder>().findAll(SanctionHolder.class);
		
		do {
			double velocidad = (double) (Math.random() * 199 + 0); // Velocidad generada aleatoriamente
			if (velocidad > velocidad_max) {
				aux = coches.get((int) Math.random() * (coches.size() - 1) + 0); // Coche seleccionado aleatoriamente de la lista de coches
				infractor = (int) Math.random() * (infractores.size() - 1) + 0; // Infractor seleccionado aleatoriamente de la lista de infractores
				direccion = infractores.get(infractor).getFullAddress();
				System.out.println("Coche: " + aux.getLicense()+ " Localizacion: " + direccion);
				// Apertura de Expediente
				numeroExpediente = Manager.get().openInquiry(aux.getLicense(), velocidad, direccion, velocidad_max);
				infraccion= new GeneralDao<Inquiry>().findById(Inquiry.class, numeroExpediente);
				infraccion.createSanctionFor(infractores.get(infractor).getDni());
			}
		} while (state); // Mientras el estado sea encendido, el radar esta capturando coches.
	}
}