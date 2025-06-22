package domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Sesion implements Serializable{
	@Temporal(TemporalType.TIMESTAMP)
	Date fechaInicio;
	@Temporal(TemporalType.TIMESTAMP)
	Date fechaFin;
	boolean disponible;
	int numUsuarios;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instalacion_nombre")
    @XmlIDREF
	Instalacion instalacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "actividad_nombre")
    @XmlIDREF
	Actividad actividad;
	
	@OneToMany(mappedBy = "sesion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @XmlTransient
	List<Reserva> reservas = new ArrayList<>();

	  @XmlID
	    @XmlAttribute(name = "idXml")
	    public String getIdXml() {
	        return "ses" + id;
	    }
	
	public Sesion() {}
	public Sesion(Date fechaI, Date fechaF) {
		fechaInicio = fechaI;
		fechaFin = fechaF;
		disponible = true;
		numUsuarios = 0;
	}
	
	public void agregarReserva(Reserva reserva) {
		reservas.add(reserva);
		reserva.setSesion(this);
	}
	
	
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	public void setNumUsuarios(int numUsuarios) {
		this.numUsuarios = numUsuarios;
	}
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	public void setInstalacion(Instalacion i) {
		instalacion = i;
	}
	
	public void setActividad(Actividad a) {
		actividad = a;
	}
	
	public void setUsuarios(int u) {
		numUsuarios = u;
	}
	
	public Actividad getActividad() {
		return this.actividad;
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	
	public Date getFechaFin() {
		return fechaFin;
	}
	
	public boolean getDisponible() {
		return disponible;
	}
	
	public String estaDisponible() {
		if (disponible) {return "Si";}
		else { return "No";}
	}
	
	public int getNumUsuarios() {
		return numUsuarios;
	}
	
	public List<Reserva> getReservas() {
		return reservas;
	}
	
	public Instalacion getInstalacion() {
		return instalacion;
	}
	@Override
	public String toString() {
		return actividad.getNombre() + " " + fechaInicio + " " + fechaFin + " " + disponible + " " + numUsuarios + " " + instalacion.getNombre();
	}

   
}
