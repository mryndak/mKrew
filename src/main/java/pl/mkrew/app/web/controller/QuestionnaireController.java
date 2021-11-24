package pl.mkrew.app.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.mkrew.app.dto.QuestionnaireDto;
import pl.mkrew.app.service.QuestionnaireService;

import java.util.List;

@RestController
@RequestMapping("/v1/questionnaire")
public class QuestionnaireController {

    private QuestionnaireService questionnaireService;

    @Autowired
    public QuestionnaireController(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }

    @GetMapping
    public List<QuestionnaireDto> getQuestionnaires() {
        return questionnaireService.getQuestionnaires();
    }

    @PostMapping
    public void  addQuestionnaire(@RequestBody QuestionnaireDto questionnaireDto) {
        questionnaireService.addQuestionnaire(questionnaireDto);
    }

}
