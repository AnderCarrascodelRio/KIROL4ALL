package dataAccess;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import domain.Reserva;
import domain.Sesion;
import domain.Socio;
import otros.Estado;

public class CancelarReservasDAO {
	EntityManager db;
	
	public CancelarReservasDAO(EntityManager db) {
		this.db = db;
	}
	
	public List<Reserva> getReservasSocio(int id) {
		Socio socio = db.find(Socio.class, id);
		List<Reserva> resultado = new ArrayList<Reserva>();
		for (Reserva r: socio.getReservas()) {
			if (r.getCancelable())
				resultado.add(r);
		}
		return resultado;
	}
	public List<Reserva> reservasActivasSocio(int id) {
		Socio socio = db.find(Socio.class, id);
		List<Reserva> resultado = new ArrayList<>();
		for (Reserva r: socio.getReservas()) {
			if (r.getEstado() == Estado.confirmada || r.getEstado() == Estado.enEspera)
				resultado.add(r);
		}
		return resultado;
	}
	
	public void cancelarReserva(Reserva r) {
		db.getTransaction().begin();
		r.setEstado(Estado.cancelada);
		Sesion ses = r.getSesion();	
		ses.setUsuarios(ses.getNumUsuarios()-1);
		List<Reserva> reservas = ses.getReservas();
		for (Reserva res: reservas) {
			if (res.getEstado() == Estado.enEspera) {
				res.setEstado(Estado.confirmada);
				ses.setUsuarios(ses.getNumUsuarios()+1);
				break;
			}
		}
		db.getTransaction().commit();
	}
}
