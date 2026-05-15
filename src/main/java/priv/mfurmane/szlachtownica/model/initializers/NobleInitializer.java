package priv.mfurmane.szlachtownica.model.initializers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.engine.naming.ernizjum.ErnizjumPhonotactic;
import priv.mfurmane.szlachtownica.engine.naming.model.Phonotactic;
import priv.mfurmane.szlachtownica.engine.naming.nereneth.NerenethPhonotactic;
import priv.mfurmane.szlachtownica.model.VassalFrom;
import priv.mfurmane.szlachtownica.model.config.ConfigurationPerson;
import priv.mfurmane.szlachtownica.model.main.ModelPerson;
import priv.mfurmane.szlachtownica.model.simulation.SimulationFamily;
import priv.mfurmane.szlachtownica.model.simulation.SimulationPerson;

import java.util.ArrayList;
import java.util.List;

@Component
public class NobleInitializer {
    private final FamiliesConfiguration familiesConfiguration = new FamiliesConfiguration();
    private final PersonsConfiguration personsConfiguration = new PersonsConfiguration();
    private MainEngine engine;

    private final Phonotactic ernizjumPhonotactic = new ErnizjumPhonotactic();
    private final Phonotactic nerenethPhonotactic = new NerenethPhonotactic();

    private final ObjectMapper mapper = new ObjectMapper();

