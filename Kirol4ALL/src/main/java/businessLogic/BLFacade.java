package businessLogic;

import java.util.Date;
import java.util.List;

//import domain.Booking;
import domain.Sesion;
import domain.Socio;
import domain.Actividad;
import domain.Encargado;
import domain.Factura;
import domain.Instalacion;
import domain.Reserva;

import javax.jws.WebMethod;
import javax.jws.WebService;
 
/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	  
	/**
	 * Devuelve el numero de tarjeta del usuario-
	 * @param id Identificador del usuario.
	 * @return Numero de tarjeta
	 */
	@WebMethod public String getNumTarjeta(int id);

	/**
	 * Comprueba si el número de tarjeta introducido cumple con el formato establecido.
	 * @param tarjeta Numero de tarjeta introducido
	 * @return True si lo cumple, False si no.
	 */
	@WebMethod public boolean comprobarTarjeta( String tarjeta);
	
	/**
	 * Establece un nuevo número de tarjeta al usuario.
	 * @param id Identificador del usuario
	 * @param tarjeta El nuevo número de tarjeta.
	 */
	@WebMethod public void cambiarTarjeta(int id, String tarjeta);
	
	/**
	 * Devuelve una lista con todas las reservas hechas por un socio
	 * @param id Identificador del socio
	 * @return La lista de reservas
	 */
	@WebMethod public List<Reserva> getReservasSocio(int id);
	
	/**
	 * Devuelve una lista con todas las reservas hechas por un socio que se puedan cancelar.
	 * @param id Identificador del socio
	 * @return La lista de reservas cancelables
	 */
	@WebMethod public List<Reserva> getReservasCancelablesSocio(int id);
	
	/**
	 * Pone una reserva en estado cancelado.
	 * @param r La reserva a cancelar.
	 */
	@WebMethod public void cancelarReserva(Reserva r);
	
	/**
	 * Devuelve una lista con todas las facturas de un usuario.
	 * @param id Identificador del usuario.
	 * @return  La lista de facturas
	 */
	@WebMethod public List<Factura> getFacturas(int id);
	
	/**
	 * Devuelve el número de tarjeta del usuario
	 * @param id Identificador del usuario.
	 * @return EL número de tarjeta del usuario.
	 */
	@WebMethod public String getTarjeta(int id);
	
	/**
	 * Se efectúa el pago de la factura.
	 * @param factura La factura a pagar.
	 * @param id Identificador del usuario
	 */
	@WebMethod public void pagarFactura(int id, Factura factura);
	
	/**
	 * Devuelve la lista de todas las sesiones programadas
	 * @return La lista de sesiones.
	 */
	@WebMethod public List<Sesion> obtenerSesiones();
	
	/**
	 * Devuelva la lista de todas las sesiones de una actividad.
	 * @param act
	 * @return La lista de sesiones de dicha actividad.
	 */
	@WebMethod public List<Sesion> obtenerSesionesActividad(Actividad act);
	
	/**
	 * Devuelva la lista de todas las sesiones de un nivel de exigencia.
	 * @param ex 
	 * @return La lista de sesiones de dicho nivel
	 */
	@WebMethod public List<Sesion> obtenerSesionesExigencia(int ex);
	
	/**
	 * Para saber si un usuario cumple las condiciones para efectuar reservas.
	 * @param id Identificador del usuario.
	 * @return True si las cumple, False en caso contrario.
	 */
	@WebMethod public boolean puedeReservar(int id);
	
	/**
	 * Crea una actividad con los parámetros introducidos,
	 * @param nombre Nombre de la actividad
	 * @param ex Grado de exigencia de la actividad
	 * @param precio Precio por hora de la actividad
	 * @return True si logra crearla, False si no.
	 */
	@WebMethod public boolean crearActividad(String nombre, int ex, double precio);
	
	/**
	 * Devuelva una lista con todas las instalaciones del centro.
	 * @return La lista de instalaciones.
	 */
	@WebMethod public List<Instalacion> getInstalaciones();
	
	/**
	 * Autentica a un usuario en el sistema
	 * @param login Identificador del usuario
	 * @param passwd Contraseña del usuario
	 * @return El rol del usuario autenticado (Socio o Encargado)
	 */
	@WebMethod public String autenticar(int login, String passwd);
	
	/**
	 * Devuelva una lista con todas las actividades en el sistema.
	 * @return La lista de actividades.
	 */
	@WebMethod public List<Actividad> obtenerActividades();
	
	/**
	 * Devuelve una lista con todas las instalaciones a cargo de un encargado
	 * @param id Identificador del encargado.
	 * @return La lista de instalaciones
	 */
	@WebMethod public List<Instalacion> obtenerInstalacionesEncargado(int id);
	
	/**
	 * Devuelva la instalacion con el nombre proporcionado.
	 * @param nomInst
	 * @return La instalacion.
	 */
	@WebMethod public Instalacion getInstalacionNombre(String nomInst);
	
	/**
	 * Devuelve la actividad con el nombre proporcionado.
	 * @param nomAct
	 * @return La actividad.
	 */
	@WebMethod public Actividad getActividadNombre(String nomAct);
	
	/**
	 * Crea una nueva sesión con los parámetros proporcionados.
	 * @param act La actividad de la sesión.
	 * @param inst En que instalación se llevará a cabo.
	 * @param fechaIni Fecha de inicio.
	 * @param fechaFin Fecha de finalización.
	 */
	@WebMethod public void crearSesion(Actividad act, Instalacion inst, Date fechaIni, Date fechaFin);
	
	/**
	 * Devuelve el número de reservas restantes de un usuario.
	 * @param id Identificador del usuario.
	 * @return Número de reservas restantes.
	 */
	@WebMethod public int getNumeroReservasRestantes(int id);
	
	/**
	 * Realiza una reserva para un usuario
	 * @param id Identificador del usuario
	 * @param sesion Sesion que quiere reservar el usuario.
	 */
	@WebMethod public void hacerReserva(int id, Sesion sesion);	
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();

	@WebMethod public Socio getSocio();
	@WebMethod public Encargado getEncargado();
}
