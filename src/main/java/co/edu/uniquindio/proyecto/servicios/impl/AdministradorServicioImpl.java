package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.dto.admin.DetalleMedicoDTO;
import co.edu.uniquindio.proyecto.dto.admin.HistorialConsultas;
import co.edu.uniquindio.proyecto.dto.admin.ItemMedicoDTO;
import co.edu.uniquindio.proyecto.dto.admin.RespuestaDTO;
import co.edu.uniquindio.proyecto.excepciones.Excepciones;
import co.edu.uniquindio.proyecto.modelo.entidades.*;
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

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public int crearMedico(MedicoDTO medicoDTO) throws Exception {


        if( estaRepetidaCedula(medicoDTO.cedula()) ){
            throw new Excepciones("La cédula ya se encuentra registrada");
        }

        if( estaRepetidoCorreo(medicoDTO.correo()) ) {
            throw new Excepciones("El correo ya se encuentra registrado");
        }

        Medico medico = new Medico();

        medico.setCedula(medicoDTO.cedula());
        medico.setTelefono(medicoDTO.telefono());
        medico.setNombre(medicoDTO.nombre());
        medico.setEspecialidad(medicoDTO.especialidad());
        medico.setCiudad(medicoDTO.ciudad());
        String passwordEncriptada = passwordEncoder.encode( medicoDTO.password() );
        medico.setPassword(passwordEncriptada);
        medico.setFoto(medicoDTO.urlFoto());
        medico.setCorreo(medicoDTO.correo());
        medico.setEstado(true);

        Medico medicoNuevo = medicoRepo.save(medico);

        Horario horario=new Horario();
        horario.setMedico(medicoNuevo);
        horario.setHoraInicio(medicoDTO.horaInicioJornada());
        horario.setHoraFin(medicoDTO.horaFinJornada());
        horarioRepo.save(horario);

        return medicoNuevo.getCedula();
    }

    @Override
    public DetalleMedicoDTO obtenerMedico(int codigo) throws Exception {
        Optional<Medico> buscado = medicoRepo.findById(codigo);

        if(buscado.isEmpty() ){
            throw new Exception("El código "+codigo+" no existe");
        }
        if(!buscado.get().isEstado()){
            throw new Exception("El cóodigo "+codigo+" no existe");
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
    public int actualizarMedico(DetalleMedicoDTO detalleMedicoDTO) throws Exception {
        Optional<Medico> buscado = medicoRepo.findById(detalleMedicoDTO.cedula());

        if(buscado.isEmpty() ){
            throw new Exception("El código "+detalleMedicoDTO.cedula()+" no existe");
        }

        if(!buscado.get().isEstado()){
            throw new Exception("El cóodigo "+detalleMedicoDTO.cedula()+" no existe");
        }

        Medico medico = buscado.get();
        medico.setCedula(detalleMedicoDTO.cedula() );
        medico.setTelefono(detalleMedicoDTO.telefono());
        medico.setNombre(detalleMedicoDTO.nombre() );
        medico.setEspecialidad( detalleMedicoDTO.especialidad() );
        medico.setCiudad(detalleMedicoDTO.ciudad());
        medico.setCorreo(detalleMedicoDTO.correo() );
        medico.setFoto(detalleMedicoDTO.urlFoto());

        Medico medicoNuevo = medicoRepo.save(medico);

       // emailServicio.enviarCorreo(new EmailDTO("Asunto", "Cuerpo mensaje", "Correo destino"));

        return medicoNuevo.getCedula();

    }

    @Override
    public void eliminarMedico(int codigo) throws Exception {

        Optional<Medico> buscado = medicoRepo.findById(codigo);

        if(buscado.isEmpty()){
            throw new Exception("El cóodigo "+codigo+" no existe");
        }

        //medicoRepo.delete( buscado.get() );
        Medico obtenido = buscado.get();
        if(!obtenido.isEstado()){
            throw new Exception("El cóodigo "+codigo+" no existe");
        }
        obtenido.setEstado( false );

        medicoRepo.save(obtenido);

    }

    @Override
    public List<ItemMedicoDTO> listarMedicos() throws Exception {

        List<Medico> medicos = medicoRepo.findAll();
        List<ItemMedicoDTO> respuesta = new ArrayList<>();

        for(Medico medico : medicos){
            if(medico.isEstado()) {
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
    public List<ItemPQRSDTO> listarPQRS() throws Exception {

        List<Pqrs> listaPqrs = pqrsRepo.findAll(); //select * from pqrs
        List<ItemPQRSDTO> respuesta = new ArrayList<>();

        for (Pqrs p : listaPqrs){
            respuesta.add( new ItemPQRSDTO(
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

        if(opcional.isEmpty()){
            throw new Exception("El código" +codigo+" no está asociado a ningún PQRS");
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
    public PQRSDTOAdmin verDetallePQRS() throws Exception {
        return null;
    }

    @Override
    public List<ItemCitaAdminDTO> listarCitas() throws Exception {
        List<Cita> listaCitas = citaRepo.findAll();
        List<ItemCitaAdminDTO> respuesta = new ArrayList<>();

        for (Cita c: listaCitas
             ) {
            respuesta.add( new ItemCitaAdminDTO(
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
    public void cambiarEstadoPQRS(int codigoPQRS, EstadoPQRS estadoPQRS)throws Exception{

        Optional <Pqrs> opcional = pqrsRepo.findById(codigoPQRS);

        if(opcional.isEmpty()){
            throw new Exception("El código" +codigoPQRS+" no está asociado a ningún PQRS");
        }
        Pqrs pqrs = opcional.get();
        pqrs.setEstado(estadoPQRS);
        pqrsRepo.save(pqrs);

    }

    public boolean estaRepetidaCedula(int id) {
        return medicoRepo.existsById(id);
    }

    public boolean estaRepetidoCorreo(String correo)
    {
        Medico medico = medicoRepo.findByCorreo(correo);

        return medico != null;
    }

    public List<HistorialConsultas> verHistorialDeConsultas(){
        List<Cita> citas = citaRepo.listarCitasFinalizadas();
        List<HistorialConsultas> historialConsultas = new ArrayList<>();
        for (Cita cita: citas) {
            historialConsultas.add(new HistorialConsultas(
                    cita.getFechaCita(),
                    cita.getMedico().getCedula(),
                    cita.getMedico().getNombre(),
                    cita.getPaciente().getCedula(),
                    cita.getPaciente().getNombre()));
        }
        return  historialConsultas;
    }
    public List<HistorialConsultas> verHistorialDeConsultasMedico(int codigoMedico){
        List<Cita> citas = citaRepo.listarCitasFinalizadasPorMedico(codigoMedico);
        List<HistorialConsultas> historialConsultas = new ArrayList<>();
        for (Cita cita: citas) {
            historialConsultas.add(new HistorialConsultas(
                    cita.getFechaCita(),
                    cita.getMedico().getCedula(),
                    cita.getMedico().getNombre(),
                    cita.getPaciente().getCedula(),
                    cita.getPaciente().getNombre()));
        }
        return  historialConsultas;
    }
}
