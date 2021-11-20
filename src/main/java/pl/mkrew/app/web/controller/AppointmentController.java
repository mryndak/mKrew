package pl.mkrew.app.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mkrew.app.service.AppointmentService;
import pl.mkrew.app.web.controller.request.CreateScheduleAppointmentRequest;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/schedule")
    public ResponseEntity<Void> createScheduleAppointment(@RequestBody @Valid CreateScheduleAppointmentRequest request) {
        appointmentService.createScheduleAppointment(request.getRckikId(), request.getStartDate(), request.getEndDate(),
                request.getStartTime(), request.getEndTime(), request.getVisitDuring());
        return ResponseEntity.noContent().build();
    }

}
