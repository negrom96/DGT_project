package radar.domain;

import java.util.List;
import org.hibernate.HibernateException;
import edu.uclm.esi.iso2.multas.dao.DriverDao;
import edu.uclm.esi.iso2.multas.dao.GeneralDao;
import edu.uclm.esi.iso2.multas.dao.OwnerDao;
import edu.uclm.esi.iso2.multas.domain.Driver;
import edu.uclm.esi.iso2.multas.domain.Inquiry;
import edu.uclm.esi.iso2.multas.domain.Manager;
import edu.uclm.esi.iso2.multas.domain.Owner;
import edu.uclm.esi.iso2.multas.domain.Sanction;
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
	public List<Vehicle> get_coches () {
		return coches;
	}
	
	public void desconectar () {
		state = false;
	}
	
	public void iniciar_radar () throws HibernateException {
		int infractor; // posicion aleatoria en la lista SanctionHolder
		String direccion; // FullAddress del infractor
		int numeroExpediente;
		Inquiry infraccion; // Objeto tipo Inquiry para crear la sancion
		Vehicle aux; // Objeto tipo Vehicle para obtener la licencia
		Sanction sancion; // Objeto Sancion 
		
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
				sancion = infraccion.createSanctionFor(infractores.get(infractor).getDni());
				
				pagarsancion(sancion);
				
			}
		} while (state); // Mientras el estado sea encendido, el radar esta capturando coches.
	}
	public void pagarsancion (Sanction sancion) {
		Driver conductor; // Objeto Driver que almacenará el conductor causante de la infraccion
		int puntos_actuales; // Variable numerica que almacena los puntos del conductor una vez se le aplica la sancion
		conductor = new DriverDao().findByDni(sancion.getSanctionHolder().getDni()); // Busqueda del conductor de la infraccion a partir del dni del infractor.
		puntos_actuales = conductor.getPoints() - sancion.getPoints(); // Calculo de puntos restantes del conductor.
		
			if (puntos_actuales >= 0) {
				conductor.setPoints(puntos_actuales); // Cambio de puntos
				sancion.pay(); // Pago de sancion
				System.out.println("Conductor: " + conductor.getDni() + "\nCantidad (€): " + sancion.getAmount() + "\nPuntos restantes: " + conductor.getPoints() + "\nFecha: " + sancion.getDateOfPayment());
			}
			else { // El numero de puntos es menor que 0.
				System.out.println("Error. El conductor " + conductor.getDni() + " no tiene suficientes puntos.");
			}
	}
	public void cambiarpropietario (String Licencia, String DNI) {
		Owner propietario;
		Vehicle coche = null;
		
		propietario = new OwnerDao().findByDni(DNI);
		
		for (int i=0; i < coches.size(); i++) {
			if (coches.get(i).getLicense().equals(Licencia)) {
				coche = coches.get(i);
				coche.setOwner(propietario);
			}
		}
		if (coche == null)
			System.out.println("Error. La Licencia " + Licencia + " no ha sido encontrada.");
	}
}