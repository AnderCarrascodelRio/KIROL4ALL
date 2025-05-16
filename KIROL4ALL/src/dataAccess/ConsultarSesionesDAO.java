package dataAccess;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import domain.Reserva;
import domain.Sesion;
import domain.Socio;
import otros.Estado;

public class ConsultarSesionesDAO {
	EntityManager db;
	
	public ConsultarSesionesDAO(EntityManager db) {
		this.db = db;
	}
	
	public List<Sesion> obtenerSesiones() {
		return db.createQuery("SELECT s FROM Sesion s", Sesion.class).getResultList();
	}

	public Socio buscarPorId(int id) {
		return db.find(Socio.class, id);
	}
	
	public List<Reserva> reservasSocio(Socio socio) {
		return socio.getReservas();
	}
	public List<Reserva> reservasActivasSocio(Socio socio) {
		List<Reserva> resultado = new ArrayList<>();
		for (Reserva r: socio.getReservas()) {
			if (r.getEstado() == Estado.confirmada || r.getEstado() == Estado.enEspera)
				resultado.add(r);
		}
		
		return resultado;
	}

}
