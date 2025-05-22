package domain;
 import java.util.Date;
import otros.Estado;
import javax.persistence.*;

@Entity
public class Reserva {
	@Id
	@GeneratedValue
	int numReserva;
	Date fecha;
	boolean cancelable;
	Estado estado;
	
	@ManyToOne
	@JoinColumn(name = "sesion")
	Sesion sesion;
	
	@ManyToOne
	@JoinColumn(name = "socio_id")
	Socio socio;
	
	public Reserva(Sesion s, Estado es) {
		fecha = new Date();
		cancelable = true;
		sesion = s;
		estado = es;
	}
	
	public void setEstado(Estado est) {
		estado=est;
	}
	
	public void setSesion(Sesion s) {
		sesion=s;
	}
	
	public void setSocio(Socio s) {
		socio=s;
	}
	
	public Estado getEstado() {
		return estado;
	}
	
	public Sesion getSesion() {
		return sesion;
	}
	public Socio getSocio() {
		return socio;
	}
	public boolean getCancelable() {
		return cancelable;
	}
	
	public int getNumReserva() {
		return numReserva;
	}
	
	@Override
	public String toString() {
		return "ID: " + numReserva + " " + sesion.getActividad().getNombre() + " Día:" + sesion.getFechaInicio().getDayOfMonth() + "/" +
				sesion.getFechaInicio().getMonthValue() + " Hora:" + sesion.getFechaInicio().getHour() + ":00 Lugar:" + sesion.getInstalacion().getNombre();
	}
}
