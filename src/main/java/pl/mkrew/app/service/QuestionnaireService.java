package pl.mkrew.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mkrew.app.domain.Questionnaire;
import pl.mkrew.app.dto.QuestionnaireDto;
import pl.mkrew.app.mapper.QuestionnaireStructMapper;
import pl.mkrew.app.repository.QuestionnaireRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionnaireService {

    @Deprecated
    private final List<QuestionnaireDto> questionnaires = new ArrayList<>();
    private final QuestionnaireRepository questionnaireRepository;
    private final QuestionnaireStructMapper questionnaireStructMapper;


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
        Questionnaire questionnaire = questionnaireStructMapper.mapToQuestionnaire(questionnaireDto);
        questionnaire.setLastName(questionnaireDto.getLastName());
        questionnaire.setFirstName(questionnaireDto.getFirstName());
        questionnaire.setAlreadyDonatedBlood(questionnaireDto.getAlreadyDonatedBlood());
        questionnaire.setAnyDentalTreatmentsIn7Days(questionnaireDto.getAnyDentalTreatmentsIn7Days());
        questionnaire.setAnyHormoneInjectionsBetween_1965_And_1985(questionnaireDto.getAnyHormoneInjectionsBetween_1965_And_1985());
        questionnaire.setAnyoneInYourFamilyHaveSufferedCreutzfeldtJakobDisease(questionnaireDto.getAnyoneInYourFamilyHaveSufferedCreutzfeldtJakobDisease());
        questionnaire.setAreYouMenstruating(questionnaireDto.getAreYouMenstruating());
        questionnaire.setConsentProcedureBloodDonation(questionnaireDto.getConsentProcedureBloodDonation());
        questionnaire.setDateOfLastPragnecy(questionnaireDto.getDateOfLastPragnecy());
        questionnaire.setHadContactWithContagiouslyIllPersonIn12Months(questionnaireDto.getHadContactWithContagiouslyIllPersonIn12Months());
        questionnaire.setHadGastroscopyBiopsyOtherDiagnosticIn6Weeks(questionnaireDto.getHadGastroscopyBiopsyOtherDiagnosticIn6Weeks());
        questionnaire.setHadJaundice(questionnaireDto.getHadJaundice());
        questionnaire.setHaveBeenCustodyOrImprisonmentLast6Months(questionnaireDto.getHaveBeenCustodyOrImprisonmentLast6Months());
        questionnaire.setHadMalariaOrOtherTropicalDiseases(questionnaireDto.getHadMalariaOrOtherTropicalDiseases());
        questionnaire.setHadTattoosAcupunctureCosmeticDepilationPiercing(questionnaireDto.getHadTattoosAcupunctureCosmeticDepilationPiercing());
        questionnaire.setHaveBeenAdvisedNotToDonateBlood(questionnaireDto.getHaveBeenAdvisedNotToDonateBlood());
        questionnaire.setHaveCareOfDoctorOrFeverAbove38Degrees(questionnaireDto.getHaveCareOfDoctorOrFeverAbove38Degrees());
        questionnaire.setHaveContactWithBloodOrToolsContaminatedWithBlood(questionnaireDto.getHaveContactWithBloodOrToolsContaminatedWithBlood());
        questionnaire.setHaveDangerousJobOrHobby(questionnaireDto.getHaveDangerousJobOrHobby());
        questionnaire.setHaveReceivedBloodTransfusionsOrBloodComponents(questionnaireDto.getHaveReceivedBloodTransfusionsOrBloodComponents());
        questionnaire.setHaveSeriouslyIllOrMajorSurgeryOrAccident(questionnaireDto.getHaveSeriouslyIllOrMajorSurgeryOrAccident());
        questionnaire.setHaveStayedInAreasWestNileVirusWasTransmittedToHumans(questionnaireDto.getHaveStayedInAreasWestNileVirusWasTransmittedToHumans());
        questionnaire.setHaveStayedOutsidePolandLast12Months(questionnaireDto.getHaveStayedOutsidePolandLast12Months());
        questionnaire.setHaveTransplantRecipient(questionnaireDto.getHaveTransplantRecipient());
        questionnaire.setHaveYouVaccinatedInTheLast4Weeks(questionnaireDto.getHaveYouVaccinatedInTheLast4Weeks());
        questionnaire.setLastYearDonatedBlood(questionnaireDto.getLastYearDonatedBlood());
        questionnaire.setAppointmentId(questionnaireDto.getAppointmentId());
        questionnaire.setRckik(questionnaireDto.getRckik());
        questionnaire.setUserEntity(questionnaireDto.getUserEntity());
        questionnaire.setLivedOrBeenInAreaEndemicMalariaOrOtherTropicalDiseases(questionnaireDto.getLivedOrBeenInAreaEndemicMalariaOrOtherTropicalDiseases());
        questionnaire.setPregnantOrHaveBeenPregnantLast12Months(questionnaireDto.getPregnantOrHaveBeenPregnantLast12Months());
        questionnaire.setReadInfoOnInfectiousDiseasesForBloodDonors(questionnaireDto.getReadInfoOnInfectiousDiseasesForBloodDonors());
        questionnaire.setReceivedGrowthHormone(questionnaireDto.getReceivedGrowthHormone());
        questionnaire.setSeriouslyIllOrMajorSurgeryOrAccidentAndWhenItHappend(questionnaireDto.getSeriouslyIllOrMajorSurgeryOrAccidentAndWhenItHappend());
        questionnaire.setStayedInCentralAndWestAfricanCountriesOrThailand(questionnaireDto.getStayedInCentralAndWestAfricanCountriesOrThailand());
        questionnaire.setStaying6MonthsOrMoreInGreatBritainFranceIreland(questionnaireDto.getStaying6MonthsOrMoreInGreatBritainFranceIreland());
        questionnaire.setTakenAnyMedicationsLast4Weeks(questionnaireDto.getTakenAnyMedicationsLast4Weeks());
        questionnaire.setTakenAnyMedicineAcetylsalicylicAcidIn3Days(questionnaireDto.getTakenAnyMedicineAcetylsalicylicAcidIn3Days());
        questionnaire.setWeightLossFeverEnlargementOfTheLymphNodes(questionnaireDto.getWeightLossFeverEnlargementOfTheLymphNodes());
        questionnaire.setWhatEpidemicAndWhen(questionnaireDto.getWhatEpidemicAndWhen());
        questionnaire.setWhatTransplantRecipientAndWhen(questionnaireDto.getWhatTransplantRecipientAndWhen());
        questionnaire.setWhenLastMenstruating(questionnaireDto.getWhenLastMenstruating());
        questionnaire.setWhenSeekForMalariaOrTropicalDiseases(questionnaireDto.getWhenSeekForMalariaOrTropicalDiseases());
        questionnaire.setWhereOustidePolandAndWhen(questionnaireDto.getWhereOustidePolandAndWhen());
        questionnaire.setWitchMedicationsAndWhen(questionnaireDto.getWitchMedicationsAndWhen());
        questionnaire.setWitchVaccinAndWhen(questionnaireDto.getWitchVaccinAndWhen());
        questionnaire.setYourHealthyAtTheMoment(questionnaireDto.getYourHealthyAtTheMoment());
        questionnaire.setYourLifeOrSexualPartnerHadJaundice(questionnaireDto.getYourLifeOrSexualPartnerHadJaundice());
        questionnaire.setAgreement1(questionnaireDto.getAgreement1());
        questionnaire.setAgreement2(questionnaireDto.getAgreement2());
        questionnaire.setAgreement3(questionnaireDto.getAgreement3());
        questionnaire.setAgreement4(questionnaireDto.getAgreement4());
        questionnaire.setAgreement5(questionnaireDto.getAgreement5());
        questionnaireRepository.save(questionnaire);

        // TODO dodać wszystkie pola, wyglądać to ma jak w addUser
    }
}
