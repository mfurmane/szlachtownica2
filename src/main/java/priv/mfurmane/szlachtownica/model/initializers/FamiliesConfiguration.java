package priv.mfurmane.szlachtownica.model.initializers;

import priv.mfurmane.szlachtownica.engine.MainEngine;
import priv.mfurmane.szlachtownica.model.FamilyType;
import priv.mfurmane.szlachtownica.model.Race;
import priv.mfurmane.szlachtownica.model.VassalFrom;
import priv.mfurmane.szlachtownica.model.config.ConfigurationFamily;
import priv.mfurmane.szlachtownica.model.main.ModelFamily;
import priv.mfurmane.szlachtownica.model.simulation.SimulationFamily;

import java.util.List;

public class FamiliesConfiguration {
    public FamiliesConfiguration() {
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

    SimulationFamily initializeSeldan(List<SimulationFamily> families) {
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

    SimulationFamily initializeSarael(List<SimulationFamily> families) {
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

    SimulationFamily initializeLualent(List<SimulationFamily> families) {
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

    SimulationFamily initializeRaevill(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeVealer(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeBarsaes(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeSuelet(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeRuneFist(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeGillear(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeSzubienicznik(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeErteseas(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeBloodyHead(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeHolzer(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeZakrzepek(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeTagar(List<SimulationFamily> families) {
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

    SimulationFamily initializeMeczybula(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeAstager(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeSarrazin(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeLionear(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeIloserin(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeSiroen(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeZakutyLeb(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeBrugdenall(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeMondenero(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeSzybcioszek(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeMorten(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeSarrambert(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeCastellano(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeBurczybrzuszek(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeDavila(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeBonnet(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeZauszek(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeNavarra(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeElfogrzmot(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeSmoliszek(List<SimulationFamily> families, List<VassalFrom> seniors) {
        SimulationFamily family = new SimulationFamily();
        ConfigurationFamily conf = ConfigurationFamily.builder()

                .build();
        ModelFamily model = ModelFamily.builder()
                .setId(35)
                .setRace(Race.HALFLING)
                .setCreationYear(1216)
                .setType(FamilyType.PREDEFINED)
                .setStrength(4)
                .setSurname("Smoliszek")
                .build();
        families.add(family);
        return mergeAndRegisterFamily(family, conf, model, families);
    }

    SimulationFamily initializeBrueldell(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeCarranza(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeMelearis(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeGaumont(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeSartori(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeBolinosek(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeMoczydupa(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeOsmiorniczek(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeGrabendell(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeGastrogall(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeNalesniczek(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeKorniszonek(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializePierniczek(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeSergevall(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeZielonaWisnia(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializePaquin(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeMolla(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeDergonall(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializePalniczek(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeHertezza(List<SimulationFamily> families, List<VassalFrom> seniors) {
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

    SimulationFamily initializeCnyMilorzab(List<SimulationFamily> families, List<VassalFrom> seniors) {
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
}