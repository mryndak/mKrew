package pl.mkrew.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mkrew.app.domain.Appointment;
import pl.mkrew.app.domain.RCKiK;
import pl.mkrew.app.domain.UserEntity;
import pl.mkrew.app.repository.AppointmentRepository;
import pl.mkrew.app.repository.RCKiKRepository;
import pl.mkrew.app.repository.UserRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final RCKiKRepository rcKiKRepository;
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    public void createScheduleAppointment(Long rckikId, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, int visitDuring) {
        // TODO: walidacja danych

        int daysCount = Period.between(startDate, endDate).getDays() + 1;
        long dayTime = Duration.between(startTime, endTime).getSeconds() / 60;

        RCKiK rckik = rcKiKRepository.findById(rckikId)
                .orElseThrow();

        List<Appointment> appointments = new ArrayList<>();
        for (int i = 0; i < daysCount; i++) {
            LocalTime startVisit = LocalTime.of(startTime.getHour(), startTime.getMinute());
            for (int j = 0; j < dayTime; j = j + visitDuring) {
                Appointment appointment = Appointment.builder()
                        .date(startDate.plusDays(i))
                        .time(startVisit)
                        .rckik(rckik)
                        .available(true)
                        .build();
                appointments.add(appointment);
                startVisit = startVisit.plusMinutes(visitDuring);
            }
        }

        appointmentRepository.saveAll(appointments);
    }

    public void makeReservation(Long appointmentID, Long userId) {
        Appointment appointment = appointmentRepository.findById(appointmentID).orElseThrow();
        UserEntity user = userRepository.findById(userId).orElseThrow();

        appointment.setAvailable(false);
        appointment.setUser(user);
        appointmentRepository.save(appointment);
    }

    public void deleteReservation(Long reservationId) {
        Appointment appointment = appointmentRepository.findById(reservationId).orElseThrow();

        appointment.setAvailable(true);
        appointment.setUser(null);
        appointmentRepository.save(appointment);
    }
    public void confirmVisit (Long reservationId) {
        Appointment appointment = appointmentRepository.findById(reservationId).orElseThrow();

        appointment.setConfirmVisit(true);
        appointmentRepository.save(appointment);
    }
}

