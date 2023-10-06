package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.EmailServicio;
import org.springframework.stereotype.Service;

@Service
public class EmailServicioImpl implements EmailServicio {
    @Override
    public String enviarCorreo(EmailDTO emailDTO) throws Exception {
        return null;
    }
}
