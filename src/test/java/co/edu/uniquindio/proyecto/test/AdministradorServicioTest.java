package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.DetallePQRSDTO;
import co.edu.uniquindio.proyecto.dto.ItemPQRSDTO;
import co.edu.uniquindio.proyecto.dto.MedicoDTO;
import co.edu.uniquindio.proyecto.dto.admin.DetalleMedicoDTO;
import co.edu.uniquindio.proyecto.dto.admin.HistorialConsultas;
import co.edu.uniquindio.proyecto.dto.admin.ItemMedicoDTO;
import co.edu.uniquindio.proyecto.dto.admin.RespuestaDTO;
import co.edu.uniquindio.proyecto.dto.paciente.ItemPacienteDTO;
import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoPQRS;
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
    @Sql("classpath:dataset.sql")
    public void registrarMedicoTest() throws Exception {

        MedicoDTO medicoDTO = new MedicoDTO(
                "Gabriela Nuñez Díaz",
                "1563",
                Ciudad.BOGOTÁ,
                Especialidad.CARDIOLOGIA,
                "3117567564",
                "ganudi@gmail.com",
                "123456",
                LocalTime.of(8, 00),
                LocalTime.of(17, 00),
                "Url_Foto"
        );

        int nuevo = administradorServicio.crearMedico(medicoDTO);

        Assertions.assertNotEquals(1, nuevo);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void modificarMedicoTest() throws Exception {
        DetalleMedicoDTO detalleMedicoDTO = administradorServicio.obtenerMedico(14);

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

        DetalleMedicoDTO objetoModificado = administradorServicio.obtenerMedico(14);

        Assertions.assertEquals("julianstebann8@gmail.com", objetoModificado.correo());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarMedicoTest() throws Exception {
        DetalleMedicoDTO detalleMedicoDTO = administradorServicio.obtenerMedico(14);

        Assertions.assertNotNull(detalleMedicoDTO);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarMedicosTest() throws Exception {
        List<ItemMedicoDTO> medicos = administradorServicio.listarMedicos();
        medicos.forEach(System.out::println);

        Assertions.assertEquals(5, medicos.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarMedicoTest() throws Exception {
        administradorServicio.eliminarMedico(13);
        Assertions.assertThrows(Exception.class, () -> administradorServicio.obtenerMedico(13));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void ListarPqrsTest() throws Exception {
        List<ItemPQRSDTO> listaPqrs = administradorServicio.listarPQRS();
        listaPqrs.forEach(System.out::println);
        Assertions.assertEquals(3, listaPqrs.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void verDetallePqrs() throws Exception {
        DetallePQRSDTO detallePQRSDTO = administradorServicio.verDetallePQRS(2);
        System.out.println(detallePQRSDTO.toString());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void responderPqrs() throws Exception {
        RespuestaDTO respuestaDTO = new RespuestaDTO(
                5,
                "Lamentamos lo sucedido, hablaremos con el doctor y lo mantendremos al tanto"
        );
        administradorServicio.responderPQRS(respuestaDTO);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void finalizarPqrs() throws Exception {
        administradorServicio.cambiarEstadoPQRS(2, EstadoPQRS.CERRADA);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void verHistorialConsultas() {
        List<HistorialConsultas> historialConsultas = administradorServicio.verHistorialDeConsultas();

        historialConsultas.forEach(System.out::println);

        Assertions.assertEquals(1, historialConsultas.size());

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void verHistorialConsultasMedico() {
        List<HistorialConsultas> historialConsultas = administradorServicio.verHistorialDeConsultasMedico(14);
        historialConsultas.forEach(System.out::println);
        Assertions.assertEquals(0, historialConsultas.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPacientesTest() {
//Obtenemos la lista de todos los pacientes
        List<ItemPacienteDTO> lista = administradorServicio.listarTodos();
        lista.forEach(System.out::println);
//Si en el dataset creamos 2 pacientes, entonces el tamaño de la lista debe ser 2
        Assertions.assertEquals(9, lista.size());
    }
}