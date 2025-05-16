package dataAccess;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import domain.Reserva;
import domain.Sesion;
import domain.Socio;
import otros.Estado;

public class ReservarSesionesDAO {
	EntityManager db;
	
	public ReservarSesionesDAO(EntityManager db) {
		this.db = db;
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
	
	public void storeReserva(int id, Sesion sesion, Estado estado) {
		db.getTransaction().begin();
		Reserva res = new Reserva(sesion, estado);
		Socio s = buscarPorId(id);
		if (estado == Estado.confirmada) {
			sesion.setUsuarios(sesion.getNumUsuarios()+1);
		}
		s.agregarReserva(res);
		sesion.agregarReserva(res);
		db.persist(res);	
		db.getTransaction().commit();
	}
}
