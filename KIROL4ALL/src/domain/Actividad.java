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
}
