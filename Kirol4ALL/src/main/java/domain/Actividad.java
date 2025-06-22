package domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;

@XmlAccessorType(XmlAccessType.FIELD)

@Entity
public class Actividad implements Serializable{
	
	@Id
	@XmlID
	String nombre;
	int exigencia; //1 a 5
	double precioHora;
	
	@OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL)
	@XmlElement(name = "sesion")
    @XmlElementWrapper(name = "sesiones")
	List<Sesion> sesiones = new ArrayList<>();
	
	public Actividad() {}
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
