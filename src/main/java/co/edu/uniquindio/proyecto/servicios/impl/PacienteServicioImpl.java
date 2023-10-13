package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.dto.paciente.*;
import co.edu.uniquindio.proyecto.excepciones.Excepciones;
import co.edu.uniquindio.proyecto.modelo.entidades.*;
import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoCita;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoPQRS;
import co.edu.uniquindio.proyecto.repositorios.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.PacienteServicio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PacienteServicioImpl implements PacienteServicio {

    private final PacienteRepo pacienteRepo;
    private final CitaRepo citaRepo;
    private final MedicoRepo medicoRepo;
    private final HorarioRepo horarioRepo;
    private final DiaLibreRepo diaLibreRepo;
    private final PqrsRepo pqrsRepo;
    private final MensajeRepo mensajeRepo;

    @Override
    public int registrarse(RegistroPacienteDTO registroPacienteDTO) throws Exception{
        if( estaRepetidaCedula(registroPacienteDTO.cedula()) ){
            throw new Excepciones("La cédula ya se encuentra registrada");
        }

        if( estaRepetidoCorreo(registroPacienteDTO.correo()) ) {
            throw new Excepciones("El correo ya se encuentra registrado");
        }

        Paciente paciente = new Paciente();
//Datos de la Cuenta
        paciente.setCorreo( registroPacienteDTO.correo() );
        paciente.setPassword( registroPacienteDTO.password() );
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

        return pacienteCreado.getCedula();
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
        paciente.setFoto( pacienteDTO.urlFoto() );
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
                paciente.getNombre(), paciente.getTelefono(), paciente.getFoto(), paciente.getCiudad(),
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

        List<Cita> citasPorPaciente= citaRepo.findCitasByClienteId(registroCitaDTO.idPaciente());

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

        Optional<Cita> citaBuscada = citaRepo.findById(registroPQRSDTO.CodigoCita());

        if( citaBuscada.isEmpty() ){
            throw new Excepciones("No existe una cita con el código ");
        }

        List<Pqrs> pqrsPacienteList= pqrsRepo.findByCodigoPaciente(registroPQRSDTO.codigoPaciente());
        if(pqrsPacienteList.size()==3){
            throw new Excepciones("Usted ya tiene 3 PQRS en el sistema, no es posible crear otro");
        }

        Cita cita = citaBuscada.get();
        pqrsNuevo.setFechaCreacion(LocalDate.now());
        pqrsNuevo.setEstado(EstadoPQRS.EN_PROCESO);
        pqrsNuevo.setMotivo(registroPQRSDTO.movito());
        pqrsNuevo.setCita(cita);

        pqrsRepo.save(pqrsNuevo);

        mensajeNuevo.setPqrs(pqrsNuevo);
        mensajeNuevo.setContenido(registroPQRSDTO.Detalle());
        mensajeNuevo.setFecha(LocalDate.now());
        mensajeRepo.save(mensajeNuevo);

    }

    @Override
    public void responderPQRS(int codigoPQRS)
    {
        pqrsRepo.findById(codigoPQRS);
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

    @Override
    public List<MedicosDisponiblesGetDTO> mostrarMedicosDisponibles(MedicosDisponiblesDTO medicosDisponiblesDTO) throws Exception{

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
                medicosDisponiblesGetDTOS.add( new MedicosDisponiblesGetDTO(medico.getNombre(), horaInicio));
                horaInicio = horaInicio.plusMinutes(30);
            }
        }
    for (int i=0; i<citas.size();i++){
        if(citas.get(i).getFechaCita().toLocalDate().equals(medicosDisponiblesDTO.fecha() )){
            System.out.println(citas.get(i).getCodigo() +" "+ citas.get(i).getMedico().getNombre()+" hora de cita "+citas.get(i).getFechaCita().toLocalTime());
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

    public boolean estaRepetidaCedula(int id) {
        return pacienteRepo.existsById(id);
    }

    public boolean estaRepetidoCorreo(String correo){

        Paciente paciente = pacienteRepo.findByCorreo(correo);

        return paciente != null;
    }

    void listarPQRSPaciente(){

    }
}

