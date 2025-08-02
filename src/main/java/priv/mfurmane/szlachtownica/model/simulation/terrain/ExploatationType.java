package priv.mfurmane.szlachtownica.model.simulation.terrain;

import java.time.LocalDate;
import java.time.Month;

public enum ExploatationType {
    WOOD(0.03, Month.JANUARY, Month.DECEMBER, ResourceCategory.STONE),
    WATER(0.03, Month.JANUARY, Month.DECEMBER, ResourceCategory.FLUID), //woda pitna
    WILD_ANIMALS(0.05, Month.JANUARY, Month.DECEMBER, ResourceCategory.FOOD),
    WILD_FRUITS(0.1, Month.MARCH, Month.SEPTEMBER, ResourceCategory.FOOD),
    FISHES(0.07, Month.JANUARY, Month.DECEMBER, ResourceCategory.FOOD);

    private final double recovery;
    private final Month start;
    private final Month end;
    private final ResourceCategory category;

    public ResourceCategory getCategory() {
        return category;
    }

    public double getRecovery(LocalDate date) {
        int monthValue = date.getMonthValue();
        int startValue = start.getValue();
        int endValue = end.getValue();
        boolean inRange = startValue <= endValue
                ? (monthValue >= startValue && monthValue <= endValue)
                : (monthValue >= startValue || monthValue <= endValue);
        return inRange ? recovery : 0;
    }

    ExploatationType(double recovery, Month start, Month end, ResourceCategory category) {
        this.recovery = recovery;
        this.start = start;
        this.end = end;
        this.category = category;
    }
}
