package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.Eps;
import co.edu.uniquindio.proyecto.modelo.enums.TipoSangre;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClinicaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clinica")
@RequiredArgsConstructor
public class ClinicaController {
    private final ClinicaServicio clinicaServicio;

    @GetMapping("/ciudades")
    public ResponseEntity<MensajeDTO<List<Ciudad>>> listarCiudades(){

        List<Ciudad> ciudades = clinicaServicio.listarCiudades();

        return ResponseEntity.ok().body(new MensajeDTO<>(false, ciudades));
    }

    @GetMapping("/eps")
    public ResponseEntity<MensajeDTO<List<Eps>>> listarEps(){

        List<Eps> eps = clinicaServicio.listarEps();

        return ResponseEntity.ok().body(new MensajeDTO<>(false, eps));
    }

    @GetMapping("/tipo-sangre")
    public ResponseEntity<MensajeDTO<List<TipoSangre>>> listarTipoSangre(){

        List<TipoSangre> tipoSangre = clinicaServicio.listarTipoSangre();

        return ResponseEntity.ok().body(new MensajeDTO<>(false, tipoSangre));
    }

}
