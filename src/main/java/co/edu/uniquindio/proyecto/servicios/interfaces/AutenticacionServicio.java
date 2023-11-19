package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.LoginDTO;
import co.edu.uniquindio.proyecto.dto.TokenDTO;


public interface AutenticacionServicio {
    TokenDTO login(LoginDTO loginDTO) throws Exception;
}
