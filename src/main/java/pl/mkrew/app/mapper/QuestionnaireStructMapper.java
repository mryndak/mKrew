package pl.mkrew.app.mapper;

import org.mapstruct.Mapper;
import pl.mkrew.app.domain.Questionnaire;
import pl.mkrew.app.dto.QuestionnaireDto;

@Mapper(componentModel = "spring")
public interface QuestionnaireStructMapper {

    QuestionnaireDto mapToDto(Questionnaire questionnaire);

    Questionnaire mapToQuestionnaire(QuestionnaireDto questionnaireDto);
}

