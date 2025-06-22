package domain;
 import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import configuration.Estado;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Reserva implements Serializable{
	@Id
	@GeneratedValue
	int numReserva;
	 @Temporal(TemporalType.DATE)
	Date fecha;
	boolean cancelable;
	@Enumerated(EnumType.STRING)
	Estado estado;
	
	@XmlID
	@XmlAttribute(name = "idXml")
	public String getNumReservaXml() {
	    return "res" + numReserva;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sesion")
	@XmlIDREF
	Sesion sesion;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "socio_id")
    @XmlIDREF
	Socio socio;
	
	public Reserva() {}
	public Reserva(Sesion s, Estado es) {
		fecha = new Date();
		cancelable = true;
		sesion = s;
		estado = es;
	}
	
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public void setNumReserva(int numReserva) {
		this.numReserva = numReserva;
	}
	public void setCancelable(boolean cancelable) {
		this.cancelable = cancelable;
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
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(sesion.getFechaInicio());

	    int dia = cal.get(Calendar.DAY_OF_MONTH);
	    int mes = cal.get(Calendar.MONTH) + 1; // OJO: enero = 0
	    int hora = cal.get(Calendar.HOUR_OF_DAY);

	    return "ID: " + numReserva + " " + 
	           sesion.getActividad().getNombre() + 
	           " Día: " + dia + "/" + mes + 
	           " Hora: " + hora + ":00 Lugar: " + 
	           sesion.getInstalacion().getNombre();
	}
}
