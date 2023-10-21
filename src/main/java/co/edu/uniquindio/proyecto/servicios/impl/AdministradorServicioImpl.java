package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.dto.admin.DetalleMedicoDTO;
import co.edu.uniquindio.proyecto.dto.admin.HistorialConsultas;
import co.edu.uniquindio.proyecto.dto.admin.ItemMedicoDTO;
import co.edu.uniquindio.proyecto.dto.admin.RespuestaDTO;
import co.edu.uniquindio.proyecto.dto.paciente.ItemPacienteDTO;
import co.edu.uniquindio.proyecto.excepciones.Excepciones;
import co.edu.uniquindio.proyecto.modelo.entidades.*;
import co.edu.uniquindio.proyecto.modelo.enums.Estado;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoPQRS;
import co.edu.uniquindio.proyecto.repositorios.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.AdministradorServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministradorServicioImpl implements AdministradorServicio {

    private final MedicoRepo medicoRepo;
    private final PqrsRepo pqrsRepo;
    private final CitaRepo citaRepo;
    private final MensajeRepo mensajeRepo;
    private final HorarioRepo horarioRepo;
    private final PacienteRepo pacienteRepo;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public int crearMedico(MedicoDTO medicoDTO) throws Exception {

        if (estaRepetidaCedula(medicoDTO.cedula())) {
            throw new Excepciones("La cédula ya se encuentra registrada");
        }

        if (estaRepetidoCorreo(medicoDTO.correo())) {
            throw new Excepciones("El correo ya se encuentra registrado");
        }

        Medico medico = new Medico();
        medico.setCedula(medicoDTO.cedula());
        medico.setTelefono(medicoDTO.telefono());
        medico.setNombre(medicoDTO.nombre());
        medico.setEspecialidad(medicoDTO.especialidad());
        medico.setCiudad(medicoDTO.ciudad());
        String passwordEncriptada = passwordEncoder.encode(medicoDTO.password());
        medico.setPassword(passwordEncriptada);
        medico.setFoto(medicoDTO.urlFoto());
        medico.setCorreo(medicoDTO.correo());
        medico.setEstado(true);

        Medico medicoNuevo = medicoRepo.save(medico);

        Optional<Horario> horarioBuscado = horarioRepo.findByMedicoId(medicoNuevo.getCedula());
        if(horarioBuscado.isEmpty()){
            Horario horario=new Horario();
            horario.setMedico(medicoNuevo);
            horario.setHoraInicio(medicoDTO.horaInicioJornada());
            horario.setHoraFin(medicoDTO.horaFinJornada());
            horarioRepo.save(horario);
        }else{
            horarioBuscado.get().setMedico(medicoNuevo);
            horarioBuscado.get().setHoraInicio(medicoDTO.horaInicioJornada());
            horarioBuscado.get().setHoraFin(medicoDTO.horaFinJornada());
            horarioRepo.save(horarioBuscado.get());
        }
        return medicoNuevo.getCedula();
    }

    @Override
    public int actualizarMedico(MedicoDTO medicoDTO) throws Exception {
        Optional<Medico> buscado = medicoRepo.findById(medicoDTO.cedula());
        Optional<Horario> horarioBuscado = horarioRepo.findByMedicoId(medicoDTO.cedula());

        if (buscado.isEmpty()) {
            throw new Exception("El código " + medicoDTO.cedula() + " no existe");
        }

        if (!buscado.get().isEstado()) {
            throw new Exception("El cóodigo " + medicoDTO.cedula() + " no existe");
        }

        Medico medico = buscado.get();
        medico.setCedula(medicoDTO.cedula());
        medico.setTelefono(medicoDTO.telefono());
        medico.setNombre(medicoDTO.nombre());
        medico.setEspecialidad(medicoDTO.especialidad());
        medico.setCiudad(medicoDTO.ciudad());
        medico.setCorreo(medicoDTO.correo());
        medico.setFoto(medicoDTO.urlFoto());
        horarioBuscado.get().setHoraInicio(medicoDTO.horaInicioJornada());
        horarioBuscado.get().setHoraFin(medicoDTO.horaFinJornada());

        Medico medicoEditado = medicoRepo.save(medico);
        horarioRepo.save(horarioBuscado.get());

        // emailServicio.enviarCorreo(new EmailDTO("Asunto", "Cuerpo mensaje", "Correo destino"));

        return medicoEditado.getCedula();

    }

    @Override
    public DetalleMedicoDTO obtenerMedico(int codigo) throws Exception {
        Optional<Medico> buscado = medicoRepo.findById(codigo);

        if (buscado.isEmpty()) {
            throw new Exception("El código " + codigo + " no existe");
        }
        if (!buscado.get().isEstado()) {
            throw new Exception("El cóodigo " + codigo + " no existe");
        }
        Medico obtenido = buscado.get();

        DetalleMedicoDTO detalleMedicoDTO = new DetalleMedicoDTO(

                obtenido.getNombre(),
                obtenido.getCedula(),
                obtenido.getCiudad(),
                obtenido.getEspecialidad(),
                obtenido.getTelefono(),
                obtenido.getCorreo(),
                obtenido.getFoto()
        );

        return detalleMedicoDTO;

    }

    @Override
    public void eliminarMedico(int codigo) throws Exception {

        Optional<Medico> buscado = medicoRepo.findById(codigo);

        if (buscado.isEmpty()) {
            throw new Exception("El cóodigo " + codigo + " no existe");
        }

        //medicoRepo.delete( buscado.get() );
        Medico obtenido = buscado.get();
        if (!obtenido.isEstado()) {
            throw new Exception("El cóodigo " + codigo + " no existe");
        }
        obtenido.setEstado(false);
        obtenido.setCorreo(Integer.toString(codigo) + "@inexistente.com");
        medicoRepo.save(obtenido);
        horarioRepo.deleteByMedicoId(obtenido.getCedula());

    }

    @Override
    public List<ItemMedicoDTO> listarMedicos() throws Exception {

        List<Medico> medicos = medicoRepo.findAll();
        List<ItemMedicoDTO> respuesta = new ArrayList<>();

        for (Medico medico : medicos) {
            if (medico.isEstado()) {
                respuesta.add(new ItemMedicoDTO(
                        medico.getCedula(),
                        medico.getNombre(),
                        medico.getFoto(),
                        medico.getEspecialidad(),
                        medico.getHorario().getHoraInicio(),
                        medico.getHorario().getHoraFin()));
            }
        }
        return respuesta;
    }

    @Override
    public void responderPQRS(RespuestaDTO respuestaDTO) throws Exception {

        Mensaje mensajeNuevo = new Mensaje();
        Mensaje mensajeAnterior = mensajeRepo.getById(respuestaDTO.codigoMensaje());
        mensajeNuevo.setFecha(LocalDate.now());
        mensajeNuevo.setContenido(respuestaDTO.mensaje());
        mensajeNuevo.setMensaje(mensajeAnterior);
        mensajeNuevo.setPqrs(mensajeAnterior.getPqrs());

        mensajeRepo.save(mensajeNuevo);
    }

    @Override
    public List<ItemPQRSDTO> listarPQRS() throws Exception {

        List<Pqrs> listaPqrs = pqrsRepo.findAll(); //select * from pqrs
        List<ItemPQRSDTO> respuesta = new ArrayList<>();

        for (Pqrs p : listaPqrs) {
            respuesta.add(new ItemPQRSDTO(
                    p.getCodigo(),
                    p.getEstado(),
                    p.getMotivo(),
                    p.getFechaCreacion(),
                    p.getCita().getPaciente().getNombre()
            ));
        }

        return respuesta;
    }

    @Override
    public DetallePQRSDTO verDetallePQRS(int codigo) throws Exception {
        Optional<Pqrs> opcional = pqrsRepo.findById(codigo);

        if (opcional.isEmpty()) {
            throw new Exception("El código" + codigo + " no está asociado a ningún PQRS");
        }

        Pqrs pqrs = opcional.get();

        return new
                DetallePQRSDTO(
                pqrs.getCodigo(),
                pqrs.getEstado(),
                pqrs.getMotivo(),
                pqrs.getCita().getPaciente().getNombre(),
                pqrs.getCita().getMedico().getNombre(),
                pqrs.getCita().getMedico().getEspecialidad(),
                pqrs.getFechaCreacion(),
                new ArrayList<>() //Falta meter los mensajes del pqrs
        );
    }

    @Override
    public List<ItemCitaAdminDTO> listarCitas() throws Exception {
        List<Cita> listaCitas = citaRepo.findAll();
        List<ItemCitaAdminDTO> respuesta = new ArrayList<>();

        for (Cita c : listaCitas
        ) {
            respuesta.add(new ItemCitaAdminDTO(
                    c.getCodigo(),
                    c.getPaciente().getCedula(),
                    c.getPaciente().getNombre(),
                    c.getMedico().getNombre(),
                    c.getMedico().getEspecialidad(),
                    c.getEstadoCita(),
                    c.getFechaCreacion()
            ));
        }
        return respuesta;
    }

    @Override
    public void cambiarEstadoPQRS(int codigoPQRS, EstadoPQRS estadoPQRS) throws Exception {

        Optional<Pqrs> opcional = pqrsRepo.findById(codigoPQRS);

        if (opcional.isEmpty()) {
            throw new Exception("El código" + codigoPQRS + " no está asociado a ningún PQRS");
        }
        Pqrs pqrs = opcional.get();
        pqrs.setEstado(estadoPQRS);
        pqrsRepo.save(pqrs);

    }

    @Override
    public List<ItemPacienteDTO> listarTodos() {
        List<Paciente> pacientes = pacienteRepo.findAll();
        List<ItemPacienteDTO> repuesta = new ArrayList<>();
//Hacemos un mapeo de cada uno de los objetos de tipo Paciente a objetos de tipo ItemPacienteDTO
        for (Paciente paciente : pacientes) {
            repuesta.add(new ItemPacienteDTO(paciente.getCedula(),
                    paciente.getNombre(), paciente.getCiudad()));
        }
        return repuesta;
    }

    public List<HistorialConsultas> verHistorialDeConsultas() {
        List<Cita> citas = citaRepo.listarCitasFinalizadas();
        List<HistorialConsultas> historialConsultas = new ArrayList<>();
        for (Cita cita : citas) {
            historialConsultas.add(new HistorialConsultas(
                    cita.getFechaCita(),
                    cita.getMedico().getCedula(),
                    cita.getMedico().getNombre(),
                    cita.getPaciente().getCedula(),
                    cita.getPaciente().getNombre()));
        }
        return historialConsultas;
    }

    public List<HistorialConsultas> verHistorialDeConsultasMedico(int codigoMedico) {
        List<Cita> citas = citaRepo.listarCitasFinalizadasPorMedico(codigoMedico);
        List<HistorialConsultas> historialConsultas = new ArrayList<>();
        for (Cita cita : citas) {
            historialConsultas.add(new HistorialConsultas(
                    cita.getFechaCita(),
                    cita.getMedico().getCedula(),
                    cita.getMedico().getNombre(),
                    cita.getPaciente().getCedula(),
                    cita.getPaciente().getNombre()));
        }
        return historialConsultas;
    }

    public boolean estaRepetidaCedula(int id) {
        Optional<Medico> medicoBuscado = medicoRepo.findById(id);
        if (!medicoBuscado.isEmpty()) {
            if (!medicoBuscado.get().isEstado()) {
                return false;
            }
            return true;
        }
        return medicoRepo.existsById(id);
    }

    public boolean estaRepetidoCorreo(String correo) {
        Medico medico = medicoRepo.findByCorreo(correo);

        return medico != null;
    }
}
