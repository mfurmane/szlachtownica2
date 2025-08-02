package priv.mfurmane.szlachtownica.model;

import priv.mfurmane.szlachtownica.model.naming.NamingStrategy;

public class RaceProfile {
    private final int avgPregnancyLength;
    private final int preferredMarriageAgeOffset;
    private final int baseChildRate;
    private final int defaultFamilySize;
    private NamingStrategy namingStrategy;
    // basic
    private final int fertileFrom;
    private final int fertileTo;
    private final int lifespan;
    private final double baseDeathChance;
    // ages
    private final Integer hornyAge;
    private final Integer stableAge;
    private final Integer marriageAge;
    private final Integer mourningTime;
    // sexual style
    private final Double horny;
    private final Double loyal;
    private final Double homo;
    private final Double interracial;
    private final Double attachment;
    private final Double amorous;
    private final Double poliamoric;
    // character
    private final Double jealous;
    private final Double impulsive;
    private final Double temperament; // impulsive?
    private final Double proud;
    private final Double paranoid; //skłonność do paniki/uderzeń wyprzedzających
    private final Double ambition;  //czy postać będzie próbować zdobyć władzę/pozycję
    private final Double divorcable;
    private final Double revengous;
    private final Double honor; // do wpływu na decyzje o ślubie, zemście, lojalności
    private final Double diplomatic;
    private final Double manipulative; //chęci mącenia
    private final Double cunning; //przebiegłość
    private final Double moral;
    private final Double charisma; //zdobywanie sympatii
    private final Double intellect;
    private final Double beauty;
    // biology & society
    private final Double fertility;
    private final Double health;

    private RaceProfile(Builder builder) {
        this.avgPregnancyLength = builder.avgPregnancyLength;
        this.preferredMarriageAgeOffset = builder.preferredMarriageAgeOffset;
        this.baseChildRate = builder.baseChildRate;
        this.defaultFamilySize = builder.defaultFamilySize;
        this.namingStrategy = builder.namingStrategy;
        this.fertileFrom = builder.fertileFrom;
        this.fertileTo = builder.fertileTo;
        this.lifespan = builder.lifespan;
        this.baseDeathChance = builder.baseDeathChance;
        this.hornyAge = builder.hornyAge;
        this.stableAge = builder.stableAge;
        this.marriageAge = builder.marriageAge;
        this.mourningTime = builder.mourningTime;
        this.horny = builder.horny;
        this.loyal = builder.loyal;
        this.homo = builder.homo;
        this.interracial = builder.interracial;
        this.attachment = builder.attachment;
        this.amorous = builder.amorous;
        this.poliamoric = builder.poliamoric;
        this.jealous = builder.jealous;
        this.impulsive = builder.impulsive;
        this.temperament = builder.temperament;
        this.proud = builder.proud;
        this.paranoid = builder.paranoid;
        this.ambition = builder.ambition;
        this.divorcable = builder.divorcable;
        this.revengous = builder.revengous;
        this.honor = builder.honor;
        this.diplomatic = builder.diplomatic;
        this.manipulative = builder.manipulative;
        this.cunning = builder.cunning;
        this.moral = builder.moral;
        this.charisma = builder.charisma;
        this.fertility = builder.fertility;
        this.health = builder.health;
        this.intellect = builder.intellect;
        this.beauty = builder.beauty;
    }

    public int getAvgPregnancyLength() {
        return avgPregnancyLength;
    }

    public int getPreferredMarriageAgeOffset() {
        return preferredMarriageAgeOffset;
    }

    public int getBaseChildRate() {
        return baseChildRate;
    }

    public int getDefaultFamilySize() {
        return defaultFamilySize;
    }

    public NamingStrategy getNamingStrategy() {
        return namingStrategy;
    }

    public void setNamingStrategy(NamingStrategy namingStrategy) {
        this.namingStrategy = namingStrategy;
    }

    public int getFertileFrom() {
        return fertileFrom;
    }

    public int getFertileTo() {
        return fertileTo;
    }

    public int getLifespan() {
        return lifespan;
    }

    public double getBaseDeathChance() {
        return baseDeathChance;
    }

    public Integer getHornyAge() {
        return hornyAge;
    }

    public Integer getStableAge() {
        return stableAge;
    }

    public Integer getMarriageAge() {
        return marriageAge;
    }

    public Integer getMourningTime() {
        return mourningTime;
    }

    public Double getHorny() {
        return horny;
    }

    public Double getLoyal() {
        return loyal;
    }

    public Double getHomo() {
        return homo;
    }

    public Double getInterracial() {
        return interracial;
    }

    public Double getAttachment() {
        return attachment;
    }

    public Double getAmorous() {
        return amorous;
    }

    public Double getPoliamoric() {
        return poliamoric;
    }

    public Double getJealous() {
        return jealous;
    }

    public Double getImpulsive() {
        return impulsive;
    }

    public Double getTemperament() {
        return temperament;
    }

    public Double getProud() {
        return proud;
    }

    public Double getParanoid() {
        return paranoid;
    }

    public Double getAmbition() {
        return ambition;
    }

    public Double getDivorcable() {
        return divorcable;
    }

    public Double getRevengous() {
        return revengous;
    }

    public Double getHonor() {
        return honor;
    }

    public Double getDiplomatic() {
        return diplomatic;
    }

    public Double getManipulative() {
        return manipulative;
    }

    public Double getCunning() {
        return cunning;
    }

    public Double getMoral() {
        return moral;
    }

    public Double getCharisma() {
        return charisma;
    }

    public Double getFertility() {
        return fertility;
    }

    public Double getHealth() {
        return health;
    }

    public Double getIntellect() {
        return intellect;
    }

