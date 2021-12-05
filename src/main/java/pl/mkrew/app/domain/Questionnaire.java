package pl.mkrew.app.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "questionnaire")
@Entity
public class Questionnaire {
    //TODO zapytać czy trzeba wstrzyknąć obiekty klas Rckik i UserEntity
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private UserEntity userEntity;

    @ManyToOne
    private RCKiK rckik;

    @OneToOne
    private Appointment appointmentId;

    //personal information
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    //Czy już oddawał/a Pan/Pani krew? Jeżeli tak to w którym roku ostatnio? ……….

    private Boolean alreadyDonatedBlood;
    private int lastYearDonatedBlood;

    //Czy czuje się Pan/Pani obecnie zdrowy/a?

    private Boolean yourHealthyAtTheMoment;

    //Czy w ciągu ostatnich 7 dni przechodził/a Pan Pani jakieś zabiegi stomatologiczne?

    private Boolean anyDentalTreatmentsIn7Days;

    //Czy w ciągu ostatnich 4 tygodni chorował/a Pan/Pani lub pozostawał/a pod opieką lekarza albo miał/a gorączkę powyższej 38 stopni?

    private Boolean haveCareOfDoctorOrFeverAbove38Degrees;

    //Czy w ciągu ostatnich 4 tygodni przyjmował/a Pan/Pani lekarstwa (tabletki, zastrzyki, czopki, maści i inne).
    //Jeżeli tak to jakie i kiedy? ……….

    private Boolean takenAnyMedicationsLast4Weeks;
    private String witchMedicationsAndWhen;

    //Czy w ciągu ostatnich 3 dni przyjmował/a Pan/Pani lek, którego składnikiem jest kwas acetylosalicylowy (np. aspiryna)?

    private Boolean takenAnyMedicineAcetylsalicylicAcidIn3Days;

    //Czy w ciągu ostatnich 4 tygodni przechodził/a Pan/Pani szczepienia?
    //Jeżeli tak, jakie? ……………. Kiedy? …………….

    private Boolean haveYouVaccinatedInTheLast4Weeks;
    private String witchVaccinAndWhen;

    //Czy zauważył/a Pan/Pani u siebie następujące objawy: a) nieuzasadniony spadek ciężaru ciała,
    //b) nieuzasadnioną gorączkę, c) powiększenie węzłów chłonnych?

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Symptoms> weightLossFeverEnlargementOfTheLymphNodes = new HashSet<>();

    //Czy w ciągu ostatnich 6 miesięcy miał Pan/Pani wykonywaną gastroskopię, biopsję lub inne badanie diagnostyczne?

    private Boolean hadGastroscopyBiopsyOtherDiagnosticIn6Weeks;

    //Czy w ciągu ostatnich 6 miesięcy lub od czasu ostatniego oddania krwi chorował/a Pan/Pani ciężko albo przebył/a poważny zabieg operacyjny lub wypadek?
    //Jeżeli tak, to jaki i kiedy? ……………..

    private Boolean haveSeriouslyIllOrMajorSurgeryOrAccident;
    private String seriouslyIllOrMajorSurgeryOrAccidentAndWhenItHappend;

    //Czy kiedykolwiek otrzymywał/a Pan/Pani transfuzje krwi lub jej składników?

    private Boolean haveReceivedBloodTransfusionsOrBloodComponents;

    //Czy kiedykolwiek był/a Pan/Pani biorcą przeszczepu (np. rogówki lub innych tkanek?
    //Jeżeli tak, to jakich? …………..

    private Boolean haveTransplantRecipient;
    private String whatTransplantRecipientAndWhen;

    //Czy kiedykolwiek otrzymywał/a Pan/Pani hormon wzrostu?

    private Boolean receivedGrowthHormone;

    //Czy ktokolwiek z Pana/Pani rodziny cierpi lub cierpiał na chorobę Creutzfeldta-Jakoba?

    private Boolean anyoneInYourFamilyHaveSufferedCreutzfeldtJakobDisease;

    //Czy Pan/Pani w okresie 01 stycznia 1980 roku do 31 grudnia 1996 roku przebywała łącznie przez okres 6 miesięcy lub dłużej w Wielkiej Brytanii, Francji lub Irlandii?

    private Boolean staying6MonthsOrMoreInGreatBritainFranceIreland;

    //Czy w ciągu ostatnich 12 miesięcy przebywał Pan/Pani poza terenem Polski?
    //Jeżeli tak to gdzie i kiedy ……………..

