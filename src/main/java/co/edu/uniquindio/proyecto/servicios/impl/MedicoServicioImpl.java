package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.ItemCitaDTO;
import co.edu.uniquindio.proyecto.dto.medico.*;
import co.edu.uniquindio.proyecto.dto.paciente.DetalleCita;
import co.edu.uniquindio.proyecto.excepciones.Excepciones;
import co.edu.uniquindio.proyecto.modelo.entidades.Atencion;
import co.edu.uniquindio.proyecto.modelo.entidades.Cita;
import co.edu.uniquindio.proyecto.modelo.entidades.DiaLibre;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoCita;
import co.edu.uniquindio.proyecto.repositorios.AtencionRepo;
import co.edu.uniquindio.proyecto.repositorios.CitaRepo;
import co.edu.uniquindio.proyecto.repositorios.DiaLibreRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.MedicoServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.PacienteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicoServicioImpl implements MedicoServicio {

    private final CitaRepo citaRepo;
    private final AtencionRepo atencionRepo;
    private final DiaLibreRepo diaLibreRepo;
    private final PacienteServicio pacienteServicio;

    @Override
    public List<ItemCitaDTO> listarCitasPendientesDia(CitasFechaDTO listaCitasFechaDTO) {
        List<Cita> citas = citaRepo.listarCitasPorEstadoAsignado(listaCitasFechaDTO.codigoMedico(), listaCitasFechaDTO.fecha());
        List<ItemCitaDTO> respuesta = new ArrayList<>();

        for (Cita c: citas) {
            respuesta.add(new ItemCitaDTO(
                    c.getCodigo(),
                    c.getEstadoCita(),
                    c.getPaciente().getNombre(),
                    c.getFechaCita()
            ));
        }
        return respuesta;
    }

    @Override
    //todo: Aquí envío Este método es utilizado para cuando quiera asignar un día libre, envío todas las citas que tengo pendientes
    // de esta manera se si puedo o no asignar el día libre
    public List<Cita> listarCitasPendientes(int codigoMedico) {
        List<Cita> citas = citaRepo.listarCitasAFuturo(codigoMedico);
        System.out.println(citas.size());
        return citas;
    }

    @Override
    //todo: Aquí envío todo lo que requiere el médico para ver los detalles del paciente
    public DetalleAtencionMedicaDTO atenderCita(int codigoCita) throws Exception{
            Optional <Cita> citaBuscada = citaRepo.findById(codigoCita);

            if(citaBuscada.isEmpty()){
                throw new Excepciones("Error, El código No existe");
            }

        DetalleAtencionMedicaDTO detalleAtencionMedicaDTO = new DetalleAtencionMedicaDTO(
                    citaBuscada.get().getPaciente().getCedula(),
                    citaBuscada.get().getPaciente().getNombre(),
                    citaBuscada.get().getPaciente().getTelefono(),
                    citaBuscada.get().getPaciente().getEps(),
                    citaBuscada.get().getPaciente().getTipoSangre(),
                    citaBuscada.get().getPaciente().getCiudad(),
                    citaBuscada.get().getMedico().getEspecialidad(),
                    citaBuscada.get().getMedico().getNombre(),
                    citaBuscada.get().getMotivo());

        return detalleAtencionMedicaDTO;
    }

    @Override
    public void agendarDiaLibre(AgendarDiaLibre agendarDiaLibre) throws Exception{

        Optional<DiaLibre> diaLibre = diaLibreRepo.findByMedico(agendarDiaLibre.codigoMedico());

        List<Cita> citasPendientesDelDia = listarCitasPendientes(agendarDiaLibre.codigoMedico());

       for(int i=0;i<citasPendientesDelDia.size();i++){
           if(agendarDiaLibre.fecha().equals(citasPendientesDelDia.get(i).getFechaCita().toLocalDate())){
               throw new Excepciones("Usted ya tiene citas agendadas para la fecha dada "+ agendarDiaLibre.fecha());
           }
       }

        if (diaLibre.isEmpty()){

            if(agendarDiaLibre.fecha().isAfter(LocalDate.now())){

                DiaLibre diaLibre1 = new DiaLibre();
                diaLibre1.setCodigo(agendarDiaLibre.codigoMedico());
                diaLibre1.setDia(agendarDiaLibre.fecha());

                diaLibreRepo.save(diaLibre1);
            }else{
                throw new Excepciones("La fecha ingresada es incorrecta");

            }
        }else {
            throw new Excepciones("Error, usted ya tiene asignado un día libre");

        }

    }

    @Override
    public void finalizarCita(FinalizarCitaDTO finalizarCitaDTO) throws Exception{
        Optional <Cita> citaBuscada = citaRepo.findById(finalizarCitaDTO.codigoCita());

        if(citaBuscada.isEmpty()){
            throw new Excepciones("Error, El código de cita "+finalizarCitaDTO.codigoCita()+ " No existe");
        }

        if(citaBuscada.get().getEstadoCita()==EstadoCita.ASIGNADA){
            Cita cita = citaBuscada.get();
            Atencion atencion = new Atencion();
            atencion.setCita(cita);
            atencion.setDiagnostico(finalizarCitaDTO.diagnostico());
            atencion.setNotasMedicas(finalizarCitaDTO.notas_medicas());
            atencion.setTratamiento(finalizarCitaDTO.tratamiento());
            cita.setEstadoCita(EstadoCita.FINALIZADA);

            atencionRepo.save(atencion);
            citaRepo.save(cita);
        }else {
            throw new Excepciones("La cita "+citaBuscada.get().getCodigo()+" no está asignada");
        }

    }

    @Override
    public List<DetalleCita> listarHistorialMedico(int codigoPaciente) {

        List<DetalleCita> detalleCitas = pacienteServicio.verHistorialMedico(codigoPaciente);

        return detalleCitas;

    }

    @Override
    public List<AtencionMedica> listarCitasRealizadasMedico(int codigoMedico) {
        List<Cita> citas = citaRepo.historialDeAtenciones(codigoMedico);
        System.out.println(citas.size());
        List<AtencionMedica> respuesta = new ArrayList<>();
        for (Cita cita:
             citas) {
            respuesta.add(new AtencionMedica(
                    cita.getCodigo(),
                    cita.getPaciente().getNombre(),
                    cita.getFechaCita(),
                    cita.getMotivo()
            ));
        }

        return respuesta;
    }
}
