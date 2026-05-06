package priv.mfurmane.szlachtownica.model;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public enum CharacterTrait {
    MODESTY("Skromność"),
    ALTRUISM("Altruizm"),
    OPTIMISM("Optymizm"),
    GENTLENESS("Łagodność"),
    COURAGE("Odwaga"),
    EGOISM("Egoizm"),
    SUSPICION("Podejrzliwość"),
    CALMNESS("Spokój"),
    COWARDICE("Tchórzostwo"),
    EARTHLINESS("Przyziemność"),
    PRIDE("Pycha"),
    ROMANTICISM("Romantyzm"),
    CHILDHOOD("Dziecko"),
    HONESTY("Szczerość"),
    PASSION("Pasja"),
    RUTHLESSNESS("Bezwzględność"),
    TRUST("Ufność"),
    DEPRESSION("Depresja"),
    SERIOUSNESS("Powaga"),
    CAUTION("Rozwaga"),
    MYSTICISM("Mistycyzm"),
    IMPATIENCE("Niecierpliwość"),
    RESPONSIBILITY("Odpowiedzialność"),
    IRRESPONSIBILITY("Nieodpowiedzialność"),
    IMPULSIVITY("Impulsywność"),
    REALNESS("Rzeczowość"),
    PERSEVERANCE("Wytrwałość"),
    DECEPTIVENESS("Zwodniczość"),
    PLAYFULNESS("Rozrywkowość"),
    VIRTUE("Cnotliwość"),
    BLOODTHIRSTINESS("Krwiożerczość");

    private final String meaning;

    CharacterTrait(String meaning) {
        this.meaning = meaning;
    }

    public static CharacterTrait getRandom() {
        List<CharacterTrait> values = List.of(values());
        return values.get(ThreadLocalRandom.current().nextInt(values.size()));
    }

    String getMeaning() {
        return meaning;
    }
}
