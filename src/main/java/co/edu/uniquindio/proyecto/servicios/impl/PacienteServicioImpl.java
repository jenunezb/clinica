package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.dto.admin.DetalleMedicoDTO;
import co.edu.uniquindio.proyecto.dto.admin.RespuestaDTO;
import co.edu.uniquindio.proyecto.dto.paciente.*;
import co.edu.uniquindio.proyecto.excepciones.Excepciones;
import co.edu.uniquindio.proyecto.modelo.entidades.*;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoCita;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoPQRS;
import co.edu.uniquindio.proyecto.repositorios.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.PacienteServicio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PacienteServicioImpl implements PacienteServicio {

    private final PacienteRepo pacienteRepo;
    private final CitaRepo citaRepo;
    private final MedicoRepo medicoRepo;
    private final PqrsRepo pqrsRepo;
    private final MensajeRepo mensajeRepo;
    private final AtencionRepo atencionRepo;
    private final CuentaRepo cuentaRepo;
    private final EmailServicio emailServicio;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public int registrarse(RegistroPacienteDTO registroPacienteDTO) throws Exception{
        if( estaRepetidaCedula(registroPacienteDTO.cedula()) ){
            throw new Excepciones("La cédula "+ registroPacienteDTO.cedula()+" ya se encuentra registrada");
        }

        if( estaRepetidoCorreo(registroPacienteDTO.correo()) ) {
            throw new Excepciones("El correo ya se encuentra registrado");
        }

        Paciente paciente = new Paciente();
//Datos de la Cuenta
        paciente.setCorreo( registroPacienteDTO.correo() );
        String passwordEncriptada = passwordEncoder.encode( registroPacienteDTO.password() );
        paciente.setPassword(passwordEncriptada);
        paciente.setEstado(true);
//Datos del Usuario
        paciente.setNombre( registroPacienteDTO.nombre() );
        paciente.setCedula( registroPacienteDTO.cedula() );
        paciente.setTelefono( registroPacienteDTO.telefono() );
        paciente.setCiudad( registroPacienteDTO.ciudad() );
        paciente.setFoto( registroPacienteDTO.urlFoto() );
//Datos del Paciente
        paciente.setFechaNacimiento( registroPacienteDTO.fechaNacimiento() );
        paciente.setEps( registroPacienteDTO.eps() );
        paciente.setAlergias( registroPacienteDTO.alergias() );
        paciente.setTipoSangre( registroPacienteDTO.tipoSangre() );

//Guardo el paciente
        Paciente pacienteCreado = pacienteRepo.save( paciente );

       // emailServicio.enviarCorreo(new EmailDTO( pacienteCreado.getCorreo(),"Asunto", "Cuerpo mensaje"));

        return pacienteCreado.getCodigo();
    }

    @Override
    public int editarPerfil(DetallePacienteDTO pacienteDTO) throws Exception {
        Optional<Paciente> pacienteBuscado = pacienteRepo.findByCedula(pacienteDTO.cedula());
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
        paciente.setFoto( pacienteDTO.urlFoto() );
//Datos del Paciente
        paciente.setFechaNacimiento( pacienteDTO.fechaNacimiento() );
        paciente.setEps( pacienteDTO.eps() );
        paciente.setAlergias( pacienteDTO.alergias() );
        paciente.setTipoSangre( pacienteDTO.tipoSangre() );
//Como el objeto paciente ya tiene un id, el save() no crea un nuevo registro sino que
//        actualiza el que ya existe
        pacienteRepo.save( paciente );
        return paciente.getCodigo();
    }

    @Override
    public void eliminarCuenta(int codigo) throws Exception {
        Optional<Paciente> pacienteBuscado = pacienteRepo.findById(codigo);
        if( pacienteBuscado.isEmpty() ){
            throw new Exception("No existe un paciente con el código "+codigo);
        }
        Paciente paciente = pacienteBuscado.get();
        paciente.setEstado(false);
        pacienteRepo.save(paciente);
    }

    @Override
    public void enviarLinkRecuperacion(String email) throws Exception {

        Optional<Cuenta> optionalCuenta = cuentaRepo.findByCorreo(email);

        if(optionalCuenta.isEmpty()){
            throw new Excepciones("No existe una cuenta con el correo "+email);
        }

        LocalTime fecha = LocalTime.now();

        String parametro = Base64.getEncoder().encodeToString((optionalCuenta.get().getCodigo()+": "+fecha).getBytes());

        emailServicio.enviarCorreo( new EmailDTO(
                optionalCuenta.get().getCorreo(),
                "Recuperacion de contraseña",
                "Hola, para recuperar tu contraseña ingresa al siquiente link: https://xxxxxx/recuperar-password/"+parametro

        ));
    }

    @Override
    public List<MedicosDisponiblesGetDTO> mostrarMedicosDisponibles(MedicosDisponiblesDTO medicosDisponiblesDTO) throws Excepciones{

        if(medicosDisponiblesDTO.fecha().isBefore(LocalDate.now())){
            throw new Excepciones("la fecha ingresada es incorrecta, verifique que la fecha sea de hoy o a futuro");
        }

        List<Medico> medicos = medicoRepo.findMedicosByEspecialidadAndHorario(
                medicosDisponiblesDTO.especialidad(),
                medicosDisponiblesDTO.fecha());

        if(medicos.isEmpty()){
            throw new Excepciones("no hay médicos disponibles");
        }

        List<Cita> citas = citaRepo.findAll();

        List<MedicosDisponiblesGetDTO> medicosDisponiblesGetDTOS = new ArrayList<>();

        for (Medico medico: medicos) {

            LocalTime horaInicio = medico.getHorario().getHoraInicio();
            while (horaInicio.isBefore(medico.getHorario().getHoraFin())){
                medicosDisponiblesGetDTOS.add( new MedicosDisponiblesGetDTO(medico.getNombre(), horaInicio, medico.getCodigo()));
                horaInicio = horaInicio.plusMinutes(30);
            }
        }
        for (int i=0; i<citas.size();i++){
            if(citas.get(i).getFechaCita().toLocalDate().equals(medicosDisponiblesDTO.fecha() )){
//            System.out.println(citas.get(i).getCodigo() +" "+ citas.get(i).getMedico().getNombre()+" hora de cita "+citas.get(i).getFechaCita().toLocalTime());
                for (int j=0;j<medicosDisponiblesGetDTOS.size();j++){
                    if(citas.get(i).getMedico().getNombre()==medicosDisponiblesGetDTOS.get(j).nombreMedico()
                            && citas.get(i).getFechaCita().toLocalTime().equals(medicosDisponiblesGetDTOS.get(j).horaDisponible())){
                        medicosDisponiblesGetDTOS.remove(j);
                    }
                }
            }
        }

        return medicosDisponiblesGetDTOS;
    }

    @Override
    public DetallePacienteDTO obtenerPaciente(int codigo) throws Exception {
        Optional<Paciente> buscado = pacienteRepo.findById(codigo);

        if (buscado.isEmpty()) {
            throw new Exception("El código " + codigo + " no existe");
        }
        if (!buscado.get().isEstado()) {
            throw new Exception("El cóodigo " + codigo + " no existe");
        }
        Paciente obtenido = buscado.get();

        DetallePacienteDTO detallePacienteDTO = new DetallePacienteDTO(
                obtenido.getCedula(),
                obtenido.getNombre(),
                obtenido.getTelefono(),
                obtenido.getFoto(),
                obtenido.getCiudad(),
                obtenido.getFechaNacimiento(),
                obtenido.getAlergias(),
                obtenido.getEps(),
                obtenido.getTipoSangre(),
                obtenido.getCorreo()
        );

        return detallePacienteDTO;
    }

    @Override
    public List<ItemCitaAdminDTO> listarCitas(int codigo) throws Exception{
        DetallePacienteDTO paciente = obtenerPaciente(codigo);
        List<Cita> listaCitas = citaRepo.findCitasByPacienteId(paciente.cedula());
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
    public int agendarCita(RegistroCitaDTO registroCitaDTO) throws Exception {

        Cita cita = new Cita();

        //Verifico que el paciente esté en el sistema
        Optional<Paciente> pacienteBuscado = pacienteRepo.findById(registroCitaDTO.idPaciente());
        if( pacienteBuscado.isEmpty() ){
            throw new Exception("No existe un paciente con el código "+registroCitaDTO.idPaciente());
        }

        Optional<Medico> medicoBuscado = medicoRepo.findById(registroCitaDTO.idMedico());
        if( medicoBuscado.isEmpty() ){
            throw new Exception("No existe un medico con el código "+registroCitaDTO.idMedico());
        }

        //Debo validar que el paciente no tenga mas de 3 citas activas

        List<Cita> citasPorPaciente= citaRepo.findCitasByPacienteId(pacienteBuscado.get().getCedula());

        if(citasPorPaciente.size()==3){
            throw new Excepciones("No es posible agendar más citas, puesto que ya tiene 3 citas activas");
        }

        //Creamos la cita con los datos de entrada
        Paciente paciente = pacienteBuscado.get();
        Medico medico = medicoBuscado.get();
        cita.setPaciente(paciente);
        cita.setMedico(medico);
        cita.setFechaCreacion(LocalDateTime.now());
        cita.setMotivo(registroCitaDTO.motivo());
        cita.setEstadoCita(EstadoCita.ASIGNADA);
        cita.setFechaCita(registroCitaDTO.fechaCita());
        citaRepo.save(cita);

        return cita.getCodigo();
    }

    @Override
    public void crearPQRS(RegistroPQRSDTO registroPQRSDTO) throws Exception{

        Pqrs pqrsNuevo = new Pqrs();
        Mensaje mensajeNuevo = new Mensaje();

        Optional<Cita> citaBuscada = citaRepo.findById(registroPQRSDTO.codigoCita());
        if(!pqrsRepo.findByCodigoCita(registroPQRSDTO.codigoCita(), citaBuscada.get().getPaciente().getCedula()).isEmpty()){
            throw new Exception("Usted ya tiene asignado un PQRS a la cita actual");
        }
        if (!citaBuscada.get().getEstadoCita().equals(EstadoCita.FINALIZADA)){
            throw new Exception("Su cita debe estar finalizada para poder crear un PQRS");
        }

        if( citaBuscada.isEmpty() ){
            throw new Exception("No existe una cita con el código ");
        }

        List<Pqrs> pqrsPacienteList= pqrsRepo.findByCodigoPaciente(citaBuscada.get().getPaciente().getCodigo());
        if(pqrsPacienteList.size()==3){
            throw new Excepciones("Usted ya tiene 3 PQRS en el sistema, no es posible crear otro");
        }

        Cita cita = citaBuscada.get();
        pqrsNuevo.setFechaCreacion(LocalDate.now());
        pqrsNuevo.setEstado(EstadoPQRS.EN_PROCESO);
        pqrsNuevo.setMotivo(registroPQRSDTO.motivo());
        pqrsNuevo.setCita(cita);

        pqrsRepo.save(pqrsNuevo);

        mensajeNuevo.setPqrs(pqrsNuevo);
        mensajeNuevo.setContenido(registroPQRSDTO.motivo());
        mensajeNuevo.setFecha(LocalDate.now());
        mensajeRepo.save(mensajeNuevo);
    }

    @Override
    public DetallePQRSDTO responderPQRS(ResponderPqrsPaciente responderPqrsPaciente) throws Exception
    {
        Optional<Mensaje> mensajeAdminBuscado = mensajeRepo.findById(responderPqrsPaciente.codigoMensajeAdmin());
        if(mensajeAdminBuscado.isEmpty()){
            throw new Excepciones("el Mensaje no puede ser respondido, debido a que no existe un mensaje del administrador");
        }
        Mensaje mensaje = new Mensaje();
        mensaje.setPqrs(mensajeAdminBuscado.get().getPqrs());
        mensaje.setMensaje(mensajeAdminBuscado.get());
        mensaje.setContenido(responderPqrsPaciente.mensajeRespuesta());
        mensaje.setFecha(LocalDate.now());
        mensajeRepo.save(mensaje);
        List<Mensaje> mensajeList = mensajeRepo.findByIdPqrs(mensaje.getPqrs().getCodigo());
        List<RespuestaDTO> respuestaDTOS = new ArrayList<>();
        for (Mensaje mensaje1:
             mensajeList) {
            respuestaDTOS.add(new RespuestaDTO(mensaje1.getCodigo(),mensaje1.getContenido()));
        }
        DetallePQRSDTO detallePQRSDTO = new DetallePQRSDTO(mensaje.getPqrs().getCodigo(),
                mensaje.getPqrs().getEstado(), mensaje.getPqrs().getMotivo(),
                mensaje.getPqrs().getCita().getPaciente().getNombre(),
                mensaje.getPqrs().getCita().getMedico().getNombre(),
                mensaje.getPqrs().getCita().getMedico().getEspecialidad(),
                mensaje.getPqrs().getFechaCreacion(),
                respuestaDTOS);

        return detallePQRSDTO;
    }

    @Override
    public List<DetalleCita> filtrarCitasPorFecha(String codigoPaciente, LocalDate fecha) {

        List<Cita> citas = citaRepo.listaCitasPorFecha(codigoPaciente, fecha);

        List<DetalleCita> detalleCitas = new ArrayList<>();

        for (Cita cita:
                citas) {
            Optional<Atencion> atencion = atencionRepo.buscarAtencionPorCodigoCita(cita.getCodigo());
            if(atencion.isEmpty()){
                detalleCitas.add(new DetalleCita(
                        cita.getCodigo(),
                        cita.getEstadoCita(),
                        cita.getFechaCita(),
                        cita.getMotivo(),
                        cita.getMedico().getNombre(),
                        cita.getMedico().getEspecialidad(),
                        "",
                        "",
                        ""
                ));
            }else {
                detalleCitas.add(new DetalleCita(
                        cita.getCodigo(),
                        cita.getEstadoCita(),
                        cita.getFechaCita(),
                        cita.getMotivo(),
                        cita.getMedico().getNombre(),
                        cita.getMedico().getEspecialidad(),
                        atencion.get().getNotasMedicas(),
                        atencion.get().getDiagnostico(),
                        atencion.get().getTratamiento()));
            }

        }

        return detalleCitas;
    }

    @Override
    public List<DetalleCita> filtrarCitasPorMedico(int codigoPaciente, int codigoMedico) {
        List<Cita> citas = citaRepo.listaFechasPorMedico(codigoPaciente, codigoMedico);
        System.out.println(citas.size());
        List<DetalleCita> detalleCitas = new ArrayList<>();

        for (Cita cita:
                citas) {
            Optional<Atencion> atencion = atencionRepo.buscarAtencionPorCodigoCita(cita.getCodigo());
            if(atencion.isEmpty()){
                detalleCitas.add(new DetalleCita(
                        cita.getCodigo(),
                        cita.getEstadoCita(),
                        cita.getFechaCita(),
                        cita.getMotivo(),
                        cita.getMedico().getNombre(),
                        cita.getMedico().getEspecialidad(),
                        "",
                        "",
                        ""
                ));
            }else {
                detalleCitas.add(new DetalleCita(
                        cita.getCodigo(),
                        cita.getEstadoCita(),
                        cita.getFechaCita(),
                        cita.getMotivo(),
                        cita.getMedico().getNombre(),
                        cita.getMedico().getEspecialidad(),
                        atencion.get().getNotasMedicas(),
                        atencion.get().getDiagnostico(),
                        atencion.get().getTratamiento()));
            }

        }

        return detalleCitas;
    }

    @Override
    public List<DetalleCita> verHistorialMedico(String codigoPaciente) {

        List<Cita> citas = citaRepo.findCitasByPacienteId(codigoPaciente);
        List<DetalleCita> detalleCitas = new ArrayList<>();

        for (Cita cita:
             citas) {
            Optional<Atencion> atencion = atencionRepo.buscarAtencionPorCodigoCita(cita.getCodigo());
            if(atencion.isEmpty()){
                detalleCitas.add(new DetalleCita(
                        cita.getCodigo(),
                        cita.getEstadoCita(),
                        cita.getFechaCita(),
                        cita.getMotivo(),
                        cita.getMedico().getNombre(),
                        cita.getMedico().getEspecialidad(),
                        "",
                        "",
                        ""
                ));
            }else {
                detalleCitas.add(new DetalleCita(
                        cita.getCodigo(),
                        cita.getEstadoCita(),
                        cita.getFechaCita(),
                        cita.getMotivo(),
                        cita.getMedico().getNombre(),
                        cita.getMedico().getEspecialidad(),
                        atencion.get().getNotasMedicas(),
                        atencion.get().getDiagnostico(),
                        atencion.get().getTratamiento()));
            }

        }

        return detalleCitas;
    }

    public boolean estaRepetidaCedula(String id) {
        return pacienteRepo.existsByCedula(id);
    }

    public boolean estaRepetidoCorreo(String correo){

        Paciente paciente = pacienteRepo.findByCorreo(correo);

        return paciente != null;
    }

    @Override
    public List<ItemPQRSDTO> listarPQRSPaciente(int codigoPaciente){
        List<Pqrs> pqrsList = pqrsRepo.findByCodigoPaciente(codigoPaciente);
        List<ItemPQRSDTO> itemPQRSDTOS = new ArrayList<>();
        for (Pqrs pqrs:
             pqrsList) {
            itemPQRSDTOS.add(new ItemPQRSDTO(
                    pqrs.getCodigo(),
                    pqrs.getEstado(),
                    pqrs.getMotivo(),
                    pqrs.getFechaCreacion(),
                    pqrs.getCita().getPaciente().getNombre(),
                    pqrs.getCita().getMedico().getNombre()));
        }
        System.out.println(itemPQRSDTOS);
        return itemPQRSDTOS;
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
                paciente.getNombre(), paciente.getTelefono(), paciente.getFoto(), paciente.getCiudad(),
                paciente.getFechaNacimiento(), paciente.getAlergias(), paciente.getEps(),
                paciente.getTipoSangre(), paciente.getCorreo() );
    }

    @Override
    public void cambiarPassword(NuevaPasswordDTO nuevaPasswordDTO) throws Exception {
        Optional<Paciente> pacienteBuscado = pacienteRepo.findById(nuevaPasswordDTO.codigoPaciente());

        if(pacienteBuscado.isEmpty()){
            throw new Excepciones("El paciente no fue encontrado");
        }
        String passwordEncriptada = passwordEncoder.encode( nuevaPasswordDTO.password() );
        Paciente paciente = pacienteBuscado.get();
        paciente.setPassword(passwordEncriptada);

        pacienteRepo.save(paciente);

    }
}

