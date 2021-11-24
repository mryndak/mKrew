package pl.mkrew.app.mapper;

import org.springframework.stereotype.Service;
import pl.mkrew.app.domain.Questionnaire;
import pl.mkrew.app.dto.QuestionnaireDto;

@Service
public class QuestionnaireMapper {

    public QuestionnaireDto mapToDto(Questionnaire questionnaire) {
        return QuestionnaireDto.builder()
                .id(questionnaire.getId())
                .firstName(questionnaire.getFirstName())
                .lastName(questionnaire.getLastName())
                .build();
    } //TODO zapytać czy wszystkie pola mapować

    public Questionnaire mapToQuestionnaire(QuestionnaireDto questionnaireDto){
        return Questionnaire.builder()
                .firstName(questionnaireDto.getFirstName())
                .lastName(questionnaireDto.getLastName())
                .build();
    } //TODO zapytać czy wszystkie pola mapować
}