    public NobleInitializer() {
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    public void setEngine(MainEngine mainEngine) {
        this.engine = mainEngine;
    }

    public void initializeNobles() {
        List<SimulationFamily> families = new ArrayList<>();

        SimulationFamily preTagaraPlaceholder = new SimulationFamily();
        SimulationFamily aldaharDynasty = new SimulationFamily();

        SimulationFamily seldan = familiesConfiguration.initializeSeldan(families);
        SimulationFamily sarael = familiesConfiguration.initializeSarael(families);
        SimulationFamily lualent = familiesConfiguration.initializeLualent(families);

        SimulationFamily tagar = familiesConfiguration.initializeTagar(families);

        SimulationFamily holzer = familiesConfiguration.initializeHolzer(families, List.of(new VassalFrom(preTagaraPlaceholder, 1183), new VassalFrom(tagar, 1194), new VassalFrom(null, 1497)));
        SimulationFamily bolinosek = familiesConfiguration.initializeBolinosek(families, List.of(new VassalFrom(holzer, 1229)));
        SimulationFamily elfogrzmot = familiesConfiguration.initializeElfogrzmot(families, List.of(new VassalFrom(holzer, 1198)));
        SimulationFamily sartori = familiesConfiguration.initializeSartori(families, List.of(new VassalFrom(holzer, 1222)));

        SimulationFamily gillear = familiesConfiguration.initializeGillear(families, List.of(new VassalFrom(preTagaraPlaceholder, 1151), new VassalFrom(tagar, 1194), new VassalFrom(null, 1497), new VassalFrom(holzer, 1507)));
        SimulationFamily mondenero = familiesConfiguration.initializeMondenero(families, List.of(new VassalFrom(preTagaraPlaceholder, 1183), new VassalFrom(tagar, 1194), new VassalFrom(null, 1497), new VassalFrom(holzer, 1507)));
        SimulationFamily szybcioszek = familiesConfiguration.initializeSzybcioszek(families, List.of(new VassalFrom(tagar, 1194), new VassalFrom(null, 1497), new VassalFrom(holzer, 1507)));

        SimulationFamily suelet = familiesConfiguration.initializeSuelet(families, List.of(new VassalFrom(lualent, 836), new VassalFrom(preTagaraPlaceholder, 1188), new VassalFrom(tagar, 1194), new VassalFrom(null, 1497), new VassalFrom(holzer, 1507)));
        SimulationFamily davila = familiesConfiguration.initializeDavila(families, List.of(new VassalFrom(suelet, 1188)));
        SimulationFamily korniszonek = familiesConfiguration.initializeKorniszonek(families, List.of(new VassalFrom(suelet, 1293)));
        SimulationFamily meczybula = familiesConfiguration.initializeMeczybula(families, List.of(new VassalFrom(suelet, 1195)));
        SimulationFamily siroen = familiesConfiguration.initializeSiroen(families, List.of(new VassalFrom(lualent, 987), new VassalFrom(suelet, 1188)));
        SimulationFamily zielonaWisnia = familiesConfiguration.initializeZielonaWisnia(families, List.of(new VassalFrom(suelet, 1499)));

        SimulationFamily raevill = familiesConfiguration.initializeRaevill(families, List.of(new VassalFrom(preTagaraPlaceholder, 1187), new VassalFrom(tagar, 1194), new VassalFrom(null, 1497), new VassalFrom(holzer, 1507)));
        SimulationFamily barsaes = familiesConfiguration.initializeBarsaes(families, List.of(new VassalFrom(raevill, 823)));
        SimulationFamily castellano = familiesConfiguration.initializeCastellano(families, List.of(new VassalFrom(raevill, 1186)));
        SimulationFamily melearis = familiesConfiguration.initializeMelearis(families, List.of(new VassalFrom(raevill, 1212)));
        SimulationFamily osmiorniczek = familiesConfiguration.initializeOsmiorniczek(families, List.of(new VassalFrom(raevill, 1246)));

        SimulationFamily runeFist = familiesConfiguration.initializeRuneFist(families, List.of(new VassalFrom(tagar, 1219), new VassalFrom(null, 1497), new VassalFrom(holzer, 1507)));
        SimulationFamily brugdenall = familiesConfiguration.initializeBrugdenall(families, List.of(new VassalFrom(runeFist, 1125)));
        SimulationFamily smoliszek = familiesConfiguration.initializeSmoliszek(families, List.of(new VassalFrom(runeFist, 1223)));
        SimulationFamily szubienicznik = familiesConfiguration.initializeSzubienicznik(families, List.of(new VassalFrom(runeFist, 996)));
        SimulationFamily zakutyLeb = familiesConfiguration.initializeZakutyLeb(families, List.of(new VassalFrom(runeFist, 1057)));

        SimulationFamily burczybrzuszek = familiesConfiguration.initializeBurczybrzuszek(families, List.of(new VassalFrom(preTagaraPlaceholder, 1190), new VassalFrom(tagar, 1194), new VassalFrom(null, 1497), new VassalFrom(holzer, 1507)));
        SimulationFamily erteseas = familiesConfiguration.initializeErteseas(families, List.of(new VassalFrom(preTagaraPlaceholder, 1187), new VassalFrom(burczybrzuszek, 1190)));
        SimulationFamily gaumont = familiesConfiguration.initializeGaumont(families, List.of(new VassalFrom(burczybrzuszek, 1221)));
        SimulationFamily grabendell = familiesConfiguration.initializeGrabendell(families, List.of(new VassalFrom(burczybrzuszek, 1262)));
        SimulationFamily moczydupa = familiesConfiguration.initializeMoczydupa(families, List.of(new VassalFrom(burczybrzuszek, 1235)));

        SimulationFamily sarrambert = familiesConfiguration.initializeSarrambert(families, List.of(new VassalFrom(preTagaraPlaceholder, 1186), new VassalFrom(tagar, 1194), new VassalFrom(null, 1497), new VassalFrom(holzer, 1507)));
        SimulationFamily bloodyHead = familiesConfiguration.initializeBloodyHead(families, List.of(new VassalFrom(sarrambert, 1186)));
        SimulationFamily carranza = familiesConfiguration.initializeCarranza(families, List.of(new VassalFrom(sarrambert, 1199)));
        SimulationFamily palniczek = familiesConfiguration.initializePalniczek(families, List.of(new VassalFrom(sarrambert, 1507)));

        SimulationFamily vealer = familiesConfiguration.initializeVealer(families, List.of(new VassalFrom(tagar, 1194), new VassalFrom(null, 1497), new VassalFrom(holzer, 1507)));
        SimulationFamily cnyMilorzab = familiesConfiguration.initializeCnyMilorzab(families, List.of(new VassalFrom(vealer, 1507)));
        SimulationFamily dergonall = familiesConfiguration.initializeDergonall(families, List.of(new VassalFrom(vealer, 1499)));
        SimulationFamily iloserin = familiesConfiguration.initializeIloserin(families, List.of(new VassalFrom(vealer, 983)));
        SimulationFamily lionear = familiesConfiguration.initializeLionear(families, List.of(new VassalFrom(sarael, 789), new VassalFrom(vealer, 1194)));
        SimulationFamily navarra = familiesConfiguration.initializeNavarra(families, List.of(new VassalFrom(vealer, 1195)));
        SimulationFamily zauszek = familiesConfiguration.initializeZauszek(families, List.of(new VassalFrom(vealer, 1195)));

        SimulationFamily zakrzepek = familiesConfiguration.initializeZakrzepek(families, List.of(new VassalFrom(preTagaraPlaceholder, 1184), new VassalFrom(tagar, 1194), new VassalFrom(null, 1497), new VassalFrom(holzer, 1507)));
        SimulationFamily bonnet = familiesConfiguration.initializeBonnet(families, List.of(new VassalFrom(zakrzepek, 1191)));
        SimulationFamily sergevall = familiesConfiguration.initializeSergevall(families, List.of(new VassalFrom(zakrzepek, 1507)));

        SimulationFamily astager = familiesConfiguration.initializeAstager(families, List.of(new VassalFrom(tagar, 1195), new VassalFrom(aldaharDynasty, 1497), new VassalFrom(holzer, 1628)));
        SimulationFamily hertezza = familiesConfiguration.initializeHertezza(families, List.of(new VassalFrom(astager, 1504)));
        SimulationFamily nalesniczek = familiesConfiguration.initializeNalesniczek(families, List.of(new VassalFrom(astager, 1292)));

        SimulationFamily sarrazin = familiesConfiguration.initializeSarrazin(families, List.of(new VassalFrom(tagar, 1195), new VassalFrom(aldaharDynasty, 1497), new VassalFrom(holzer, 1628)));
        SimulationFamily gastrogall = familiesConfiguration.initializeGastrogall(families, List.of(new VassalFrom(sarrazin, 1263)));
        SimulationFamily molla = familiesConfiguration.initializeMolla(families, List.of(new VassalFrom(sarrazin, 1499)));
        SimulationFamily pierniczek = familiesConfiguration.initializePierniczek(families, List.of(new VassalFrom(sarrazin, 1306)));

        SimulationFamily morten = familiesConfiguration.initializeMorten(families, List.of(new VassalFrom(preTagaraPlaceholder, 1186), new VassalFrom(tagar, 1194), new VassalFrom(null, 1497), new VassalFrom(holzer, 1507)));
        SimulationFamily brueldel = familiesConfiguration.initializeBrueldell(families, List.of(new VassalFrom(morten, 1198)));
        SimulationFamily paquin = familiesConfiguration.initializePaquin(families, List.of(new VassalFrom(morten, 1507)));



        List<SimulationPerson> persons = new ArrayList<>();
        SimulationPerson riana = personsConfiguration.initializeRiana(persons, preTagaraPlaceholder);
        SimulationPerson urg = personsConfiguration.initializeUrg(persons, preTagaraPlaceholder);
        SimulationPerson merinaI = personsConfiguration.initializeMerinaI(persons, preTagaraPlaceholder);
        SimulationPerson orsa = personsConfiguration.initializeOrsa(persons, preTagaraPlaceholder);
        SimulationPerson fallonI = personsConfiguration.initializeFallonI(persons, preTagaraPlaceholder);
        SimulationPerson merinaII = personsConfiguration.initializeMerinaII(persons, preTagaraPlaceholder);

        SimulationPerson tagara = personsConfiguration.initializeTagara(persons, tagar);
        SimulationPerson erwinI = personsConfiguration.initializeErwinI(persons, tagar);
        SimulationPerson eolI = personsConfiguration.initializeEolI(persons, tagar);
        SimulationPerson merinaIII = personsConfiguration.initializeMerinaIII(persons, tagar);
        SimulationPerson erwinII = personsConfiguration.initializeErwinII(persons, tagar);
        SimulationPerson eolII = personsConfiguration.initializeEolII(persons, tagar);
        SimulationPerson ranalI = personsConfiguration.initializeRanalI(persons, tagar);
        SimulationPerson erwinIII = personsConfiguration.initializeErwinIII(persons, tagar);
        SimulationPerson roelekI = personsConfiguration.initializeRoelekI(persons, tagar);
        SimulationPerson aleraI = personsConfiguration.initializeAleraI(persons, tagar);
        SimulationPerson garvonI = personsConfiguration.initializeGarvonI(persons, tagar);

        SimulationPerson garvonII = personsConfiguration.initializeGarvonII(persons, holzer);
        SimulationPerson osaraI = personsConfiguration.initializeOsaraI(persons, holzer);
        SimulationPerson lasteraI = personsConfiguration.initializeLasteraI(persons, holzer);
        SimulationPerson garvonIII = personsConfiguration.initializeGarvonIII(persons, holzer);
        SimulationPerson galinaI = personsConfiguration.initializeGalinaI(persons, holzer);
        SimulationPerson ranalII = personsConfiguration.initializeRanalII(persons, holzer);
        SimulationPerson mereinaI = personsConfiguration.initializeMereinaI(persons, holzer);
        SimulationPerson aleraII = personsConfiguration.initializeAleraII(persons, holzer);
        SimulationPerson lasteraII = personsConfiguration.initializeLasteraII(persons, holzer);
        SimulationPerson fallonII = personsConfiguration.initializeFallonII(persons, holzer);

        persons.forEach(person -> {
            System.out.println("# " + person.getModel().getName());
        });

    }

    public static void main(String[] args) {

    }
}
