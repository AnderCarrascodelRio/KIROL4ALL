package businessLogic;
import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import configuration.Estado;
import dataAccess.DataAccess;
import domain.Sesion;
import domain.Socio;
import domain.Actividad;
import domain.Encargado;
import domain.Factura;
import domain.Instalacion;
import domain.Reserva;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		
		
		    dbManager=new DataAccess();
		    
		//

		
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		
		dbManager=da;		
	}
    
    public Socio getSocio() {
    	return dbManager.getSocio();
    }
    public Encargado getEncargado() {
    	return dbManager.getEncargado();
    }
	
	public void close() {
		DataAccess dB4oManager=new DataAccess();

		dB4oManager.close();

	}

	/**
	 * {@inheritDoc}
	 */
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open();
		dbManager.initializeDB();
		
	}

	@Override
	public String getNumTarjeta(int id) {
		dbManager.open();
		String res = dbManager.getTarjetaSocio(id);
		
		if (res == null) {
			return "No tiene.";
		}
		else {
			return res;
		}
			
	}

	@Override
	public boolean comprobarTarjeta(String tarjeta) {
		if (tarjeta != null && tarjeta.matches("\\d{12}")) {
	        return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void cambiarTarjeta(int id, String tarjeta) {
		dbManager.open();
		dbManager.cambiarTarjeta(id, tarjeta);	
		
	}

	@Override
	public List<Reserva> getReservasSocio(int id) {
		dbManager.open();
		List<Reserva> res = dbManager.getReservasSocio(id);
		
		return res;
	}

	@Override
	public List<Reserva> getReservasCancelablesSocio(int id) {
		dbManager.open();
		List<Reserva> res = dbManager.reservasActivasSocio(id);
		
		return res;
	}

	@Override
	public void cancelarReserva(Reserva r) {
		dbManager.open();
		dbManager.cancelarReserva(r);
		
	}

	@Override
	public List<Factura> getFacturas(int id) {
		dbManager.open();
		List<Factura> res = dbManager.getFacturasSocio(id);
		
		return res;
	}

	@Override
	public String getTarjeta(int id) {
		dbManager.open();
		String res = dbManager.getTarjetaSocio(id);
		
		return res;
	}

	@Override
	public void pagarFactura(int id, Factura factura) {
		dbManager.open();
		dbManager.pagarFactura(id, factura);
				
	}

	@Override
	public List<Sesion> obtenerSesiones() {
		dbManager.open();
		List<Sesion> res = dbManager.obtenerSesiones();
		
		return res;
	}

	@Override
	public List<Sesion> obtenerSesionesActividad(Actividad act) {
		dbManager.open();
		List<Sesion> res = dbManager.obtenerSesionesActividad(act);
		
		return res;
	}

	@Override
	public List<Sesion> obtenerSesionesExigencia(int ex) {
		dbManager.open();
		List<Sesion> res = dbManager.obtenerSesionesExigencia(ex);
		
		return res;
	}

	@Override
	public boolean puedeReservar(int id) {
		dbManager.open();
		Socio socio = dbManager.buscarPorId(id);
	    int numReservas = dbManager.reservasActivasSocio(socio).size();
		
		 if (numReservas >=5) { return false;}
		    else {return true;}
	}

	@Override
	public boolean crearActividad(String nombre, int ex, double precio) {
		dbManager.open();
		boolean existe = dbManager.buscarActividad(nombre);
		if (!existe) {
			dbManager.crearActividad(nombre, ex, precio);
		}
		
		return !existe;
	}

	@Override
	public List<Instalacion> getInstalaciones() {
		dbManager.open();
		List<Instalacion> res = dbManager.getInstalaciones();
		
		return res;
	}

	@Override
	public String autenticar(int login, String passwd) {
		dbManager.open();
		 String res = dbManager.rolCredenciales(login, passwd);
		
		return res;
	}

	@Override
	public List<Actividad> obtenerActividades() {
		dbManager.open();
		List<Actividad> res = dbManager.obtenerActividades();
		
		return res;
	}

	@Override
	public List<Instalacion> obtenerInstalacionesEncargado(int id) {
		dbManager.open();
		List<Instalacion> res = dbManager.obtenerInstalacionesEncargado(id);
		
		return res;
	}

	@Override
	public Instalacion getInstalacionNombre(String nomInst) {
		dbManager.open();
		Instalacion res = dbManager.getInstalacionNombre(nomInst);
		
		return res;
	}

	@Override
	public Actividad getActividadNombre(String nomAct) {
		dbManager.open();
		Actividad res = dbManager.getActividadNombre(nomAct);
		
		return res;
	}

	@Override
	public void crearSesion(Actividad act, Instalacion inst, Date fechaIni, Date fechaFin) {
		dbManager.open();
		Sesion sesion = new Sesion(fechaIni, fechaFin);
		dbManager.crearSesion(act, inst, sesion);
				
	}

	@Override
	public int getNumeroReservasRestantes(int id) {
		dbManager.open();
		Socio socio = dbManager.buscarPorId(id);
		int num = dbManager.reservasActivasSocio(socio).size();
		
		return 5-num;
	}

	@Override
	public void hacerReserva(int id, Sesion sesion) {
		dbManager.open();
		Estado estado;
		if (sesion.getNumUsuarios() >= sesion.getInstalacion().getAforo()) {
			estado = Estado.enEspera;
		}
		else {
			estado = Estado.confirmada;
		}
		dbManager.storeReserva(id, sesion, estado);
				
	}

}

