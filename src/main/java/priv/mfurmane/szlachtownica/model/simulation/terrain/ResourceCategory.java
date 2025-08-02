package priv.mfurmane.szlachtownica.model.simulation.terrain;

public enum ResourceCategory {
    STONE(new ResourceDeposit[]{ResourceDeposit.MEDIUM, ResourceDeposit.BIG, ResourceDeposit.VERY_BIG, ResourceDeposit.EXTRA_BIG, ResourceDeposit.HUGE, ResourceDeposit.ENORMOUS, ResourceDeposit.IMPOSSIBLE}),
    ORE(new ResourceDeposit[]{ResourceDeposit.MEDIUM, ResourceDeposit.BIG, ResourceDeposit.VERY_BIG, ResourceDeposit.EXTRA_BIG, ResourceDeposit.LARGE, ResourceDeposit.VERY_LARGE, ResourceDeposit.HUGE}),
    GEM(new ResourceDeposit[]{ResourceDeposit.MICRO, ResourceDeposit.PETITE, ResourceDeposit.TINY, ResourceDeposit.VERY_SMALL, ResourceDeposit.SMALL, ResourceDeposit.RATHER_SMALL, ResourceDeposit.MEDIUM}),
    FLUID(new ResourceDeposit[]{ResourceDeposit.SMALL, ResourceDeposit.MEDIUM, ResourceDeposit.BIG, ResourceDeposit.EXTRA_BIG, ResourceDeposit.LARGE, ResourceDeposit.VERY_HUGE, ResourceDeposit.COLOSSAL}),
    FOOD(new ResourceDeposit[]{ResourceDeposit.VERY_SMALL, ResourceDeposit.SMALL, ResourceDeposit.RATHER_SMALL, ResourceDeposit.MEDIUM, ResourceDeposit.BIG, ResourceDeposit.VERY_BIG, ResourceDeposit.EXTRA_BIG});

    private final ResourceDeposit[] deposits;

    public ResourceDeposit[] getDeposits() {
        return deposits;
    }

    ResourceCategory(ResourceDeposit[] deposits) {
        this.deposits = deposits;
    }
}
