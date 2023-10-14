package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.ItemCitaDTO;
import co.edu.uniquindio.proyecto.dto.medico.CitasFechaDTO;
import co.edu.uniquindio.proyecto.dto.medico.DetalleAtencionMedicaDTO;
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
        CitasFechaDTO listaCitasFechaDTO = new CitasFechaDTO(2, LocalDate.of(2023,10,11));
        List<ItemCitaDTO> citas = medicoServicio.listarCitasPendientes(listaCitasFechaDTO);
        citas.forEach(System.out::println);
        Assertions.assertEquals(1, citas.size());
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void atenderCita() throws Exception{
        DetalleAtencionMedicaDTO detalleAtencionMedicaDTO = medicoServicio.atenderCita(5);
        System.out.println(detalleAtencionMedicaDTO.toString());
    }
}