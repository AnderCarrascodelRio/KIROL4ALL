package domain;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;

import configuration.EstadoFactura;

import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Factura implements Serializable{
	@Id
	@GeneratedValue
	int codigo;
	@Temporal(TemporalType.DATE)
	Date fecha;
	double precioTotal;
	@Enumerated(EnumType.STRING)
	EstadoFactura estado;

	@XmlID
	@XmlAttribute(name = "idXml")
	public String getCodigoXml() {
	    return "fac" + codigo;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "socio_id")
	@XmlIDREF 
	Socio socio;
	
	public Factura() {}
	public Factura(double d) {
		fecha = new Date();
		precioTotal = d;
		estado = EstadoFactura.porAbonar;
	}
	
	public void setEstado(EstadoFactura est) {
		estado = est;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public double getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}
	public EstadoFactura getEstado() {
		return estado;
	}
	public Socio getSocio() {
		return socio;
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
