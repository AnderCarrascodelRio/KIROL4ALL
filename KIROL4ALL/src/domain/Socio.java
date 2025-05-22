package domain;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Socio {
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
	
	@OneToMany(mappedBy = "socio", cascade = CascadeType.ALL)
	List<Reserva> reservas = new ArrayList<>();
	
	@OneToMany(mappedBy = "socio", cascade = CascadeType.ALL)
	List<Factura> facturas = new ArrayList<>();
	
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
	
	public void setTarjeta(String t) {
		tarjetaCredito = t;
	}
	
	public List<Factura> getFacturas() {
		return facturas;
	}
	
	public int getNumSocio() {
		return numSocio;
	}
}
