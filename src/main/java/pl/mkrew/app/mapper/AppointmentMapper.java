package pl.mkrew.app.mapper;

import org.springframework.stereotype.Service;
import pl.mkrew.app.domain.Appointment;
import pl.mkrew.app.dto.AppointmentDto;

@Service
public class AppointmentMapper {

    public AppointmentDto mapToDto(Appointment appointment) {
        return AppointmentDto.builder()
                .id(appointment.getId())
                .date(appointment.getDate())
                .time(appointment.getTime())
                .rckikId(appointment.getRckik().getId())
                .userId(appointment.getUser().getId())
                .build();
    }

    public Appointment mapToAppointment(AppointmentDto appointmentDto) {
        return Appointment.builder()
                .id(appointmentDto.getId())
                .date(appointmentDto.getDate())
                .time(appointmentDto.getTime())
                .rckik(appointmentDto.getRckikId())
                .rckik(appointmentDto.)
                .build();
    }

}
