package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClinicaServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@SpringBootTest
public class ClinicaServicioTest {
    @Autowired
    ClinicaServicio clinicaServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCiudadesTest(){
        List<Ciudad> ciudades = clinicaServicio.listarCiudades();

        ciudades.forEach(System.out::println);
    }
}
