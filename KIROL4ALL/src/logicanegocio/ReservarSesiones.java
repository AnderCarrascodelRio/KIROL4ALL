package logicanegocio;

import javax.persistence.EntityManager;

import dataAccess.ReservarSesionesDAO;
import domain.Reserva;
import domain.Sesion;
import domain.Socio;
import otros.Estado;

public class ReservarSesiones {
	ReservarSesionesDAO dataAccess;
	int id;
	String rol;
	
	public ReservarSesiones(EntityManager db, int i, String r) {
		dataAccess = new ReservarSesionesDAO(db);
		id = i;
		rol = r;
	}
	
	public int getNumeroReservasRestantes() {
		Socio socio = dataAccess.buscarPorId(id);
		int num = dataAccess.reservasActivasSocio(socio).size();
		return 5-num;
	}
	
	public void hacerReserva(Sesion sesion) {
		Estado estado;
		if (sesion.getNumUsuarios() >= sesion.getInstalacion().getAforo()) {
			estado = Estado.enEspera;
		}
		else {
			estado = Estado.confirmada;
		}
		dataAccess.storeReserva(id, sesion, estado);
	}
}
