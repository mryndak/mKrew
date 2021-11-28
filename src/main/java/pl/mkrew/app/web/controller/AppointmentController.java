package pl.mkrew.app.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.bind.annotation.*;
import pl.mkrew.app.service.AppointmentService;
import pl.mkrew.app.web.controller.request.CreateScheduleAppointmentRequest;
import pl.mkrew.app.web.request.ReservationRequest;

import javax.validation.Valid;
import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/schedule")
    public ResponseEntity<Void> createScheduleAppointment(@RequestBody @Valid CreateScheduleAppointmentRequest request) {
        validate(request);

        appointmentService.createScheduleAppointment(request.getRckikId(), request.getStartDate(), request.getEndDate(),
                request.getStartTime(), request.getEndTime(), request.getVisitDuring());
        return ResponseEntity.noContent().build();
    }

    private void validate(CreateScheduleAppointmentRequest request) {
        if (request.getEndDate().isBefore(request.getStartDate())) {
            throw new RequestRejectedException("Appointment end date is before start date");
        }
       else if (request.getStartDate().isAfter(request.getEndDate())) {
            throw new RequestRejectedException("Appointment start date is after end date");
        }
       else if (request.getStartDate().isBefore(LocalDate.now())) {
            throw new RequestRejectedException("Appointment start date is before today's date");
        }
    }

    @PostMapping("/reservation")
    public ResponseEntity<Void> createReservation(@RequestBody @Valid ReservationRequest reservationRequest) {
        appointmentService.makeReservation(reservationRequest.getAppointmentId(), reservationRequest.getUserId());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/reservarion/{reservationId}")
    public void removeReservation(@PathVariable("reservationId") Long reservationId) {
        appointmentService.deleteReservation(reservationId);
    }

    @PostMapping("/confirmationVisit/{appointmentId}")
    public void confirmationVisit(@PathVariable("appointmentId") Long appointmentId) {
        appointmentService.confirmVisit(appointmentId);
    }

}
