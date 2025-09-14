package priv.mfurmane.szlachtownica.model.simulation.goal;

import java.util.Arrays;
import java.util.List;

public enum Goal {
    //💰 Materializm / Status / Ambicja
//Cele związane z dobrami materialnymi, statusem społecznym i prestiżem.
    GREED(true, true, true), // – dążenie do bogactwa i jego pomnażania dla samego bogactwa
    LUXURY(true, true, true), // – dążenie do przynależenia do bogatej gałęzi rodziny dla wygody
    CHILDREN_WEALTH(true, true, false), // – chęć zapewnienia dzieciom bogactwa
    CHILDREN_STATUS(true, true, false), // – chęć zapewnienia dzieciom statusu społecznego
    PRESTIGE(true, true, true), // – dążenie do uznania i bycia podziwianym, w tym atrakcyjny małżonek, czy publiczne kochanki
    RESPECT(true, true, true), // – dążenie do uznania/autorytetu
    RECOGNITION(true, true, true), // – sława, reputacja
    CONTROLLED_IMAGE(true, true, true), // – dbałość o pozory i reputację
    //🏛️ Władza / Kontrola / Wpływ
//Cele związane z dominacją, kontrolą nad sobą lub innymi.
    POWER(true, true, true), // – dążenie do poszerzania swoich wpływów i rzeczywistej władzy
    INFLUENCE(true, true, true), // – miękka władza nad innymi
    CONTROL(true, true, true), // – kontrolowanie innych/sytuacji
    DESTABILIZATION(true, true, true), // – destabilizacja sprzyja nagłym i dużym zmianom, można wypłynąć
    STABILIZATION(true, true, true), // – dążenie do stabilnośći i bezpieczeństwa i jego zachowania na szeroką skalę
    SECURITY(true, true, true), // – unikanie ryzyka, dążenie do osobistej stabilizacji
    VENGEANCE(true, true, true), // – dążenie do odwetu
    //❤️ Relacje / Miłość / Przynależność
//Cele społeczne i emocjonalne, oparte na relacjach międzyludzkich.
    LOVE(true, false, false), // – romantyzm, poszukiwanie miłości większej niż życie
    LUST(true, false, false), // – głównym motorem napędowym jest popęd, atrakcyjność partnerów ważna
    BELONGING(true, false, false), // – potrzeba przynależności
    DYNASTY_SURVIVE(true, true, false), // – dążenie do oficjalnej reprodukcji i przekazania nazwiska
    REPRODUCTION(true, true, false), // – prymitywna potrzeba mnożenia się
    BLOODLINE_PURITY(true, true, false), // – czystość rasowa/dynastyczna
    //🧠 Intelekt / Duchowość / Pamięć
//Cele wyższego rzędu, poznawcze, ideowe i związane z dziedzictwem.
    KNOWLEDGE(true, true, true), // – chęć poznania, studiowania, mistycyzm
    LEGACY(true, true, true), // – pozostawienie śladu w historii
    FAITH(true, true, true), // – religijność, mistyka, ideologiczna gorliwość
    //🧍‍♂️ Autonomia / Samorealizacja / Przetrwanie
//Cele związane z przetrwaniem, niezależnością i spełnieniem wewnętrznym.
    PERSONAL_SURVIVE(true, true, true), // – chęć przetrwania
    CALMNESS(true, true, true), // – dążenie do spokoju, niezależnie od własnego statusu
    FUN(true, true, true), // – chęć ciągłej zabawy, imprezy, bale, orgie, gry, podróże
    CHAOS(true, true, true), // – głównym motorem napędowym jest nuda i chęć, żeby coś się działo
    ATTENTION(true, true, true), // – chęć ciągłego bycia w centrum uwagi
    FREEDOM(true, true, true), // – niechęć do zobowiązań i struktur
    INDEPENDENCE(true, true, true); // – unikanie zależności

    private final Boolean personal;
    private final Boolean family;
    private final Boolean organization;

    public List<Goal> getPersonal() {
        return Arrays.stream(values()).filter(goal -> goal.personal).toList();
    }

    public List<Goal> getFamily() {
        return Arrays.stream(values()).filter(goal -> goal.family).toList();
    }

    public List<Goal> getOrganization() {
        return Arrays.stream(values()).filter(goal -> goal.organization).toList();
    }

    public Boolean canBePersonal() {
        return personal;
    }

    public Boolean canBeFamily() {
        return family;
    }

    public Boolean canBeOrganization() {
        return organization;
    }

    Goal(Boolean personal, Boolean family, Boolean organization) {
        this.personal = personal;
        this.family = family;
        this.organization = organization;
    }

}
