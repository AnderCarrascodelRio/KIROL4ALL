package domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Encargado extends Socio{
	@OneToMany(mappedBy = "encargado")
	@XmlTransient
	List<Instalacion> instalaciones = new ArrayList<>();

	public void setInstalaciones(List<Instalacion> instalaciones) {
		this.instalaciones = instalaciones;
	}

	public Encargado () {}
	public Encargado(String n, String d, String p) {
		super(n, d, p);
		rol = "encargado";
	}
	
	public void agregarInstalacion(Instalacion instalacion) {
		instalaciones.add(instalacion);
		instalacion.setEncargado(this);
	}

	public List<Instalacion> getInstalaciones() {
		return instalaciones;
	}
}
