package pl.mkrew.app.web.controller;

import com.sun.jdi.request.InvalidRequestStateException;
import lombok.RequiredArgsConstructor;
import org.hibernate.hql.internal.QueryExecutionRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mkrew.app.service.AppointmentService;
import pl.mkrew.app.web.controller.request.CreateScheduleAppointmentRequest;
import pl.mkrew.app.web.request.ReservationRequest;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;

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
            throw new RequestRejectedException("Schedule end date is before start date");
        }
       else if (request.getStartDate().isAfter(request.getEndDate())) {
            throw new RequestRejectedException("Schedule start date is after end date");
        }
       else if (request.getStartDate().isBefore(LocalDate.now())) {
            throw new RequestRejectedException("Schedule start date is before today's date");
        }
    }

    @PostMapping("/reservation")
    public ResponseEntity<Void> createReservation(@RequestBody @Valid ReservationRequest reservationRequest) {
        appointmentService.makeReservation(reservationRequest.getAppointmentID(), reservationRequest.getUserId());
        return ResponseEntity.noContent().build();
    }

}