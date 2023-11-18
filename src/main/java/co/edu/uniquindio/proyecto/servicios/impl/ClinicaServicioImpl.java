package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.Eps;
import co.edu.uniquindio.proyecto.modelo.enums.TipoSangre;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClinicaServicio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClinicaServicioImpl implements ClinicaServicio {

    @Override
    public List<Ciudad> listarCiudades() {
        return Arrays.asList(Ciudad.values());
    }

    @Override
    public List<Eps> listarEps() {
        return Arrays.asList(Eps.values());
    }

    @Override
    public List<TipoSangre> listarTipoSangre() {
        return Arrays.asList(TipoSangre.values());
    }
}
