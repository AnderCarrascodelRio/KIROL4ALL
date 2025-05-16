package logicanegocio;

import java.util.List;

import javax.persistence.EntityManager;

import dataAccess.ConsultarFacturasDAO;
import domain.Factura;

public class ConsultarFacturas {
	ConsultarFacturasDAO dataAccess;
	int id;
	String rol;
	
	public ConsultarFacturas(EntityManager db, int i, String r) {
		dataAccess = new ConsultarFacturasDAO(db);
		id = i;
		rol = r;
	}

	public List<Factura> getFacturas() {
		return dataAccess.getFacturasSocio(id);
	}
	
	public String getTarjeta() {
		return dataAccess.getTarjetaSocio(id);
				
	}
	
	public void pagarFactura(Factura factura) {
		dataAccess.pagarFactura(id, factura);
	}
}
