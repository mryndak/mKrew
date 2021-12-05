package pl.mkrew.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mkrew.app.domain.Appointment;
import pl.mkrew.app.domain.RCKiK;
import pl.mkrew.app.domain.Symptoms;
import pl.mkrew.app.domain.UserEntity;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionnaireDto {
    //TODO zapytać czy trzeba dodać obiekty RCKiK i UserEntity
    private Long id;
    private String firstName;
    private String lastName;

    @OneToMany
    private UserEntity userEntity;

    @OneToMany
    private RCKiK rckik;

    @OneToMany
    private Appointment appointmentId;

    public QuestionnaireDto(Long id, String firstName, String lastName) {
    }

    private Boolean alreadyDonatedBlood;
    private int lastYearDonatedBlood;
    private Boolean yourHealthyAtTheMoment;
    private Boolean anyDentalTreatmentsIn7Days;
    private Boolean haveCareOfDoctorOrFeverAbove38Degrees;
    private Boolean takenAnyMedicationsLast4Weeks;
    private String witchMedicationsAndWhen;
    private Boolean takenAnyMedicineAcetylsalicylicAcidIn3Days;
    private Boolean haveYouVaccinatedInTheLast4Weeks;
    private String witchVaccinAndWhen;
    private Set<Symptoms> weightLossFeverEnlargementOfTheLymphNodes = new HashSet<>();
    private Boolean hadGastroscopyBiopsyOtherDiagnosticIn6Weeks;
    private Boolean haveSeriouslyIllOrMajorSurgeryOrAccident;
    private String seriouslyIllOrMajorSurgeryOrAccidentAndWhenItHappend;
    private Boolean haveReceivedBloodTransfusionsOrBloodComponents;
    private Boolean haveTransplantRecipient;
    private String whatTransplantRecipientAndWhen;
    private Boolean receivedGrowthHormone;
    private Boolean anyoneInYourFamilyHaveSufferedCreutzfeldtJakobDisease;
    private Boolean staying6MonthsOrMoreInGreatBritainFranceIreland;
    private Boolean haveStayedOutsidePolandLast12Months;
    private String whereOustidePolandAndWhen;
    private Boolean stayedInCentralAndWestAfricanCountriesOrThailand;
    private Boolean livedOrBeenInAreaEndemicMalariaOrOtherTropicalDiseases;
    private String whatEpidemicAndWhen;
    private Boolean hadMalariaOrOtherTropicalDiseases;
    private String whenSeekForMalariaOrTropicalDiseases;
    private Boolean haveStayedInAreasWestNileVirusWasTransmittedToHumans;
    private Boolean hadTattoosAcupunctureCosmeticDepilationPiercing;
    private Boolean haveContactWithBloodOrToolsContaminatedWithBlood;
    private Boolean hadJaundice;
    private Boolean yourLifeOrSexualPartnerHadJaundice;
    private Boolean hadContactWithContagiouslyIllPersonIn12Months;
    private Boolean readInfoOnInfectiousDiseasesForBloodDonors;
    private Boolean haveBeenCustodyOrImprisonmentLast6Months;
    private Boolean haveBeenAdvisedNotToDonateBlood;
    private Boolean haveDangerousJobOrHobby;
    private Boolean pregnantOrHaveBeenPregnantLast12Months;
    private String dateOfLastPragnecy;
    private Boolean areYouMenstruating;
    private String whenLastMenstruating;
    private Boolean anyHormoneInjectionsBetween_1965_And_1985;
    private Boolean consentProcedureBloodDonation;
    private Boolean agreement1;
    private Boolean agreement2;
    private Boolean agreement3;
    private Boolean agreement4;
    private Boolean agreement5;


}
