package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.Eps;
import co.edu.uniquindio.proyecto.modelo.enums.TipoSangre;

import java.util.List;

public interface ClinicaServicio {

    List<Ciudad> listarCiudades();

    List<Eps> listarEps();

    List<TipoSangre> listarTipoSangre();
}
