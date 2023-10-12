package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.MedicoDTO;
import co.edu.uniquindio.proyecto.dto.admin.DetalleMedicoDTO;
import co.edu.uniquindio.proyecto.dto.admin.ItemMedicoDTO;
import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;
import co.edu.uniquindio.proyecto.servicios.interfaces.AdministradorServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

@Transactional
@SpringBootTest
public class AdministradorServicioTest {

    @Autowired
    private AdministradorServicio administradorServicio;

    @Test
    @Sql("classpath:dataset.sql" )
    public void registrarMedicoTest() throws Exception{

        MedicoDTO medicoDTO = new MedicoDTO(
                "Gabriela Nuñez Díaz",
                1094966343,
                Ciudad.BOGOTÁ,
                Especialidad.CARDIOLOGIA,
                "3117567564",
                "ganudi@gmail.com",
                "123456",
                LocalTime.of(8,00),
                LocalTime.of(17,00),
                "Url_Foto"
        );

        int nuevo = administradorServicio.crearMedico(medicoDTO);

        Assertions.assertNotEquals(0,nuevo);
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void modificarMedicoTest() throws Exception{
        DetalleMedicoDTO detalleMedicoDTO = administradorServicio.obtenerMedico(1);

        DetalleMedicoDTO modificado = new DetalleMedicoDTO(
                detalleMedicoDTO.nombre(),
                detalleMedicoDTO.cedula(),
                detalleMedicoDTO.ciudad(),
                detalleMedicoDTO.especialidad(),
                detalleMedicoDTO.telefono(),
                "julianstebann8@gmail.com",
                detalleMedicoDTO.urlFoto()
        );

        administradorServicio.actualizarMedico(modificado);

        DetalleMedicoDTO objetoModificado = administradorServicio.obtenerMedico(1);

        Assertions.assertEquals("julianstebann8@gmail.com", objetoModificado.correo());
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void buscarMedicoTest() throws Exception{
        DetalleMedicoDTO detalleMedicoDTO = administradorServicio.obtenerMedico(1);

        Assertions.assertEquals(detalleMedicoDTO.cedula(), 1);
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void listarMedicosTest() throws Exception{
        List<ItemMedicoDTO> medicos = administradorServicio.listarMedicos();
        medicos.forEach(System.out::println);

        Assertions.assertEquals(5, medicos.size());
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void eliminarMedicoTest() throws Exception{
        administradorServicio.eliminarMedico(1);
        Assertions.assertThrows(Exception.class, () -> administradorServicio.obtenerMedico(1));
    }
}
