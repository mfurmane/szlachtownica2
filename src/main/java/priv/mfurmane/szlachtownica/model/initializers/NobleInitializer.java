package priv.mfurmane.szlachtownica.model.initializers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.engine.naming.ernizjum.ErnizjumPhonotactic;
import priv.mfurmane.szlachtownica.engine.naming.model.Phonotactic;
import priv.mfurmane.szlachtownica.engine.naming.nereneth.NerenethPhonotactic;
import priv.mfurmane.szlachtownica.model.FamilyType;
import priv.mfurmane.szlachtownica.model.Race;
import priv.mfurmane.szlachtownica.model.Sex;
import priv.mfurmane.szlachtownica.model.VassalFrom;
import priv.mfurmane.szlachtownica.model.config.ConfigurationFamily;
import priv.mfurmane.szlachtownica.model.config.ConfigurationPerson;
import priv.mfurmane.szlachtownica.model.main.ModelFamily;
import priv.mfurmane.szlachtownica.model.main.ModelPerson;
import priv.mfurmane.szlachtownica.model.simulation.SimulationFamily;
import priv.mfurmane.szlachtownica.model.simulation.SimulationPerson;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class NobleInitializer {
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

        SimulationFamily seldan = initializeSeldan(families);
        SimulationFamily sarael = initializeSarael(families);
        SimulationFamily lualent = initializeLualent(families);

        SimulationFamily tagar = initializeTagar(families);

        SimulationFamily holzer = initializeHolzer(families, List.of(new VassalFrom(preTagaraPlaceholder, 1183), new VassalFrom(tagar, 1194), new VassalFrom(null, 1497)));
        SimulationFamily bolinosek = initializeBolinosek(families, List.of(new VassalFrom(holzer, 1229)));
        SimulationFamily elfogrzmot = initializeElfogrzmot(families, List.of(new VassalFrom(holzer, 1198)));
        SimulationFamily sartori = initializeSartori(families, List.of(new VassalFrom(holzer, 1222)));

        SimulationFamily gillear = initializeGillear(families, List.of(new VassalFrom(preTagaraPlaceholder, 1151), new VassalFrom(tagar, 1194), new VassalFrom(null, 1497), new VassalFrom(holzer, 1507)));
        SimulationFamily mondenero = initializeMondenero(families, List.of(new VassalFrom(preTagaraPlaceholder, 1183), new VassalFrom(tagar, 1194), new VassalFrom(null, 1497), new VassalFrom(holzer, 1507)));
        SimulationFamily szybcioszek = initializeSzybcioszek(families, List.of(new VassalFrom(tagar, 1194), new VassalFrom(null, 1497), new VassalFrom(holzer, 1507)));

        SimulationFamily suelet = initializeSuelet(families, List.of(new VassalFrom(lualent, 836), new VassalFrom(preTagaraPlaceholder, 1188), new VassalFrom(tagar, 1194), new VassalFrom(null, 1497), new VassalFrom(holzer, 1507)));
        SimulationFamily davila = initializeDavila(families, List.of(new VassalFrom(suelet, 1188)));
        SimulationFamily korniszonek = initializeKorniszonek(families, List.of(new VassalFrom(suelet, 1293)));
        SimulationFamily meczybula = initializeMeczybula(families, List.of(new VassalFrom(suelet, 1195)));
        SimulationFamily siroen = initializeSiroen(families, List.of(new VassalFrom(lualent, 987), new VassalFrom(suelet, 1188)));
        SimulationFamily zielonaWisnia = initializeZielonaWisnia(families, List.of(new VassalFrom(suelet, 1499)));

        SimulationFamily raevill = initializeRaevill(families, List.of(new VassalFrom(preTagaraPlaceholder, 1187), new VassalFrom(tagar, 1194), new VassalFrom(null, 1497), new VassalFrom(holzer, 1507)));
        SimulationFamily barsaes = initializeBarsaes(families, List.of(new VassalFrom(raevill, 823)));
        SimulationFamily castellano = initializeCastellano(families, List.of(new VassalFrom(raevill, 1186)));
        SimulationFamily melearis = initializeMelearis(families, List.of(new VassalFrom(raevill, 1212)));
        SimulationFamily osmiorniczek = initializeOsmiorniczek(families, List.of(new VassalFrom(raevill, 1246)));

        SimulationFamily runeFist = initializeRuneFist(families, List.of(new VassalFrom(tagar, 1219), new VassalFrom(null, 1497), new VassalFrom(holzer, 1507)));
        SimulationFamily brugdenall = initializeBrugdenall(families, List.of(new VassalFrom(runeFist, 1125)));
        SimulationFamily smoliszek = initializeSmoliszek(families, List.of(new VassalFrom(runeFist, 1223)));
        SimulationFamily szubienicznik = initializeSzubienicznik(families, List.of(new VassalFrom(runeFist, 996)));
        SimulationFamily zakutyLeb = initializeZakutyLeb(families, List.of(new VassalFrom(runeFist, 1057)));

        SimulationFamily burczybrzuszek = initializeBurczybrzuszek(families, List.of(new VassalFrom(preTagaraPlaceholder, 1190), new VassalFrom(tagar, 1194), new VassalFrom(null, 1497), new VassalFrom(holzer, 1507)));
        SimulationFamily erteseas = initializeErteseas(families, List.of(new VassalFrom(preTagaraPlaceholder, 1187), new VassalFrom(burczybrzuszek, 1190)));
        SimulationFamily gaumont = initializeGaumont(families, List.of(new VassalFrom(burczybrzuszek, 1221)));
        SimulationFamily grabendell = initializeGrabendell(families, List.of(new VassalFrom(burczybrzuszek, 1262)));
        SimulationFamily moczydupa = initializeMoczydupa(families, List.of(new VassalFrom(burczybrzuszek, 1235)));

        SimulationFamily sarrambert = initializeSarrambert(families, List.of(new VassalFrom(preTagaraPlaceholder, 1186), new VassalFrom(tagar, 1194), new VassalFrom(null, 1497), new VassalFrom(holzer, 1507)));
        SimulationFamily bloodyHead = initializeBloodyHead(families, List.of(new VassalFrom(sarrambert, 1186)));
        SimulationFamily carranza = initializeCarranza(families, List.of(new VassalFrom(sarrambert, 1199)));
        SimulationFamily palniczek = initializePalniczek(families, List.of(new VassalFrom(sarrambert, 1507)));

        SimulationFamily vealer = initializeVealer(families, List.of(new VassalFrom(tagar, 1194), new VassalFrom(null, 1497), new VassalFrom(holzer, 1507)));
        SimulationFamily cnyMilorzab = initializeCnyMilorzab(families, List.of(new VassalFrom(vealer, 1507)));
        SimulationFamily dergonall = initializeDergonall(families, List.of(new VassalFrom(vealer, 1499)));
        SimulationFamily iloserin = initializeIloserin(families, List.of(new VassalFrom(vealer, 983)));
        SimulationFamily lionear = initializeLionear(families, List.of(new VassalFrom(sarael, 789), new VassalFrom(vealer, 1194)));
        SimulationFamily navarra = initializeNavarra(families, List.of(new VassalFrom(vealer, 1195)));
        SimulationFamily zauszek = initializeZauszek(families, List.of(new VassalFrom(vealer, 1195)));

        SimulationFamily zakrzepek = initializeZakrzepek(families, List.of(new VassalFrom(preTagaraPlaceholder, 1184), new VassalFrom(tagar, 1194), new VassalFrom(null, 1497), new VassalFrom(holzer, 1507)));
        SimulationFamily bonnet = initializeBonnet(families, List.of(new VassalFrom(zakrzepek, 1191)));
        SimulationFamily sergevall = initializeSergevall(families, List.of(new VassalFrom(zakrzepek, 1507)));

        SimulationFamily astager = initializeAstager(families, List.of(new VassalFrom(tagar, 1195), new VassalFrom(aldaharDynasty, 1497), new VassalFrom(holzer, 1628)));
        SimulationFamily hertezza = initializeHertezza(families, List.of(new VassalFrom(astager, 1504)));
        SimulationFamily nalesniczek = initializeNalesniczek(families, List.of(new VassalFrom(astager, 1292)));

        SimulationFamily sarrazin = initializeSarrazin(families, List.of(new VassalFrom(tagar, 1195), new VassalFrom(aldaharDynasty, 1497), new VassalFrom(holzer, 1628)));
        SimulationFamily gastrogall = initializeGastrogall(families, List.of(new VassalFrom(sarrazin, 1263)));
        SimulationFamily molla = initializeMolla(families, List.of(new VassalFrom(sarrazin, 1499)));
        SimulationFamily pierniczek = initializePierniczek(families, List.of(new VassalFrom(sarrazin, 1306)));

        SimulationFamily morten = initializeMorten(families, List.of(new VassalFrom(preTagaraPlaceholder, 1186), new VassalFrom(tagar, 1194), new VassalFrom(null, 1497), new VassalFrom(holzer, 1507)));
        SimulationFamily brueldel = initializeBrueldell(families, List.of(new VassalFrom(morten, 1198)));
        SimulationFamily paquin = initializePaquin(families, List.of(new VassalFrom(morten, 1507)));



        List<SimulationPerson> persons = new ArrayList<>();
        SimulationPerson tagara = initializeTagara(persons, tagar);

        persons.forEach(person -> {
            System.out.println("# " + person.getModel().getName());
        });

    }

    private SimulationFamily initializeSeldan(List<SimulationFamily> families) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(1)
                .setRace(Race.HUMAN)
                .setCreationYear(1154)
                .setExtinctionYear(1186)
                .setType(FamilyType.MUCHO_IMPORTANTE)
                .setStrength(8)
                .setSurname("Seldan")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeSarael(List<SimulationFamily> families) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(2)
                .setRace(Race.ELF)
                .setCreationYear(696)
                .setExtinctionYear(1193)
                .setType(FamilyType.MUCHO_IMPORTANTE)
                .setStrength(9)
                .setSurname("Sarael")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeLualent(List<SimulationFamily> families) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(3)
                .setRace(Race.ELF)
                .setCreationYear(812)
                .setExtinctionYear(1187)
                .setType(FamilyType.MUCHO_IMPORTANTE)
                .setStrength(9)
                .setSurname("Lualent")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeRaevill(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(4)
                .setRace(Race.ELF)
                .setCreationYear(687)
                .setType(FamilyType.MUCHO_IMPORTANTE)
                .setStrength(8)
                .setSurname("Raevill")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeVealer(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(5)
                .setRace(Race.ELF)
                .setCreationYear(745)
                .setType(FamilyType.MUCHO_IMPORTANTE)
                .setStrength(7)
                .setSurname("Vealer")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeBarsaes(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(6)
                .setRace(Race.ELF)
                .setCreationYear(823)
                .setType(FamilyType.PREDEFINED)
                .setStrength(3)
                .setSurname("Barsaes")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeSuelet(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(7)
                .setRace(Race.ELF)
                .setCreationYear(836)
                .setType(FamilyType.MUCHO_IMPORTANTE) //TODO na pewno?
                .setStrength(8)
                .setSurname("Suelet")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeRuneFist(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(8)
                .setRace(Race.DWARF)
                .setCreationYear(984)
                .setType(FamilyType.MUCHO_IMPORTANTE)
                .setStrength(9)
                .setSurname("Runiczna Piącha")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeGillear(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(9)
                .setRace(Race.ELF)
                .setCreationYear(986)
                .setType(FamilyType.MUCHO_IMPORTANTE)
                .setStrength(4)
                .setSurname("Gillear")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeSzubienicznik(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(10)
                .setRace(Race.DWARF)
                .setCreationYear(996)
                .setType(FamilyType.PREDEFINED)
                .setStrength(5)
                .setSurname("Szubienicznik")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeErteseas(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(11)
                .setRace(Race.ELF)
                .setCreationYear(1026)
                .setType(FamilyType.MUCHO_IMPORTANTE)
                .setStrength(3)
                .setSurname("Erteseas")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeBloodyHead(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(12)
                .setRace(Race.DWARF)
                .setCreationYear(1114)
                .setType(FamilyType.PREDEFINED)
                .setStrength(3)
                .setSurname("Krwawy Czerep")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeHolzer(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(13)
                .setRace(Race.HUMAN)
                .setCreationYear(1183)
                .setType(FamilyType.MUCHO_IMPORTANTE)
                .setStrength(9)
                .setSurname("Holzer")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeZakrzepek(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(14)
                .setRace(Race.HALFLING)
                .setCreationYear(1183)
                .setType(FamilyType.MUCHO_IMPORTANTE)
                .setStrength(7)
                .setSurname("Zakrzepek")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeTagar(List<SimulationFamily> families) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(15)
                .setRace(Race.HUMAN)
                .setCreationYear(1194)
                .setExtinctionYear(1497)
                .setType(FamilyType.MAIN_DYNASTY)
                .setStrength(10)
                .setSurname("Tagar")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeMeczybula(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(16)
                .setRace(Race.DWARF)
                .setCreationYear(1195)
                .setType(FamilyType.MUCHO_IMPORTANTE)
                .setStrength(2)
                .setSurname("Męczybuła")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeAstager(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(17)
                .setRace(Race.HUMAN)
                .setCreationYear(1195)
                .setType(FamilyType.MUCHO_IMPORTANTE)
                .setStrength(7)
                .setSurname("Astager")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeSarrazin(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(18)
                .setRace(Race.HUMAN)
                .setCreationYear(1195)
                .setType(FamilyType.MUCHO_IMPORTANTE)
                .setStrength(7)
                .setSurname("Sarrazin")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeLionear(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(19)
                .setRace(Race.ELF)
                .setCreationYear(789)
                .setType(FamilyType.PREDEFINED)
                .setStrength(4)
                .setSurname("Lionear")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeIloserin(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(20)
                .setRace(Race.ELF)
                .setCreationYear(983)
                .setType(FamilyType.PREDEFINED)
                .setStrength(3)
                .setSurname("Iloserin")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeSiroen(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(21)
                .setRace(Race.ELF)
                .setCreationYear(987)
                .setType(FamilyType.PREDEFINED)
                .setStrength(1)
                .setSurname("Siroen")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeZakutyLeb(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(22)
                .setRace(Race.DWARF)
                .setCreationYear(1057)
                .setType(FamilyType.PREDEFINED)
                .setStrength(3)
                .setSurname("Zakuty Łeb")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeBrugdenall(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(23)
                .setRace(Race.GNOME)
                .setCreationYear(1125)
                .setType(FamilyType.PREDEFINED)
                .setStrength(4)
                .setSurname("Brug'denall")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeMondenero(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(24)
                .setRace(Race.HUMAN)
                .setCreationYear(1151)
                .setType(FamilyType.PREDEFINED)
                .setStrength(3)
                .setSurname("Mondenero")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeSzybcioszek(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(25)
                .setRace(Race.HALFLING)
                .setCreationYear(1176)
                .setType(FamilyType.PREDEFINED)
                .setStrength(5)
                .setSurname("Szybcioszek")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeMorten(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(26)
                .setRace(Race.HUMAN)
                .setCreationYear(1184)
                .setType(FamilyType.PREDEFINED)
                .setStrength(7)
                .setSurname("Morten")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeSarrambert(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(27)
                .setRace(Race.HUMAN)
                .setCreationYear(1185)
                .setType(FamilyType.PREDEFINED)
                .setStrength(8)
                .setSurname("Sarrambert")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeCastellano(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(28)
                .setRace(Race.HUMAN)
                .setCreationYear(1186)
                .setType(FamilyType.PREDEFINED)
                .setStrength(4)
                .setSurname("Castellano")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeBurczybrzuszek(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(29)
                .setRace(Race.HALFLING)
                .setCreationYear(1187)
                .setType(FamilyType.PREDEFINED)
                .setStrength(9)
                .setSurname("Burczybrzuszek")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeDavila(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(30)
                .setRace(Race.HUMAN)
                .setCreationYear(1188)
                .setType(FamilyType.PREDEFINED)
                .setStrength(4)
                .setSurname("Davila")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeBonnet(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(31)
                .setRace(Race.HUMAN)
                .setCreationYear(1191)
                .setType(FamilyType.PREDEFINED)
                .setStrength(2)
                .setSurname("Bonnet")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeZauszek(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(32)
                .setRace(Race.HALFLING)
                .setCreationYear(1195)
                .setType(FamilyType.PREDEFINED)
                .setStrength(3)
                .setSurname("Zauszek")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeNavarra(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(33)
                .setRace(Race.HUMAN)
                .setCreationYear(1195)
                .setType(FamilyType.PREDEFINED)
                .setStrength(2)
                .setSurname("Navarra")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeElfogrzmot(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(34)
                .setRace(Race.DWARF)
                .setCreationYear(1198)
                .setType(FamilyType.PREDEFINED)
                .setStrength(3)
                .setSurname("Elfogrzmot")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeSmoliszek(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(35)
                .setRace(Race.HALFLING)
                .setCreationYear(1198)
                .setType(FamilyType.PREDEFINED)
                .setStrength(4)
                .setSurname("Smoliszek")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeBrueldell(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(36)
                .setRace(Race.GNOME)
                .setCreationYear(1198)
                .setType(FamilyType.PREDEFINED)
                .setStrength(4)
                .setSurname("Bru'eldell")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeCarranza(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(37)
                .setRace(Race.HUMAN)
                .setCreationYear(1199)
                .setType(FamilyType.PREDEFINED)
                .setStrength(5)
                .setSurname("Carranza")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeMelearis(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(38)
                .setRace(Race.ELF)
                .setCreationYear(1212)
                .setType(FamilyType.PREDEFINED)
                .setStrength(4)
                .setSurname("Melearis")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeGaumont(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(39)
                .setRace(Race.HUMAN)
                .setCreationYear(1221)
                .setType(FamilyType.PREDEFINED)
                .setStrength(5)
                .setSurname("Gaumont")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeSartori(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(40)
                .setRace(Race.HUMAN)
                .setCreationYear(1222)
                .setType(FamilyType.PREDEFINED)
                .setStrength(5)
                .setSurname("Sartori")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeBolinosek(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(41)
                .setRace(Race.HALFLING)
                .setCreationYear(1229)
                .setType(FamilyType.PREDEFINED)
                .setStrength(4)
                .setSurname("Bolinosek")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeMoczydupa(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(42)
                .setRace(Race.DWARF)
                .setCreationYear(1235)
                .setType(FamilyType.PREDEFINED)
                .setStrength(3)
                .setSurname("Moczydupa")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeOsmiorniczek(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(43)
                .setRace(Race.HALFLING)
                .setCreationYear(1246)
                .setType(FamilyType.PREDEFINED)
                .setStrength(2)
                .setSurname("Ośmiorniczek")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeGrabendell(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(44)
                .setRace(Race.GNOME)
                .setCreationYear(1262)
                .setType(FamilyType.PREDEFINED)
                .setStrength(4)
                .setSurname("Gra'bendell")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeGastrogall(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(45)
                .setRace(Race.GNOME)
                .setCreationYear(1263)
                .setType(FamilyType.PREDEFINED)
                .setStrength(3)
                .setSurname("Gas'trogall")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeNalesniczek(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(46)
                .setRace(Race.HALFLING)
                .setCreationYear(1292)
                .setType(FamilyType.PREDEFINED)
                .setStrength(3)
                .setSurname("Naleśniczek")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeKorniszonek(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(47)
                .setRace(Race.HALFLING)
                .setCreationYear(1293)
                .setType(FamilyType.PREDEFINED)
                .setStrength(3)
                .setSurname("Korniszonek")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializePierniczek(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(48)
                .setRace(Race.HALFLING)
                .setCreationYear(1306)
                .setType(FamilyType.PREDEFINED)
                .setStrength(1)
                .setSurname("Pierniczek")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeSergevall(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(49)
                .setRace(Race.GNOME)
                .setCreationYear(1498)
                .setType(FamilyType.PREDEFINED)
                .setStrength(3)
                .setSurname("Ser'gevall")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeZielonaWisnia(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(50)
                .setRace(Race.PEURA)
                .setCreationYear(1499)
                .setType(FamilyType.PREDEFINED)
                .setStrength(4)
                .setSurname("Zielona Wiśnia")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializePaquin(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(51)
                .setRace(Race.HUMAN)
                .setCreationYear(1499)
                .setType(FamilyType.PREDEFINED)
                .setStrength(3)
                .setSurname("Paquin")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeMolla(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(52)
                .setRace(Race.HUMAN)
                .setCreationYear(1499)
                .setType(FamilyType.PREDEFINED)
                .setStrength(2)
                .setSurname("Molla")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeDergonall(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(53)
                .setRace(Race.GNOME)
                .setCreationYear(1499)
                .setType(FamilyType.PREDEFINED)
                .setStrength(1)
                .setSurname("Der'gonall")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializePalniczek(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(54)
                .setRace(Race.HALFLING)
                .setCreationYear(1501)
                .setType(FamilyType.PREDEFINED)
                .setStrength(2)
                .setSurname("Palniczek")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeHertezza(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(55)
                .setRace(Race.HUMAN)
                .setCreationYear(1504)
                .setType(FamilyType.PREDEFINED)
                .setStrength(2)
                .setSurname("Hertezza")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    private SimulationFamily initializeCnyMilorzab(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(56)
                .setRace(Race.PEURA)
                .setCreationYear(1505)
                .setType(FamilyType.PREDEFINED)
                .setStrength(1)
                .setSurname("Cny Miłorząb")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    public SimulationPerson initializeNamedPerson(String name, String middlename, int bornYear, List<SimulationPerson> persons) {
        SimulationPerson person = new SimulationPerson();
        ConfigurationPerson conf = ConfigurationPerson.builder()
                .build();
        ModelPerson model = ModelPerson.builder()
                .build();
        return mergeAndRegisterPerson(person, conf, model, persons);
    }

    public static void main(String[] args) {

        String[] names = {"Lionear",
                "Iloserin",
                "Siroen",
                "Zakuty Łeb",
                "Brug'denall",
                "Mondenero",
                "Szybcioszek",
                "Morten",
                "Sarrambert",
                "Castellano",
                "Burczybrzuszek",
                "Davila",
                "Bonnet",
                "Zauszek",
                "Navarra",
                "Elfogrzmot",
                "Smoliszek",
                "Bru'eldell",
                "Carranza",
                "Melearis",
                "Gaumont",
                "Sartori",
                "Bolinosek",
                "Moczydupa",
                "Ośmiorniczek",
                "Gra'bendell",
                "Gas'trogall",
                "Naleśniczek",
                "Korniszonek",
                "Pierniczek",
                "Ser'gevall",
                "Zielona Wiśnia",
                "Paquin",
                "Molla",
                "Der'gonall",
                "Palniczek",
                "Hertezza",
                "Cny Miłorząb"};
        String[] races = {"ELF",
                "ELF",
                "ELF",
                "DWARF",
                "GNOME",
                "HUMAN",
                "HALFLING",
                "HUMAN",
                "HUMAN",
                "HUMAN",
                "HALFLING",
                "HUMAN",
                "HUMAN",
                "HALFLING",
                "HUMAN",
                "DWARF",
                "HALFLING",
                "GNOME",
                "HUMAN",
                "ELF",
                "HUMAN",
                "HUMAN",
                "HALFLING",
                "DWARF",
                "HALFLING",
                "GNOME",
                "GNOME",
                "HALFLING",
                "HALFLING",
                "HALFLING",
                "GNOME",
                "PEURA",
                "HUMAN",
                "HUMAN",
                "GNOME",
                "HALFLING",
                "HUMAN",
                "PEURA"};
        String[] years = {"789",
                "983",
                "987",
                "1057",
                "1125",
                "1151",
                "1176",
                "1184",
                "1185",
                "1186",
                "1187",
                "1188",
                "1191",
                "1195",
                "1195",
                "1198",
                "1198",
                "1198",
                "1199",
                "1212",
                "1221",
                "1222",
                "1229",
                "1235",
                "1246",
                "1262",
                "1263",
                "1292",
                "1293",
                "1306",
                "1498",
                "1499",
                "1499",
                "1499",
                "1499",
                "1501",
                "1504",
                "1505"};
        int id = 19;
        String method = "    private SimulationFamily initialize%s(List<SimulationFamily> families) {\n" +
                "        SimulationFamily family = new SimulationFamily();\n" +
                "        ConfigurationFamily conf = ConfigurationFamily.builder()\n" +
                "\n" +
                "                .build();\n" +
                "        ModelFamily model = ModelFamily.builder()\n" +
                "                .setId(%s)\n" +
                "                .setRace(Race.%s)\n" +
                "                .setCreationYear(%s)\n" +
                "                .setType(FamilyType.PREDEFINED)\n" +
                "                .setStrength(9)\n" +
                "                .setSurname(\"%s\")\n" +
                "                .build();\n" +
                "        families.add(family);\n" +
                "        return mergeAndRegisterFamily(family, conf, model, families);\n" +
                "    }\n";
        for (int i = 0; i < names.length; i++) {
            System.out.printf((method) + "%n", names[i], (id + i), races[i], years[i], names[i]);
        }


    }

    private SimulationPerson initializeTagara(List<SimulationPerson> persons, SimulationFamily tagar) {
        SimulationPerson person = new SimulationPerson();
        ConfigurationPerson conf = ConfigurationPerson.builder()
                //AGE PHASES
                .setHornyAge(16)
                .setStableAge(20)
                .setMarriageAge(18)
                .setMourningTime(16)
                //INTERPERSONAL
                .setAmorous(0.1)
                .setAttachment(0.9)
                .setDivorcable(0.01)
                .setHomo(0.1)
                .setHorny(0.7)
                .setImpulsive(0.1)
                .setInfluence(1.0)
                .setInterracial(0.5)
                .setJealous(0.4)
                .setLoyal(0.96)
                .setManipulative(0.3)
                .setPoliamoric(0.05)
                .setRevengous(0.1)
                .setVisibility(1.0)
                //PERSONAL
                .setAmbition(0.95)
                .setBeauty(0.6)
                .setCharisma(0.9)
                .setCunning(0.7)
                .setDiplomatic(0.9)
                .setHonor(0.9)
                .setIntellect(0.9)
                .setMoral(0.95)
                .setParanoid(0.4)
                .setProud(0.5)
                .setTemperament(0.3)
                //PHYSICAL
                .setFertility(0.7)
                .setHealth(0.7)
                .build();
        ModelPerson model = ModelPerson.builder()
                .setId(1L)
                .setName("Tagara")
                .setRace(Race.HUMAN)
                .setBorn(LocalDate.parse("1168-03-17"))
                .setDied(LocalDate.parse("1226-09-02"))
                .setMiddleName("I Budownicza")
                .setFamily(tagar.getModel())
                .setSex(Sex.FEMALE)
                .build();
        return mergeAndRegisterPerson(person, conf, model, persons);
    }

    private static SimulationFamily mergeAndRegisterFamily(SimulationFamily family, ConfigurationFamily conf, ModelFamily model, List<SimulationFamily> families) {
        family.setConf(conf);
        family.setModel(model);
        if (MainEngine.getInstance() != null) {
            MainEngine.getInstance().getFamilyRegistry().register(family);
        }
        families.add(family);
        return family;
    }

    private static SimulationPerson mergeAndRegisterPerson(SimulationPerson person, ConfigurationPerson conf, ModelPerson model, List<SimulationPerson> persons) {
        person.setConf(conf);
        person.setModel(model);
        if (MainEngine.getInstance() != null) {
            MainEngine.getInstance().getPersonRegistry().register(person);
        }
        persons.add(person);
        return person;
    }
}
