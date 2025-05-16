package dataAccess;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import domain.Factura;
import domain.Socio;
import otros.EstadoFactura;

public class ConsultarFacturasDAO {
	EntityManager db;
	
	public ConsultarFacturasDAO (EntityManager db) {
		this.db = db;
	}

	
	public List<Factura> getFacturasSocio(int id) {
		Socio socio = db.find(Socio.class, id);
		List<Factura> resultado = new ArrayList<>();
		for (Factura f: socio.getFacturas()) {
			if (f.getEstadoFactura() == EstadoFactura.porAbonar) {
				resultado.add(f);
			}
		}
		return resultado;
	}
	
	public String getTarjetaSocio(int id) {
		Socio socio = db.find(Socio.class, id);
		return socio.getTarjeta();
	}


	public void pagarFactura(int id, Factura f) {
		db.getTransaction().begin();
		f.setEstado(EstadoFactura.abonada);
		db.getTransaction().commit();
	}
}
