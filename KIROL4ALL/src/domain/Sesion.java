package domain;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

@Entity
public class Sesion {
	LocalDateTime fechaInicio;
	LocalDateTime fechaFin;
	boolean disponible;
	int numUsuarios;
	
	@ManyToOne
	@JoinColumn(name = "instalacion_nombre")
	Instalacion instalacion;
	
	@ManyToOne
	@JoinColumn(name = "actividad_nombre")
	Actividad actividad;
	
	@OneToMany(mappedBy = "sesion", cascade = CascadeType.ALL)
	List<Reserva> reservas = new ArrayList<>();
	
	
	public Sesion(LocalDateTime fechaI, LocalDateTime fechaF) {
		fechaInicio = fechaI;
		fechaFin = fechaF;
		disponible = true;
		numUsuarios = 0;
	}
	
	public void agregarReserva(Reserva reserva) {
		reservas.add(reserva);
		reserva.setSesion(this);
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
	
	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}
	
	public LocalDateTime getFechaFin() {
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
