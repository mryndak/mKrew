package pl.mkrew.app.mapper;

import org.mapstruct.Mapper;
import pl.mkrew.app.domain.Appointment;
import pl.mkrew.app.dto.AppointmentDto;

@Mapper(componentModel= "spring")
public interface AppointmentMapper {

    abstract AppointmentDto mapToDto(Appointment appointment);

    abstract Appointment mapToAppointment(AppointmentDto appointmentDto);


}
