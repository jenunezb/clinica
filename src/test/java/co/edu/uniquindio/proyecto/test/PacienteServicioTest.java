package co.edu.uniquindio.proyecto.test;
import co.edu.uniquindio.proyecto.dto.DetallePQRSDTO;
import co.edu.uniquindio.proyecto.dto.ItemPQRSDTO;
import co.edu.uniquindio.proyecto.dto.paciente.*;
import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.Eps;
import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;
import co.edu.uniquindio.proyecto.modelo.enums.TipoSangre;
import co.edu.uniquindio.proyecto.servicios.interfaces.PacienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
@SpringBootTest
public class PacienteServicioTest {
    @Autowired
    private PacienteServicio pacienteServicio;

    @Test
    @Sql("classpath:dataset.sql" )
    public void registrarPacienteTest() throws Exception{
//Creamos un objeto con los datos del paciente
        RegistroPacienteDTO pacienteDTO = new RegistroPacienteDTO(
                "juesnub@gmail.com",
                "1254",
                "1094927538",
                "pepito perez","7485440", "urlFoto",
                Ciudad.BARRANQUILLA,LocalDate.of(1992,05,19), "Alergias", Eps.NUEVA_EPS, TipoSangre.A_NEGATIVO);
//Guardamos el registro usando el método del servicio
        int nuevo = pacienteServicio.registrarse(pacienteDTO);

//Comprobamos que sí haya quedado guardado. Si se guardó debe retornar el código (no 0).
        Assertions.assertEquals(0, nuevo);
    }
    @Test
    @Sql("classpath:dataset.sql" )
    public void actualizarPacienteTest() throws Exception{
//Para actualizar el paciente primero lo obtenemos
        DetallePacienteDTO guardado = pacienteServicio.verDetallePaciente(4);
//Le modificamos el número de teléfono, lo demás lo dejamos igual
        DetallePacienteDTO modificado = new DetallePacienteDTO(
                guardado.cedula(),
                guardado.nombre(),
                "111111",
                guardado.urlFoto(),
                guardado.ciudad(),
                guardado.fechaNacimiento(),
                guardado.alergias(),
                guardado.eps(),
                guardado.tipoSangre(),
                guardado.correo());
//Se invoca el servicio de actualizar los datos
        pacienteServicio.editarPerfil(modificado);
//Se obtiene nuevamente el paciente para comprobar que sí se haya actualizado
        DetallePacienteDTO objetoModificado = pacienteServicio.verDetallePaciente(4);
//Se comprueba que el teléfono del paciente sea el que se le asignó en la actualización
        Assertions.assertEquals("111111", objetoModificado.telefono());
    }
    @Test
    @Sql("classpath:dataset.sql" )
    public void eliminarPacienteTest() throws Exception{
//Se borra por ejemplo el paciente con el código 1
        pacienteServicio.eliminarCuenta(4);
    }
    @Test
    @Sql("classpath:dataset.sql" )
    public void agendarCitaTest() throws Exception{
        RegistroCitaDTO registroCitaDTO = new RegistroCitaDTO(
                LocalDateTime.of(2023,10,11,7,00),
                "Odontologia",
                "7",
                "4"
        );
        int codigo=pacienteServicio.agendarCita(registroCitaDTO);

    }
    @Test
    @Sql("classpath:dataset.sql" )
    public void crearPQRSTest() throws Exception{
        RegistroPQRSDTO registroPQRSDTO = new RegistroPQRSDTO(
                8,
                "El doctor fue muy grosero",
                "En la atención el doctor fue muy grosero y no me dio la formula médica"
        );
        pacienteServicio.crearPQRS(registroPQRSDTO);
    }
    
    @Test
    @Sql("classpath:dataset.sql" )
    public void ListarMedicosTest() throws Exception{
MedicosDisponiblesDTO medicosDisponiblesDTO = new MedicosDisponiblesDTO(
        LocalDate.of(2023,10,24),
        Especialidad.DERMATOLOGIA);

List<MedicosDisponiblesGetDTO> medicosDisponibles = pacienteServicio.mostrarMedicosDisponibles(medicosDisponiblesDTO);
medicosDisponibles.forEach(System.out::println);
        Assertions.assertEquals(5, medicosDisponibles.size());
    }
    @Test
    @Sql("classpath:dataset.sql" )
    public void verHistorialMedicoTest(){
        List<DetalleCita> detalleCitas = pacienteServicio.verHistorialMedico("8");
        detalleCitas.forEach(System.out::println);
    }
    @Test
    @Sql("classpath:dataset.sql" )
    public void filtrarCitasPorFechaTest(){
        List<DetalleCita> detalleCitas = pacienteServicio.filtrarCitasPorFecha("8", LocalDate.of(2023,10,11));
        detalleCitas.forEach(System.out::println);
    }
    @Test
    @Sql("classpath:dataset.sql" )
    public void filtrarCitasPorMedicoTest(){
        List<DetalleCita> detalleCitas = pacienteServicio.filtrarCitasPorMedico(4,14);
        detalleCitas.forEach(System.out::println);
    }
    @Test
    @Sql("classpath:dataset.sql" )
    public void enviarLinkRecuperacion() throws Exception{
        pacienteServicio.enviarLinkRecuperacion("juesnube@gmail.com");
        Assertions.assertThrows(Exception.class, () -> pacienteServicio.enviarLinkRecuperacion("josehernandez@gmail.com"));
    }
    @Test
    @Sql("classpath:dataset.sql" )
    public void listarPQRSPaciente(){
        List<ItemPQRSDTO> itemPQRSDTOList = pacienteServicio.listarPQRSPaciente(5);
        itemPQRSDTOList.forEach(System.out::println);
        Assertions.assertEquals(1, itemPQRSDTOList.size());
    }
    @Test
    @Sql("classpath:dataset.sql" )
    public void responderPQRSTest()throws Exception{
        ResponderPqrsPaciente responderPqrsPaciente = new ResponderPqrsPaciente(4,"Gracias por atenderme");
        DetallePQRSDTO detallePQRSDTO=pacienteServicio.responderPQRS(responderPqrsPaciente);

        System.out.println(detallePQRSDTO);

        Assertions.assertNotNull(detallePQRSDTO);
    }
    @Test
    @Sql("classpath:dataset.sql" )
    public void verHistorialMedico(){
        List<DetalleCita> detalleCitas = pacienteServicio.verHistorialMedico("8");
        detalleCitas.forEach(System.out::println);
        Assertions.assertEquals(2, detalleCitas.size());
    }

}