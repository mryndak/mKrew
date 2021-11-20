package pl.mkrew.app.web.request;

import lombok.Data;

@Data
public class ReservationRequest {

    private Long userId;
    private Long appointmentID;
}