    private Boolean haveStayedOutsidePolandLast12Months;
    private String whereOustidePolandAndWhen;

    //Czy w ciągu ostatnich 6 miesięcy przebywał Pan/Pani w krajach Afryki Środkowej i Zachodniej lub w Tajlandii

    private Boolean stayedInCentralAndWestAfricanCountriesOrThailand;

    //Czy mieszkał/a Pan/Pani lub przebywał/a na terenach endemicznego występowania: malarii lub innych chorób tropikalnych.
    //Jeżeli tak, kiedy i jakie? ………………..

    private Boolean livedOrBeenInAreaEndemicMalariaOrOtherTropicalDiseases;
    private String whatEpidemicAndWhen;

    //Czy chorował Pan/Pani na malarię, inne chorób tropikalnych.
    //Jeżeli tak, kiedy i jakie? ………………..

    private Boolean hadMalariaOrOtherTropicalDiseases;
    private String whenSeekForMalariaOrTropicalDiseases;

    //Czy w ciągu ostatnich 28 dni przebywał Pan/Pani na terenach gdzie stwierdzono przypadki przeniesienia Wirusa Zachodniego Nilu na ludzi?

    private Boolean haveStayedInAreasWestNileVirusWasTransmittedToHumans;

    //Czy w ciągu ostatnich 6 miesięcy wykonywano u Pana/Pani: tatuaż, akupunkturę, depilację kosmetyczną, przekłucia uszu lub
    // innych części ciała, zabiegi kosmetyczne z naruszeniem powłok skórnych?
    //Jeżeli tak, kiedy? ………………….

    private Boolean hadTattoosAcupunctureCosmeticDepilationPiercing;

    //Czy w ciągu ostatnich 6 miesięcy lub od czasu ostatniego oddania krwi miał/a Pan/Pani
    //przypadkowy kontakt z krwią ludzką lub narzędziami zanieczyszczonymi krwią ludzką

    private Boolean haveContactWithBloodOrToolsContaminatedWithBlood;

    //Czy kiedykolwiek przechodził/a Pan/Pani żółtaczkę

    private Boolean hadJaundice;

    //Czy Pana/Pani partner życiowy lub seksualny w ciągu ostatnich 6 miesięcy przechodził żółtaczkę?

    private Boolean yourLifeOrSexualPartnerHadJaundice;

    //Czy w ciągu ostatnich 12 miesięcy miał/a Pan/Pani kontakt z zakaźnie chorym?

    private Boolean hadContactWithContagiouslyIllPersonIn12Months;

    //Czy przeczytał i zrozumiał Pan/Pani „Informację o chorobach zakaźnych dla krwiodawców

    private Boolean readInfoOnInfectiousDiseasesForBloodDonors;

    //Czy w ciągu ostatnich 6 miesięcy przebywał/a na Pan/Pani w areszcie lub więzieniu

    private Boolean haveBeenCustodyOrImprisonmentLast6Months;

    //Czy kiedykolwiek zalecono Panu/Pani rezygnację z oddawania krwi

    private Boolean haveBeenAdvisedNotToDonateBlood;

    //Czy wykonuje Pan/Pani niebezpieczną pracę (np. kierowca autobusu, nurek) lub ma niebezpieczne
    //hobby?

    private Boolean haveDangerousJobOrHobby;

    //Czy jest Pani obecnie w ciąży lub była w ciąży w ciągu ostatnich 12 miesięcy lub od czasu
    //ostatniej donacji krwi?
    //Jeżeli tak, proszę podać datę porodu ……………………………………….

    private Boolean pregnantOrHaveBeenPregnantLast12Months;
    private String dateOfLastPragnecy;

    //Czy Pani miesiączkuje? Jeżeli tak, to kiedy ostatnio?

    private Boolean areYouMenstruating;
    private String whenLastMenstruating;

    //Czy w latach 1965 – 1985 otrzymywała Pani zastrzyki hormonów w celu leczenia niepłodności

    private Boolean anyHormoneInjectionsBetween_1965_And_1985;

    //Wyrażam zgodę na zabieg:
    //– pobrania krwi pełnej
    //– pobrania osocza metodą plazmaferezy
    //– pobrania krwinek płytkowych metodą trombaferezy
    //– pobrania krwinek białych metodą leukaferezy

    private Boolean consentProcedureBloodDonation;

