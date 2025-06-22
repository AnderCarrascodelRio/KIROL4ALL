package configuration;

import javax.swing.table.AbstractTableModel;

import domain.Sesion;

import java.util.Calendar;
import java.util.List;

public class SesionTableModel extends AbstractTableModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final List<Sesion> sesiones;
    private final String[] columnas = {"Actividad", "Fecha", "Disponible", "N.Usuarios", "Instalación"};

    public SesionTableModel(List<Sesion> sesiones) {
        this.sesiones = sesiones;
    }

    @Override
    public int getRowCount() {
        return sesiones.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
   
    public Object getValueAt(int rowIndex, int columnIndex) {
        Sesion s = sesiones.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return s.getActividad().getNombre();
            case 1: {
                Calendar calInicio = Calendar.getInstance();
                calInicio.setTime(s.getFechaInicio());
                int dia = calInicio.get(Calendar.DAY_OF_MONTH);
                int mes = calInicio.get(Calendar.MONTH) + 1; // Enero = 0
                int horaInicio = calInicio.get(Calendar.HOUR_OF_DAY);

                Calendar calFin = Calendar.getInstance();
                calFin.setTime(s.getFechaFin());
                int horaFin = calFin.get(Calendar.HOUR_OF_DAY);

                return dia + "/" + mes + " " + horaInicio + ":00-" + horaFin + ":00";
            }
            case 2:
                return s.estaDisponible();
            case 3:
                return s.getNumUsuarios();
            case 4:
                return s.getInstalacion().getNombre();
            default:
                return null;
        }
    }


    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    public Sesion getSesionAt(int rowIndex) {
        return sesiones.get(rowIndex);
    }
}

