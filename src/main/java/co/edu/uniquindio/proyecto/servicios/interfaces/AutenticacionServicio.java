package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.LoginDTO;


public interface AutenticacionServicio {
    void login(LoginDTO loginDTO) throws Exception;
}
