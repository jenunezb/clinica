package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.ItemCitaDTO;
import co.edu.uniquindio.proyecto.dto.medico.*;
import co.edu.uniquindio.proyecto.dto.paciente.DetalleCita;
import co.edu.uniquindio.proyecto.servicios.interfaces.MedicoServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
@SpringBootTest
public class MedicoServicioTest {

    @Autowired
    private MedicoServicio medicoServicio;

    @Test
    @Sql("classpath:dataset.sql" )
    public void verCitasPendientes(){
        CitasFechaDTO listaCitasFechaDTO = new CitasFechaDTO(2, LocalDate.of(2023,12,11));
        List<ItemCitaDTO> citas = medicoServicio.listarCitasPendientesDia(listaCitasFechaDTO);
        citas.forEach(System.out::println);
        Assertions.assertEquals(1, citas.size());
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void atenderCita() throws Exception{
        DetalleAtencionMedicaDTO detalleAtencionMedicaDTO = medicoServicio.atenderCita(5);
        System.out.println(detalleAtencionMedicaDTO);
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void finalizarCita() throws Exception{

        FinalizarCitaDTO finalizarCitaDTO = new FinalizarCitaDTO(
                5,
                "El paciente se sometió a un electrocardiograma (ECG) y una prueba de esfuerzo. Los resultados del ECG mostraron una actividad cardíaca normal sin signos de arritmias o bloqueos. La prueba de esfuerzo indicó una capacidad cardiovascular adecuada y la presión arterial se mantuvo dentro de los rangos normales durante el ejercicio. Basándonos en estos resultados, no se detectaron problemas cardíacos evidentes en este momento. Se recomienda al paciente mantener un estilo de vida saludable, hacer ejercicio regularmente y realizar un seguimiento médico anual para evaluar la salud cardíaca debido a los antecedentes familiares",
                "El paciente presenta dolor torácico y dificultad para respirar, lo que es altamente sugestivo de un episodio de angina de pecho. Se recomienda una evaluación cardiológica más detallada, que podría incluir un electrocardiograma (ECG) y análisis de sangre para marcadores cardíacos.",
                "Administrar aspirina 325 mg para masticar para prevenir la agregación plaquetaria.\n" +
                        "Proporcionar oxígeno suplementario a 2 litros por minuto por cánula nasal para mejorar la saturación de oxígeno.\n" +
                        "Remitir al paciente a una consulta con un cardiólogo para un ECG y pruebas adicionales.\n" +
                        "Recomendar una dieta baja en grasas y la adopción de un estilo de vida más saludable, que incluya ejercicio regular y control del estrés."
        );
        medicoServicio.finalizarCita(finalizarCitaDTO);

    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void registrarDiaLibre() throws Exception{
        //Si al médico del código 1 le asigno un dia libre el 20 no me va a dejar porque ya tiene citas agendadas
        AgendarDiaLibre agendarDiaLibre= new AgendarDiaLibre(
                1,
                LocalDate.of(2023,10,21)
        );
        medicoServicio.agendarDiaLibre(agendarDiaLibre);
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void listarHistorialDeAtenciones(){
        List<AtencionMedica> hitorialDeAtenciones = medicoServicio.listarCitasRealizadasMedico(4);
        for (AtencionMedica atencionMedica:
             hitorialDeAtenciones) {
            System.out.println(atencionMedica);
        }
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void verHistorialMedicoTest(){
        List<DetalleCita> detalleCitas = medicoServicio.listarHistorialMedico(8);
        detalleCitas.forEach(System.out::println);
    }
}