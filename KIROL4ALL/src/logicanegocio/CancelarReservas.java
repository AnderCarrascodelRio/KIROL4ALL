package logicanegocio;

import java.util.List;

import javax.persistence.EntityManager;

import dataAccess.CancelarReservasDAO;
import domain.Reserva;

public class CancelarReservas {
	CancelarReservasDAO dataAccess;
	int id;
	String rol;
	
	public CancelarReservas(EntityManager db, int i, String r) {
		dataAccess = new CancelarReservasDAO(db);
		id = i;
		rol = r;
	}
	
	public List<Reserva> getReservasSocio() {
		return dataAccess.getReservasSocio(id);
	}
	
	public List<Reserva> getReservasActivasSocio() {
		return dataAccess.reservasActivasSocio(id);
	}
	
	public void cancelarReserva(Reserva r) {
		dataAccess.cancelarReserva(r);
	}
}