    public Double getBeauty() {
        return beauty;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int avgPregnancyLength = 40;
        private int preferredMarriageAgeOffset = 2;
        private int baseChildRate = 2;
        private int defaultFamilySize = 4;
        private NamingStrategy namingStrategy;
        // basic
        private int fertileFrom = 14;
        private int fertileTo = 50;
        private int lifespan = 80;
        private double baseDeathChance = 0.02;
        // ages
        private Integer hornyAge = 12;
        private Integer stableAge = 18;
        private Integer marriageAge = 20;
        private Integer mourningTime = 24;
        // sexual style
        private Double horny = 0.7;
        private Double loyal = 0.7;
        private Double homo = 0.3;
        private Double interracial = 0.3;
        private Double attachment = 0.6;
        private Double amorous = 0.5;
        private Double poliamoric = 0.4;
        // character
        private Double jealous = 0.5;
        private Double impulsive = 0.5;
        private Double temperament = 0.5; // impulsive?
        private Double proud = 0.5;
        private Double paranoid = 0.25; //skłonność do paniki/uderzeń wyprzedzających
        private Double ambition = 0.5;  //czy postać będzie próbować zdobyć władzę/pozycję
        private Double divorcable = 0.5;
        private Double revengous = 0.5;
        private Double honor = 0.5; // do wpływu na decyzje o ślubie, zemście, lojalności
        private Double diplomatic = 0.5;
        private Double manipulative = 0.5; //chęci mącenia
        private Double cunning = 0.5; //przebiegłość
        private Double moral = 0.5;
        private Double charisma = 0.5; //zdobywanie sympatii
        private Double intellect = 0.5;
        private Double beauty = 0.5;
        // biology & society
        private Double fertility = 0.5;
        private Double health = 0.5;

        public Builder setAvgPregnancyLength(int avgPregnancyLength) {
            this.avgPregnancyLength = avgPregnancyLength;
            return this;
        }

        public Builder setPreferredMarriageAgeOffset(int preferredMarriageAgeOffset) {
            this.preferredMarriageAgeOffset = preferredMarriageAgeOffset;
            return this;
        }

        public Builder setBaseChildRate(int baseChildRate) {
            this.baseChildRate = baseChildRate;
            return this;
        }

        public Builder setDefaultFamilySize(int defaultFamilySize) {
            this.defaultFamilySize = defaultFamilySize;
            return this;
        }

        public Builder setFertileFrom(int fertileFrom) {
            this.fertileFrom = fertileFrom;
            return this;
        }

        public Builder setFertileTo(int fertileTo) {
            this.fertileTo = fertileTo;
            return this;
        }

        public Builder setLifespan(int lifespan) {
            this.lifespan = lifespan;
            return this;
        }

        public Builder setBaseDeathChance(double baseDeathChance) {
            this.baseDeathChance = baseDeathChance;
            return this;
        }

        public Builder setHornyAge(Integer hornyAge) {
            this.hornyAge = hornyAge;
            return this;
        }

        public Builder setStableAge(Integer stableAge) {
            this.stableAge = stableAge;
            return this;
        }

        public Builder setMarriageAge(Integer marriageAge) {
            this.marriageAge = marriageAge;
            return this;
        }

        public Builder setMourningTime(Integer mourningTime) {
            this.mourningTime = mourningTime;
            return this;
        }

        public Builder setHorny(Double horny) {
            this.horny = horny;
            return this;
        }

        public Builder setLoyal(Double loyal) {
            this.loyal = loyal;
            return this;
        }

        public Builder setHomo(Double homo) {
            this.homo = homo;
            return this;
        }

        public Builder setInterracial(Double interracial) {
            this.interracial = interracial;
            return this;
        }

        public Builder setAttachment(Double attachment) {
            this.attachment = attachment;
            return this;
        }

        public Builder setAmorous(Double amorous) {
            this.amorous = amorous;
            return this;
        }

        public Builder setPoliamoric(Double poliamoric) {
            this.poliamoric = poliamoric;
            return this;
        }

        public Builder setJealous(Double jealous) {
            this.jealous = jealous;
            return this;
        }

        public Builder setImpulsive(Double impulsive) {
            this.impulsive = impulsive;
            return this;
        }

        public Builder setTemperament(Double temperament) {
            this.temperament = temperament;
            return this;
        }

        public Builder setProud(Double proud) {
            this.proud = proud;
            return this;
        }

        public Builder setParanoid(Double paranoid) {
            this.paranoid = paranoid;
            return this;
        }

        public Builder setAmbition(Double ambition) {
            this.ambition = ambition;
            return this;
        }

        public Builder setDivorcable(Double divorcable) {
            this.divorcable = divorcable;
            return this;
        }

        public Builder setRevengous(Double revengous) {
            this.revengous = revengous;
            return this;
        }

        public Builder setHonor(Double honor) {
            this.honor = honor;
            return this;
        }

        public Builder setDiplomatic(Double diplomatic) {
            this.diplomatic = diplomatic;
            return this;
        }

        public Builder setManipulative(Double manipulative) {
            this.manipulative = manipulative;
            return this;
        }

        public Builder setCunning(Double cunning) {
            this.cunning = cunning;
            return this;
        }

        public Builder setMoral(Double moral) {
            this.moral = moral;
            return this;
        }

        public Builder setCharisma(Double charisma) {
            this.charisma = charisma;
            return this;
        }

        public Builder setFertility(Double fertility) {
            this.fertility = fertility;
            return this;
        }

        public Builder setHealth(Double health) {
            this.health = health;
            return this;
        }

        public Builder setIntellect(Double intellect) {
            this.intellect = intellect;
            return this;
        }

        public Builder setBeauty(Double beauty) {
            this.beauty = beauty;
            return this;
        }

        public RaceProfile build() {
            return new RaceProfile(this);
        }
    }
}
