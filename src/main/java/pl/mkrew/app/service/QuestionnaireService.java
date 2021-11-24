package pl.mkrew.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mkrew.app.domain.Questionnaire;
import pl.mkrew.app.dto.QuestionnaireDto;
import pl.mkrew.app.repository.QuestionnaireRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionnaireService {

    @Deprecated
    private List<QuestionnaireDto> questionnaires = new ArrayList<>();
    private final QuestionnaireRepository questionnaireRepository;

    public List<QuestionnaireDto> getQuestionnaires() {
        return questionnaireRepository.findAll()
                .stream()
                .map(questionnaires -> new QuestionnaireDto(
                        questionnaires.getId(),
                        questionnaires.getFirstName(),
                        questionnaires.getLastName()
                )).collect(Collectors.toList());
    }

    public void addQuestionnaire(QuestionnaireDto questionnaireDto) {
        Questionnaire questionnaire = Questionnaire.builder()
                .firstName(questionnaireDto.getFirstName())
                .lastName(questionnaireDto.getLastName())
                .build();
        questionnaireRepository.save(questionnaire);
        // TODO zapytać czy trzeba wszystkie pola z Dto i czy tak to ma wyglądać
    }
}