    //Jednocześnie oświadczam, że zostałem/am poinformowany/a o rodzaju zabiegu i jego częstotliwości oraz o tym,
    //że w każdej chwili mogę wycofać zgodę na oddanie krwi. Zostałem/am poinformowany/a o sposobie
    //przeprowadzenia zabiegu pobrania krwi i dających się przewidzieć następstwach dla mojego stanu zdrowia.
    //Oświadczam, że w zgodzie z moim sumieniem i posiadaną wiedzą podane wyżej informacje o przebytych
    //chorobach i obecnym stanie zdrowia są prawdziwe i dokładne.
    //Oświadczam, że :
    //– zapoznałam/em się z dostarczonymi materiałami informacyjnymi i zrozumiałam/em ich znaczenie,
    //– miałam/em możliwość wyjaśnienia wątpliwości,
    //– otrzymałam/em satysfakcjonujące odpowiedzi na wszystkie zadane pytania,
    //Rozumiem, że mają one na celu ochronę mojego zdrowia jako dawcy i zapewnienie bezpieczeństwa biorcy krwi.
    //Uważam, że moja krew nadaje się do celów leczniczych. Oświadczam, że otrzymałem/am w przystępnej formie informacje
    //na temat możliwości przetworzenia oddanego przeze mnie osocza w leki w przypadku niewykorzystania go do celów klinicznych.
    //W przypadku wystąpienia w ciągu 48 godzin od zakończenia donacji jakichkolwiek objawów chorobowych,
    //zobowiązuję się do telefonicznego powiadomienia lekarza, który zakwalifikował mnie do oddania krwi.
    // W razie otrzymania zawiadomienia o konieczności odbioru wyników badań zobowiązuję się do terminowego zgłoszenia się do centrum.
    // Jednocześnie przyjmuję do wiadomości, że jeżeli pomimo czterokrotnego zawiadomienia wyniki nie zostaną przeze mnie odebrane,
    // centrum nie ponosi odpowiedzialności za konsekwencje wynikłe z tego faktu.
    //Data ……………… Podpis krwiodawcy ………………….

    private Boolean agreement1;

    //Wyrażam zgodę na przechowywanie w Instytucie Hematologii i Transfuzjologii materiału służącego do izolacji DNA/RNA lub
    //izolowanego DNA/RNA po zakończeniu diagnostyki z zachowaniem tajemnicy danych oraz na wykorzystywanie mojego DNA/RNA
    //do badań naukowych mających na celu rozszerzenie wiedzy na temat podłoża molekularnego antygenów komórek krwi oraz
    // dotyczących czynników zakaźnych, z zachowaniemwarunków anonimowości.
    //Data …………….. Podpis krwiodawcy …………………..

    private Boolean agreement2;

    //Wyrażam zgodę, aby pobrana ode mnie krew i jej składniki zostały wydane za opłata do podmiotów leczniczych z przeznaczeniem do celów klinicznych,
    // zgodnie z art. 19.1 ustawy z dnia 22 sierpnia 1997 r. o publicznej służbie krwi (Dz. U. 2014 poz. 332 i Dz. U. 2016 poz. 823).
    //Data ……………… Podpis krwiodawcy ………………….

    private Boolean agreement3;

    //Wyrażam zgodę, aby osocze, uzyskane z pobranej ode mnie krwi pełnej lub pobrane ode mnie metodą aferezy, w przypadku niewykorzystania go do celów klinicznych,
    // zostało wydane za opłata do wytwórni farmaceutycznych, jako surowiec do wytwarzania leków.
    //Data ……………… Podpis krwiodawcy ………………….

    private Boolean agreement4;

    //Nie wyrażam zgody, aby osocze, uzyskane z pobranej ode mnie krwi pełnej lub pobrane ode mnie metoda aferezy,
    // w przypadku niewykorzystania go do celów klinicznych, zostało wydane za opłata do wytwórni farmaceutycznych,
    // jako surowiec do wytwarzania leków.
    //Data ……………… Podpis krwiodawcy ………………….

    private Boolean agreement5;

    //Uprzejmie prosimy, aby w przypadku zmiany miejsca zamieszkania (adresu), zawiadomić o tej zmianie Centrum Krwiodawstwa i Krwiolecznictwa w ……………………….
    //adres …………………………. telefon ……………………….
    //Data ……………………………………… Podpis krwiodawcy ……………………………………………….. ………….
    //Data ……………………………………… Podpis osoby sprawdzającej ……………………………………………….
}
