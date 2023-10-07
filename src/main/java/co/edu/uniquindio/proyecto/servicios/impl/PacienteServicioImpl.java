package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.DetalleAtencionMedicaDTO;
import co.edu.uniquindio.proyecto.dto.NuevaPasswordDTO;
import co.edu.uniquindio.proyecto.dto.itemCitaDTO;
import co.edu.uniquindio.proyecto.dto.paciente.*;
import co.edu.uniquindio.proyecto.modelo.entidades.Paciente;
import co.edu.uniquindio.proyecto.repositorios.PacienteRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.PacienteServicio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PacienteServicioImpl implements PacienteServicio {

    private final PacienteRepo pacienteRepo;

    @Override
    public int registrarse(RegistroPacienteDTO pacienteDTO) throws Exception{
        if( estaRepetidaCedula(pacienteDTO.cedula()) ){
            throw new Exception("La cédula ya se encuentra registrada");
        }
        if( estaRepetidoCorreo(pacienteDTO.correo()) ) {
            throw new Exception("El correo ya se encuentra registrado");
        }
        Paciente paciente = new Paciente();
//Datos de la Cuenta
        paciente.setCorreo( pacienteDTO.correo() );
        paciente.setPassword( pacienteDTO.password() );
//Datos del Usuario
        paciente.setNombre( pacienteDTO.nombre() );
        paciente.setCedula( pacienteDTO.cedula() );
        paciente.setTelefono( pacienteDTO.telefono() );
        paciente.setCiudad( pacienteDTO.ciudad() );
        paciente.setUrlFoto( pacienteDTO.urlFoto() );
//Datos del Paciente
        paciente.setFechaNacimiento( pacienteDTO.fechaNacimiento() );
        paciente.setEps( pacienteDTO.eps() );
        paciente.setAlergias( pacienteDTO.alergias() );
        paciente.setTipoSangre( pacienteDTO.tipoSangre() );
        Paciente pacienteCreado = pacienteRepo.save( paciente );
        return pacienteCreado.getCedula();
    }

    @Override
    public int editarPerfil(int codigoPaciente) {
        return 0;
    }

    @Override
    public int editarPerfil(DetallePacienteDTO pacienteDTO) throws Exception {
        Optional<Paciente> pacienteBuscado = pacienteRepo.findById(pacienteDTO.cedula());
        if( pacienteBuscado.isEmpty() ){
            throw new Exception("No existe un paciente con el código "+pacienteDTO.cedula());
        }
        Paciente paciente = pacienteBuscado.get();
//Datos de la Cuenta
        paciente.setCorreo( pacienteDTO.correo() );
//Datos del Usuario
        paciente.setNombre( pacienteDTO.nombre() );
        paciente.setCedula( pacienteDTO.cedula() );
        paciente.setTelefono( pacienteDTO.telefono() );
        paciente.setCiudad( pacienteDTO.ciudad() );
        paciente.setUrlFoto( pacienteDTO.urlFoto() );
//Datos del Paciente
        paciente.setFechaNacimiento( pacienteDTO.fechaNacimiento() );
        paciente.setEps( pacienteDTO.eps() );
        paciente.setAlergias( pacienteDTO.alergias() );
        paciente.setTipoSangre( pacienteDTO.tipoSangre() );
//Como el objeto paciente ya tiene un id, el save() no crea un nuevo registro sino que
//        actualiza el que ya existe
        pacienteRepo.save( paciente );
        return paciente.getCedula();
    }


    @Override
    public void eliminarCuenta(int codigo) throws Exception {
        Optional<Paciente> pacienteBuscado = pacienteRepo.findById(codigo);
        if( pacienteBuscado.isEmpty() ){
            throw new Exception("No existe un paciente con el código "+codigo);
        }
        pacienteRepo.delete( pacienteBuscado.get() );
    }


    @Override
    public DetallePacienteDTO verDetallePaciente(int codigo) throws Exception {
        Optional<Paciente> pacienteBuscado = pacienteRepo.findById(codigo);
        if( pacienteBuscado.isEmpty() ){
            throw new Exception("No existe un paciente con el código "+codigo);
        }
        Paciente paciente = pacienteBuscado.get();
//Hacemos un mapeo de un objeto de tipo Paciente a un objeto de tipo DetallePacienteDTO
        return new DetallePacienteDTO( paciente.getCedula(),
                paciente.getNombre(), paciente.getTelefono(), paciente.getUrlFoto(), paciente.getCiudad(),
                paciente.getFechaNacimiento(), paciente.getAlergias(), paciente.getEps(),
                paciente.getTipoSangre(), paciente.getCorreo() );
    }

    @Override
    public void enviarLinkRecuperacion(String email) throws Exception {

    }

    @Override
    public void cambiarPassword(NuevaPasswordDTO nuevaPasswordDTO) {

    }

    @Override
    public int agendarCita(RegistroCitaDTO registroCitaDTO) throws Exception {
        return 0;
    }

    @Override
    public void crearPQRS(RegistroPQRSDTO registroPQRSDTO) {

    }


    @Override
    public void responderPQRS() {

    }

    @Override
    public List<ItemPacienteDTO> listarTodos(){
        List<Paciente> pacientes = pacienteRepo.findAll();
        List<ItemPacienteDTO> repuesta = new ArrayList<>();
//Hacemos un mapeo de cada uno de los objetos de tipo Paciente a objetos de tipo ItemPacienteDTO
        for (Paciente paciente : pacientes) {
            repuesta.add( new ItemPacienteDTO( paciente.getCedula(),
                    paciente.getNombre(), paciente.getCiudad() ) );
        }
        return repuesta;
    }


    @Override
    public void filtrarCitasPorFecha() {

    }

    @Override
    public void filtrarCitasPorMedico() {

    }

    @Override
    public DetalleAtencionMedicaDTO verDetalleCita() {
        return null;
    }

    public boolean estaRepetidaCedula(int id) {
            return pacienteRepo.existsById(id);
        }

    public boolean estaRepetidoCorreo(String correo){
        return pacienteRepo.findByCorreo(correo);
    }

}
