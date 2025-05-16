package domain;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Instalacion {
	@Id
	String nombre;
	int aforoMax;
	
	@OneToMany(mappedBy = "instalacion", cascade = CascadeType.ALL)
	List<Sesion> sesiones = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "encargado_id")
	Encargado encargado;
	
	
	public Instalacion(String n, int a) {
		nombre = n;
		aforoMax = a;
		sesiones = new ArrayList<>();
	}
	
	public void agregarSesion(Sesion sesion) {
		sesiones.add(sesion);
		sesion.setInstalacion(this);
		
	}
	
	public void setEncargado(Encargado e) {
		encargado = e;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getAforo() {
		return aforoMax;
	}
}
