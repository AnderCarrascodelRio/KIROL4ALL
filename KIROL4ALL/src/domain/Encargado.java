package domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Encargado extends Socio{
	@OneToMany(mappedBy = "encargado")
	List<Instalacion> instalaciones = new ArrayList<>();

	public Encargado(String n, String d, String p) {
		super(n, d, p);
		rol = "encargado";
	}
	
	public void agregarInstalacion(Instalacion instalacion) {
		instalaciones.add(instalacion);
		instalacion.setEncargado(this);
	}

}
