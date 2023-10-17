package co.edu.uniquindio.proyecto.dto.medico;

import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.Eps;
import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;
import co.edu.uniquindio.proyecto.modelo.enums.TipoSangre;

public record DetalleAtencionMedicaDTO(

        int cedulaPaciente,
        String nombrePaciente,
        String telefono,
        Eps eps,
        TipoSangre tipoSangre,
        Ciudad ciudad,

        Especialidad especialidad,
        String nombreMedico,
        String Motivo

) {
}