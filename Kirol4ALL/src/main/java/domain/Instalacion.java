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
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Instalacion implements Serializable{
	@Id
	@XmlID
	String nombre;
	int aforoMax;
	
	@OneToMany(mappedBy = "instalacion", cascade = CascadeType.ALL)
	@XmlElement(name = "sesion")
    @XmlElementWrapper(name = "sesiones")
	List<Sesion> sesiones = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "encargado_id")
	@XmlIDREF
	Encargado encargado;
	
	public Instalacion() {}
	public Instalacion(String n, int a) {
		nombre = n;
		aforoMax = a;
		sesiones = new ArrayList<>();
	}
	
	public void agregarSesion(Sesion sesion) {
		sesiones.add(sesion);
		sesion.setInstalacion(this);
		
	}

	
	public int getAforoMax() {
		return aforoMax;
	}
	public void setAforoMax(int aforoMax) {
		this.aforoMax = aforoMax;
	}
	public List<Sesion> getSesiones() {
		return sesiones;
	}
	public void setSesiones(List<Sesion> sesiones) {
		this.sesiones = sesiones;
	}
	public Encargado getEncargado() {
		return encargado;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
