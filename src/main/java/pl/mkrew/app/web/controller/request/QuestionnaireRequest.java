package pl.mkrew.app.web.controller.request;

import lombok.Data;
import pl.mkrew.app.dto.QuestionnaireDto;

@Data
public class QuestionnaireRequest {

    private Long appointmentID;

    private QuestionnaireDto questionnaireDto;

}
