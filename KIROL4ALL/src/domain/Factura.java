package domain;
import javax.persistence.*;

import otros.EstadoFactura;

import java.util.Date;

@Entity
public class Factura {
	@Id
	@GeneratedValue
	int codigo;
	Date fecha;
	double precioTotal;
	EstadoFactura estado;
	
	@ManyToOne
	@JoinColumn(name = "socio_id")
	Socio socio;
	
	public Factura(double d) {
		fecha = new Date();
		precioTotal = d;
		estado = EstadoFactura.porAbonar;
	}
	
	public void setEstado(EstadoFactura est) {
		estado = est;
	}
	
	public void setSocio(Socio s) {
		socio = s;
	}
	
	public EstadoFactura getEstadoFactura() {
		return estado;
	}
	
	public String toString() {
		return "ID: " + codigo + " IDSocio: " + socio.getNumSocio() + " Precio: "  + precioTotal + "€";
	}
	
}
