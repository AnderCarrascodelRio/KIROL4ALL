package domain;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Actividad {
	@Id
	String nombre;
	int exigencia; //1 a 5
	double precioHora;
	
	@OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL)
	List<Sesion> sesiones = new ArrayList<>();
	
	public Actividad(String n, int e, double d) {
		nombre = n;
		exigencia = e;
		precioHora = d;
	}
	
	public void agregarSesion(Sesion sesion) {
		sesiones.add(sesion);
		sesion.setActividad(this);
	}
	
	public String getNombre() {
		return nombre;
	}
	public int getExigencia() {
		return exigencia;
	}
	public double getPrecioHora() {
		return precioHora;
	}
	public List<Sesion> getSesiones() {
		return sesiones;
	}
	
	public void setNombre(String n) {
		nombre=n;
	}
	
	public void setExigencia(int ex) {
		exigencia = ex;
	}
	public void setPrecioHora(double p) {
		precioHora = p;
	}
	public void setSesiones(List<Sesion> l) {
		sesiones = l;
	}
}
