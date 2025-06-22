package domain;
import java.io.Serializable;
import java.util.ArrayList;
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
@Inheritance(strategy = InheritanceType.JOINED)
public class Socio implements Serializable{
	@Id
	@GeneratedValue
	int numSocio;
	String nombre;
	String dni;
	int maxReservas;
	int reservasSemana;
	String tarjetaCredito;
	String contrasena;
	String rol;
	
	 @XmlID
	 @XmlAttribute(name="idXml")
	    public String getIdXml() {
	        return "soc" + numSocio;
	    }
	
	@OneToMany(mappedBy = "socio", cascade = CascadeType.ALL)
	@XmlTransient
	List<Reserva> reservas = new ArrayList<>();
	
	@OneToMany(mappedBy = "socio", cascade = CascadeType.ALL)
	@XmlTransient
	List<Factura> facturas = new ArrayList<>();
	
	
	public String getTarjetaCredito() {
		return tarjetaCredito;
	}
	public void setTarjetaCredito(String tarjetaCredito) {
		this.tarjetaCredito = tarjetaCredito;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public void setMaxReservas(int maxReservas) {
		this.maxReservas = maxReservas;
	}
	public void setReservasSemana(int reservasSemana) {
		this.reservasSemana = reservasSemana;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	
	public Socio() {}
	public Socio (String n, String d, String p) {
		nombre = n;
		dni = d;
		maxReservas = 5;
		reservasSemana = 0;
		contrasena = p;
		rol = "socio";
		tarjetaCredito = null;
	}
	
	public void agregarReserva(Reserva reserva) {
		reservas.add(reserva);
		reserva.setSocio(this);
	}
	
	public void agregarFactura(Factura factura) {
		facturas.add(factura);
		factura.setSocio(this);
	}

	public List<Reserva> getReservas() {
		return reservas;
	}
	
	public String getTarjeta() {
		return tarjetaCredito;
	}
	
	
	public List<Factura> getFacturas() {
		return facturas;
	}
	
	public int getNumSocio() {
		return numSocio;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDni() {
		return dni;
	}
	
	public int getMaxReservas() {
		return maxReservas;
	}
	
	public int getReservasSemana() {
		return reservasSemana;
	}
	
	public String getContrasena() {
		return contrasena;
	}
	
	public String getRol() {
		return rol;
	}
	
	public void setNumSocio(int s) {
		numSocio = s;
	}
	
	public void setNombre(String s) {
		
	}
	
	
	
	public String toString() {
		return "Nombre: " + nombre + " id: " + numSocio + " pass: " + contrasena ;
	}
}
